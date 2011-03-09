package org.light.portal.portlet.factory;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.light.portal.portlet.core.impl.PortletPreferencesImpl;
import org.light.portal.portlet.core.impl.PortletWindow;

public class PortletPreferencesFactory {
	 private static PortletPreferencesFactory instance = new PortletPreferencesFactory();
	 
	 public static PortletPreferencesFactory getInstance(){
			return instance;
	 }
	 	 
	 public PortletPreferences getPortletPreferences(Integer methodId, PortletRequest request, PortletWindow portletWindow){ 
		 return new PortletPreferencesImpl(methodId, request, portletWindow);
	 }
}
