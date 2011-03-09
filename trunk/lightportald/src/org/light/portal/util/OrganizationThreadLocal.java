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

import static org.light.portal.util.Constants._DEFAULT_ORG_LOGO;
import static org.light.portal.util.Constants._DEFAULT_ORG_LOGOICON;
import static org.light.portal.util.Constants._DEFAULT_USER;

import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Organization;

/**
 * 
 * @author Jianmin Liu
 **/
public class OrganizationThreadLocal {
	public static Organization getOrg() {
		Organization org = _threadLocal.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getOrg " + org);
		}

		return org;		
	}
	public static long getOrganizationId() {
		Organization org = _threadLocal.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getOrganizationId " + org);
		}

		if (org != null) {
			return org.getId();
		}
		else {
			return 0;
		}
	}
	
	public static String getDefaultUserId() {
		Organization org = _threadLocal.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getDefaultUserId " + org);
		}

		if (org != null) {
			return org.getUser().getUserId();
		}
		else {
			return _DEFAULT_USER;
		}
	}
	
	public static String getWebId() {
		Organization org = _threadLocal.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getWebId " + org);
		}

		if (org != null) {
			return org.getWebId();
		}
		else {
			return "";
		}
	}
	
	public static String getLogo() {
		Organization org = _threadLocal.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getLogo " + org);
		}

		if (org != null && !StringUtil.isEmpty(org.getLogoUrl())) {
			return org.getLogoUrl();
		}
		else {
			return _DEFAULT_ORG_LOGO;
		}
	}
	
	public static String getLogoIcon() {
		Organization org = _threadLocal.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getLogoIcon " + org);
		}

		if (org != null && !StringUtil.isEmpty(org.getLogoIcon())) {
			return org.getLogoIcon();
		}
		else {
			return _DEFAULT_ORG_LOGOICON;
		}
	}
	
	public static void setOrganization(Organization org) {
		if (_log.isDebugEnabled()) {
			_log.debug("setOrganizationId " + org.getId());
		}

		if (org != null) {
			_threadLocal.set(org);
		}
		else {
			_threadLocal.set(null);
		}
	}

	private static Logger _log = LoggerFactory.getLogger(OrganizationThreadLocal.class);
	private static InheritableThreadLocal<Organization> _threadLocal = new InheritableThreadLocal<Organization>();
}
