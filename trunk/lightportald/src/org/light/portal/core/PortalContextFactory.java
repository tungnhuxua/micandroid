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

package org.light.portal.core;

import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalContextFactory {
	/** Thread Local variable available in whole Thread to keep PortalContext */
	private static final ThreadLocal threadSession = new ThreadLocal();
	/** Logger for this class and subclasses */
	protected static Logger logger = LoggerFactory.getLogger(PortalContextFactory.class);
	
	public static PortalContext getPortalContext() {
		PortalContext sc = (PortalContext) threadSession.get();		
		if (sc == null) {
			logger.debug("Opening new ServiceContext for this thread.");
			sc = new PortalContext();
			threadSession.set(sc);
		}		
		return sc;
	}	
}
