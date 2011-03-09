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

package org.light.portal.logger;


/**
 * 
 * @author Jianmin Liu
 **/
public class LoggerImpl implements Logger{

	private org.apache.log4j.Logger logger;
	
	public LoggerImpl(Class klass){
		this.logger = org.apache.log4j.Logger.getLogger(klass);
	}
	
	public void trace(Object message){
		this.logger.trace(message);
	}
    public void debug(Object message){
    	if(isDebugEnabled())
    		this.logger.debug(message);
	}
    public void info(Object message){
    	this.logger.info(message);
	}
    public void warn(Object message){
    	this.logger.warn(message);
	}
    public void error(Object message){
    	this.logger.error(message);
	}
    public void fatal(Object message){
    	this.logger.fatal(message);
	}

    // generic printing method:
    public void log(Level l, Object message){
    	if(l == Logger.Level.TRACE){
    		trace(message);
    	}else if(l == Logger.Level.DEBUG){
    		debug(message);
    	}else if(l == Logger.Level.INFO){
    		info(message);
    	}else if(l == Logger.Level.WARN){
    		warn(message);
    	}else if(l == Logger.Level.ERROR){
    		error(message);
    	}else if(l == Logger.Level.FATAL){
    		fatal(message);
    	}
    }
    
    public boolean isDebugEnabled(){
    	return this.logger.isDebugEnabled();
    }
}
