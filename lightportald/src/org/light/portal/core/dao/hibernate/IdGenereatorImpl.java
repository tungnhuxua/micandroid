 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portal.core.dao.hibernate;

import static org.light.portal.util.Constants._DATABASE_INSTANCE;
import static org.light.portal.util.Constants._GENEREATOR_INTERVAL;
import static org.light.portal.util.Constants._GENEREATOR_LOAD_COUNT;
import static org.light.portal.util.Constants._GENEREATOR_LOAD_INTERVAL;
import static org.light.portal.util.Constants._GENEREATOR_LOAD_THRESHOLD;
import static org.light.portal.util.Constants._GENEREATOR_START;

import java.sql.Timestamp;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.light.portal.core.dao.IdGenereator;
import org.light.portal.model.SequenceId;

/**
 * 
 * @author Jianmin Liu
 **/
public class IdGenereatorImpl extends BaseDaoImpl implements IdGenereator{

	private Queue<Long> queue = new ConcurrentLinkedQueue<Long>();
	private AtomicInteger counter = new AtomicInteger(0);
	
	public void init(){
		loadIds();
		new Worker();
	}
	
	public long getId(){
		Long id = queue.poll();
		if(id == null){
			loadIds();
			id = queue.poll();
		}
		counter.set(counter.get() - 1);
		return id;			
	}
	
	private void loadIds(){
		logger.info(String.format("IdGenerator currently contains %d ids which start with %d.",counter.get(), (counter.get() == 0) ? 0 : queue.peek()));
		if(counter.get() > _GENEREATOR_LOAD_THRESHOLD) return;		
		while(counter.get() < _GENEREATOR_LOAD_THRESHOLD){
			try{
				SequenceId sequence = (SequenceId)this.getEntityById(SequenceId.class,_DATABASE_INSTANCE);
				if(sequence == null){
					sequence = new SequenceId(_DATABASE_INSTANCE,_GENEREATOR_START+_DATABASE_INSTANCE);
					sequence.setModifiedDate(new Timestamp(System.currentTimeMillis()));		    	
					this.save(sequence,false);
				}
				long firstId = sequence.getCurrentId();		
				sequence.setCurrentId(firstId+_GENEREATOR_LOAD_COUNT*_GENEREATOR_INTERVAL);
				sequence.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				this.save(sequence,false);
				for(int i=0;i<_GENEREATOR_LOAD_COUNT;i++){
					queue.offer(firstId+i*_GENEREATOR_INTERVAL);
				}
				counter.set(counter.get() + _GENEREATOR_LOAD_COUNT);
				logger.info(String.format("IdGenerator geneate %d ids which started from %d [total: %d]",_GENEREATOR_LOAD_COUNT,firstId,counter.get()));
				
			}catch(Exception e){				
			}
		}
	}
	
	class Worker implements Runnable{
		
		public Worker(){
			new Thread(this).start();		
		}
		
		public void run(){
			while(true){
				try{					
					Thread.sleep(_GENEREATOR_LOAD_INTERVAL);
					loadIds();
				}catch(Throwable e){
					logger.error(e.getMessage());
				}
			}
		}
		
	}
}
