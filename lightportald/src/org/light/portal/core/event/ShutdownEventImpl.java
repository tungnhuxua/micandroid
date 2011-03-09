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
package org.light.portal.core.event;

import javax.servlet.ServletContext;

import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.search.IndexWorker;
/**
 * 
 * @author Jianmin Liu
 **/
public class ShutdownEventImpl implements ShutdownEvent {
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	public void execute(ServletContext servletContext) throws Exception{
		logger.debug("shutdown event is begining");

		logger.debug("shutdown event is ending");
	}
}
