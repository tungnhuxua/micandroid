/*
 * =============================================================================
 * Copyright by Benjamin Sempere,
 * All rights reserved.
 * =============================================================================
 * Author  : Benjamin Sempere
 * E-mail  : benjamin@sempere.org
 * Homepage: www.sempere.org
 * =============================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.sempere.commons.jmx;

import java.util.Collection;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

/**
 * Interface that defines methods to work with JMX.
 * 
 * @author bsempere
 */
public interface JMXManager {

	// ********************************************************************************
	//
	// MBeans registration
	//
	// ********************************************************************************

	/**
	 * Registers a MBean
	 * 
	 * @param objectName the name used for MBean registration
	 * @param clazz the class of the MBean to be registered
	 */
	void registerMBean(String objectName, Class<?> clazz);

	/**
	 * Registers a MBean
	 * 
	 * @param objectName the name used for MBean registration
	 * @param clazzName the class of the MBean to be registered
	 */
	void registerMBean(String objectName, String clazzName);

	/**
	 * Registers a MBean
	 * 
	 * @param objectName the name used for MBean registration
	 * @param clazz the class of the MBean to be registered
	 */
	void registerMBean(ObjectName objectName, Class<?> clazz);

	/**
	 * Registers a MBean
	 * 
	 * @param objectName the name used for MBean registration
	 * @param clazzName the class of the MBean to be registered
	 */
	void registerMBean(ObjectName objectName, String clazzName);

	// ********************************************************************************
	//
	// MBeans un-registration
	//
	// ********************************************************************************

	/**
	 * Unregisters the given MBean
	 * 
	 * @param objectName the name used for MBean un-registration
	 */
	void unregisterMBean(String objectName);

	/**
	 * Unregisters the given MBean
	 * 
	 * @param objectName the name used for MBean un-registration
	 */
	void unregisterMBean(ObjectName objectName);

	// ********************************************************************************
	//
	// MBeans research
	//
	// ********************************************************************************

	/**
	 * Find the first objectName that matches the given pattern
	 * 
	 * @param objectNamePattern
	 * @return ObjectName
	 */
	ObjectName queryObjectName(String objectNamePattern);

	/**
	 * Find the first objectName that matches the given pattern
	 * 
	 * @param objectNamePattern
	 * @return ObjectName
	 */
	ObjectName queryObjectName(ObjectName objectNamePattern);

	/**
	 * Finds all objectNames that matches the given pattern
	 * 
	 * @param objectNamePattern
	 * @return Collection<ObjectName>
	 */
	Collection<ObjectName> queryObjectNames(String objectNamePattern);

	/**
	 * Finds all objectNames that matches the given pattern
	 * 
	 * @param objectNamePattern
	 * @return Collection<ObjectName>
	 */
	Collection<ObjectName> queryObjectNames(ObjectName objectNamePattern);

	// ********************************************************************************
	//
	// MBeans proxy
	//
	// ********************************************************************************

	/**
	 * Returns a proxy on the MBean identified by the given objectName
	 * 
	 * @param objectName the MBean identifier
	 * @param clazz the MBean interface class
	 * @return <T>
	 */
	<T> T getMBeanProxy(ObjectName objectName, Class<T> clazz);

	/**
	 * Returns a proxy on the MBean identified by the given objectName
	 * 
	 * @param objectName the MBean identifier
	 * @param clazz the MBean interface class
	 * @param isNotificationBroadcaster
	 * @return <T>
	 */
	<T> T getMBeanProxy(ObjectName objectName, Class<T> clazz, boolean isNotificationBroadcaster);

	/**
	 * Returns a proxy on the MBean identified by the given objectName
	 * 
	 * @param objectName the MBean identifier
	 * @param clazzName the MBean interface class
	 * @return <T>
	 */
	<T> T getMBeanProxy(ObjectName objectName, String clazzName);

	/**
	 * Returns a proxy on the MBean identified by the given objectName
	 * 
	 * @param objectName the MBean identifier
	 * @param clazzName the MBean interface class
	 * @param isNotificationBroadcaster
	 * @return <T>
	 */
	<T> T getMBeanProxy(ObjectName objectName, String clazzName, boolean isNotificationBroadcaster);

	/**
	 * Returns a proxy on the MBean identified by the given objectName
	 * 
	 * @param objectName the MBean identifier
	 * @param clazz the MBean interface class
	 * @return <T>
	 */
	<T> T getMBeanProxy(String objectName, Class<T> clazz);

	/**
	 * Returns a proxy on the MBean identified by the given objectName
	 * 
	 * @param objectName the MBean identifier
	 * @param clazzName the MBean interface class
	 * @return <T>
	 */
	<T> T getMBeanProxy(String objectName, String clazzName);

	// ********************************************************************************
	//
	// MBeans convenience methods
	//
	// ********************************************************************************
	
	/**
	 * Returns the MBeanServerConnection bound to this manager
	 * 
	 * @return MBeanServerConnection
	 */
	MBeanServerConnection getMBeanServerConnection();
}
