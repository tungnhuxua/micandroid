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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.util.Collection;

/**
 * Base implementation of JMXManager.
 *
 * @author bsempere
 */
public class JMXManagerImpl implements JMXManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMXManagerImpl.class);

    // Connection to the MBeanServer
    private MBeanServerConnection connection;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public JMXManagerImpl() {
        this.connection = ManagementFactory.getPlatformMBeanServer();
        LOGGER.debug("MBeanServerConnection obtained from PlatformMBeanServer stored successfully.");
    }

    public JMXManagerImpl(String url, String user, String password) {
        // Creates the service URL
        JMXServiceURL serviceURL = JMXHelper.createServiceURL(url);

        // Creates a connector
        JMXConnector connector = JMXHelper.createConnector(serviceURL, user, password, true);

        // Stores the MBeanServerConnection
        this.connection = JMXHelper.getMBeanServerConnection(connector);
        LOGGER.debug("MBeanServerConnection stored successfully");
    }

    public JMXManagerImpl(String protocol, String host, int port, String user, String password, String urlPath) {
        // Creates the service URL
        JMXServiceURL serviceURL = JMXHelper.createServiceURL(protocol, host, port, urlPath);

        // Creates a connector
        JMXConnector connector = JMXHelper.createConnector(serviceURL, user, password, true);

        // Stores the MBeanServerConnection
        this.connection = JMXHelper.getMBeanServerConnection(connector);
        LOGGER.debug("MBeanServerConnection stored successfully");
    }

    // ********************************************************************************
    //
    // MBeans registration
    //
    // ********************************************************************************

    public void registerMBean(String objectName, Class<?> clazz) {
        JMXHelper.registerMBean(objectName, clazz, this.connection);
    }

    public void registerMBean(String objectName, String clazzName) {
        JMXHelper.registerMBean(objectName, clazzName, this.connection);
    }

    public void registerMBean(ObjectName objectName, Class<?> clazz) {
        JMXHelper.registerMBean(objectName, clazz, this.connection);
    }

    public void registerMBean(ObjectName objectName, String clazzName) {
        JMXHelper.registerMBean(objectName, clazzName, this.connection);
    }

    // ********************************************************************************
    //
    // MBeans un-registration
    //
    // ********************************************************************************

    public void unregisterMBean(String objectName) {
        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    public void unregisterMBean(ObjectName objectName) {
        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    // ********************************************************************************
    //
    // MBeans research
    //
    // ********************************************************************************

    public ObjectName queryObjectName(String objectNamePattern) {
        return JMXHelper.queryObjectName(objectNamePattern, this.connection);
    }

    public ObjectName queryObjectName(ObjectName objectNamePattern) {
        return JMXHelper.queryObjectName(objectNamePattern, this.connection);
    }

    public Collection<ObjectName> queryObjectNames(String objectNamePattern) {
        return JMXHelper.queryObjectNames(objectNamePattern, this.connection);
    }

    public Collection<ObjectName> queryObjectNames(ObjectName objectNamePattern) {
        return JMXHelper.queryObjectNames(objectNamePattern, this.connection);
    }

    // ********************************************************************************
    //
    // MBeans manipulation
    //
    // ********************************************************************************

    public <T> T getMBeanProxy(ObjectName objectName, Class<T> clazz) {
        return this.getMBeanProxy(objectName, clazz, false);
    }

    public <T> T getMBeanProxy(ObjectName objectName, Class<T> clazz, boolean isNotificationBroadcaster) {
        return JMXHelper.getMBeanProxy(objectName, clazz, isNotificationBroadcaster, this.connection);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMBeanProxy(ObjectName objectName, String clazzName) {
        return (T) this.getMBeanProxy(objectName, clazzName, false);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMBeanProxy(ObjectName objectName, String clazzName, boolean isNotificationBroadcaster) {
        return (T) JMXHelper.getMBeanProxy(objectName, clazzName, isNotificationBroadcaster, this.connection);
    }

    public <T> T getMBeanProxy(String objectName, Class<T> clazz) {
        return this.getMBeanProxy(objectName, clazz, false);
    }

    public <T> T getMBeanProxy(String objectName, Class<T> clazz, boolean isNotificationBroadcaster) {
        return JMXHelper.getMBeanProxy(objectName, clazz, isNotificationBroadcaster, this.connection);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMBeanProxy(String objectName, String clazzName) {
        return (T) this.getMBeanProxy(objectName, clazzName, false);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMBeanProxy(String objectName, String clazzName, boolean isNotificationBroadcaster) {
        return (T) JMXHelper.getMBeanProxy(objectName, clazzName, isNotificationBroadcaster, this.connection);
    }

    // ********************************************************************************
    //
    // MBeans convenience methods
    //
    // ********************************************************************************

    public MBeanServerConnection getMBeanServerConnection() {
        return this.connection;
    }

    // ********************************************************************************
    //
    // Setters for dependencies injection
    //
    // ********************************************************************************

    public void setConnection(MBeanServerConnection connection) {
        this.connection = connection;
    }
}
