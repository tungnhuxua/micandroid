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

package org.light.portal.util;

import static org.light.portal.util.Constants._PERMISSION_PREFIX;

/**
 * 
 * @author Jianmin Liu
 **/
public class PermissionUtil {

	public static boolean hasPermission(long permissions, String permission){
		long value = 1L << PropUtil.getLong(_PERMISSION_PREFIX+permission);		
		return ((permissions & value) > 0) ? true : false;
	}
	
	public static long getPermission(String permission){
		return 1L << PropUtil.getLong(_PERMISSION_PREFIX+permission);
	}
	
	public static long getPermission(String[] permissions){
		long permission = 0L;
		for(String p : permissions){
			long value = 1L << PropUtil.getLong(_PERMISSION_PREFIX+p);
			permission = (permission > 0) ? permission | value : value;
		}
		return permission;
	}
	
}
