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

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import static javax.management.remote.JMXConnector.*;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Unit tests class for JMXHelper class.
 *
 * @author bsempere
 */
public class JMXHelperTest {

    private MBeanServerConnection connection;

    @Before
    public void before() throws Exception {
        this.connection = new JMXManagerImpl().getMBeanServerConnection();
    }

    // ********************************************************************************
    //
    // MBeans registration / un-registration
    //
    // ********************************************************************************

    @Test
    public void registerAndUnregisterMBeanWithStringAndClass() throws Exception {
        String objectName = "org.sempere.management.MyApplication:Name=MyMBean";

        JMXHelper.registerMBean(objectName, MySample.class, this.connection);
        assertEquals(objectName, JMXHelper.queryObjectName(objectName, connection).toString());

        JMXHelper.unregisterMBean(objectName, this.connection);
        assertNull("MBean should not exist anymore.", JMXHelper.queryObjectName(objectName, connection));
    }

    @Test
    public void registerAndUnregisterMBeanWithStringAndString() throws Exception {
        String objectName = "org.sempere.management.MyApplication:Name=MyMBean";

        JMXHelper.registerMBean(objectName, MySample.class.getName(), this.connection);
        assertEquals(objectName, JMXHelper.queryObjectName(objectName, connection).toString());

        JMXHelper.unregisterMBean(objectName, this.connection);
        assertNull("MBean should not exist anymore.", JMXHelper.queryObjectName(objectName, connection));
    }

    @Test
    public void registerAndUnregisterMBeanWithObjectNameAndClass() throws Exception {
        ObjectName objectName = JMXHelper.createObjectName("org.sempere.management.MyApplication:Name=MyMBean");

        JMXHelper.registerMBean(objectName, MySample.class, this.connection);
        assertEquals(objectName.toString(), JMXHelper.queryObjectName(objectName, connection).toString());

        JMXHelper.unregisterMBean(objectName, this.connection);
        assertNull("MBean should not exist anymore.", JMXHelper.queryObjectName(objectName, connection));
    }

    @Test
    public void registerAndUnregisterMBeanWithObjectNameAndString() throws Exception {
        ObjectName objectName = JMXHelper.createObjectName("org.sempere.management.MyApplication:Name=MyMBean");

        JMXHelper.registerMBean(objectName, MySample.class.getName(), this.connection);
        assertEquals(objectName.toString(), JMXHelper.queryObjectName(objectName, connection).toString());

        JMXHelper.unregisterMBean(objectName, this.connection);
        assertNull("MBean should not exist anymore.", JMXHelper.queryObjectName(objectName, connection));
    }

    // ********************************************************************************
    //
    // MBeans research
    //
    // ********************************************************************************

    @Test
    public void queryObjectNameWithStringAndClassWhenNoObjectNameIsFound() throws Exception {
        String objectName = "org.sempere.management.MyApplication:Name=MyMBean";
        String objectNamePattern = "com.sempere.management.MyApplication:Name=MyMBean";

        JMXHelper.registerMBean(objectName, MySample.class, this.connection);
        assertNull("MBean should not exist.", JMXHelper.queryObjectName(objectNamePattern, connection));
        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    @Test
    public void queryObjectNameWithStringAndClassWhenAnObjectNameIsFound() throws Exception {
        String objectName = "org.sempere.management.MyApplication:Name=MyMBean";
        String objectNamePattern = "org.sempere.management.MyApplication:*";

        JMXHelper.registerMBean(objectName, MySample.class, this.connection);
        assertEquals(objectName, JMXHelper.queryObjectName(objectNamePattern, connection).toString());
        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    @Test
    public void queryObjectNameWithObjectNameAndClassWhenNoObjectNameIsFound() throws Exception {
        ObjectName objectName = JMXHelper.createObjectName("org.sempere.management.MyApplication:Name=MyMBean");
        String objectNamePattern = "com.sempere.management.MyApplication:Name=MyMBean";

        JMXHelper.registerMBean(objectName, MySample.class, this.connection);
        assertNull("MBean should not exist.", JMXHelper.queryObjectName(objectNamePattern, connection));
        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    @Test
    public void queryObjectNameWithObjectNameAndClassWhenAnObjectNameIsFound() throws Exception {
        ObjectName objectName = JMXHelper.createObjectName("org.sempere.management.MyApplication:Name=MyMBean");
        String objectNamePattern = "org.sempere.management.MyApplication:Name=MyMBean,*";

        JMXHelper.registerMBean(objectName, MySample.class, this.connection);
        assertEquals(objectName.toString(), JMXHelper.queryObjectName(objectNamePattern, connection).toString());
        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    @Test
    public void queryObjectNamesWithStringAndClassWhenNoObjectNameIsFound() throws Exception {
        String objectName1 = "org.sempere.management.MyApplication:Name=MyMBean1";
        String objectName2 = "org.sempere.management.MyApplication:Name=MyMBean2";
        String objectNamePattern = "com.sempere.management.MyApplication:Name=MyMBean";

        JMXHelper.registerMBean(objectName1, MySample.class, this.connection);
        JMXHelper.registerMBean(objectName2, MySample.class, this.connection);
        assertTrue("MBeans list should be empty.", JMXHelper.queryObjectNames(objectNamePattern, connection).isEmpty());
        JMXHelper.unregisterMBean(objectName1, this.connection);
        JMXHelper.unregisterMBean(objectName2, this.connection);
    }

    @Test
    public void queryObjectNamesWithStringAndClassWhenObjectNamesAreFound() throws Exception {
        String objectName1 = "org.sempere.management.MyApplication:Name=MyMBean1";
        String objectName2 = "org.sempere.management.MyApplication:Name=MyMBean2";
        String objectNamePattern = "org.sempere.management.MyApplication:*";

        JMXHelper.registerMBean(objectName1, MySample.class, this.connection);
        JMXHelper.registerMBean(objectName2, MySample.class, this.connection);

        Collection<ObjectName> beans = JMXHelper.queryObjectNames(objectNamePattern, connection);
        assertEquals(2, beans.size());

        JMXHelper.unregisterMBean(objectName1, this.connection);
        JMXHelper.unregisterMBean(objectName2, this.connection);
    }


    @Test
    public void queryObjectNamesWithObjectNameAndClassWhenNoObjectNameIsFound() throws Exception {
        String objectName1 = "org.sempere.management.MyApplication:Name=MyMBean1";
        String objectName2 = "org.sempere.management.MyApplication:Name=MyMBean2";
        ObjectName objectNamePattern = JMXHelper.createObjectName("com.sempere.management.MyApplication:Name=MyMBean");

        JMXHelper.registerMBean(objectName1, MySample.class, this.connection);
        JMXHelper.registerMBean(objectName2, MySample.class, this.connection);
        assertTrue("MBeans list should be empty.", JMXHelper.queryObjectNames(objectNamePattern, connection).isEmpty());
        JMXHelper.unregisterMBean(objectName1, this.connection);
        JMXHelper.unregisterMBean(objectName2, this.connection);
    }

    @Test
    public void queryObjectNamesWithObjectNameAndClassWhenObjectNamesAreFound() throws Exception {
        String objectName1 = "org.sempere.management.MyApplication:Name=MyMBean1";
        String objectName2 = "org.sempere.management.MyApplication:Name=MyMBean2";
        ObjectName objectNamePattern = JMXHelper.createObjectName("org.sempere.management.MyApplication:*");

        JMXHelper.registerMBean(objectName1, MySample.class, this.connection);
        JMXHelper.registerMBean(objectName2, MySample.class, this.connection);

        Collection<ObjectName> beans = JMXHelper.queryObjectNames(objectNamePattern, connection);
        assertEquals(2, beans.size());

        JMXHelper.unregisterMBean(objectName1, this.connection);
        JMXHelper.unregisterMBean(objectName2, this.connection);
    }

    // ********************************************************************************
    //
    // MBeans proxies
    //
    // ********************************************************************************

    @Test
    public void getMBeanProxyWithStringAndClass() throws Exception {
        String objectName = "org.sempere.management.MyApplication:Name=MyMBean";
        JMXHelper.registerMBean(objectName, MySample.class, this.connection);

        String expectedMessage = "hello";
        MySampleMBean bean = JMXHelper.getMBeanProxy(objectName, MySampleMBean.class, false, this.connection);
        bean.setMessage(expectedMessage);
        assertEquals(expectedMessage, bean.getMessage());

        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    @Test
    public void getMBeanProxyWithStringAndString() throws Exception {
        String objectName = "org.sempere.management.MyApplication:Name=MyMBean";
        JMXHelper.registerMBean(objectName, MySample.class, this.connection);

        String expectedMessage = "hello";
        MySampleMBean bean = JMXHelper.getMBeanProxy(objectName, MySampleMBean.class.getName(), false, this.connection);
        bean.setMessage(expectedMessage);
        assertEquals(expectedMessage, bean.getMessage());

        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    @Test
    public void getMBeanProxyWithObjectNameAndClass() throws Exception {
        ObjectName objectName = JMXHelper.createObjectName("org.sempere.management.MyApplication:Name=MyMBean");
        JMXHelper.registerMBean(objectName, MySample.class, this.connection);

        String expectedMessage = "hello";
        MySampleMBean bean = JMXHelper.getMBeanProxy(objectName, MySampleMBean.class, false, this.connection);
        bean.setMessage(expectedMessage);
        assertEquals(expectedMessage, bean.getMessage());

        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    @Test
    public void getMBeanProxyWithObjectNameAndString() throws Exception {
        ObjectName objectName = JMXHelper.createObjectName("org.sempere.management.MyApplication:Name=MyMBean");
        JMXHelper.registerMBean(objectName, MySample.class, this.connection);

        String expectedMessage = "hello";
        MySampleMBean bean = JMXHelper.getMBeanProxy(objectName, MySampleMBean.class.getName(), false, this.connection);
        bean.setMessage(expectedMessage);
        assertEquals(expectedMessage, bean.getMessage());

        JMXHelper.unregisterMBean(objectName, this.connection);
    }

    // ********************************************************************************
    //
    // MBeans convenience methods
    //
    // ********************************************************************************

    @Test(expected = JMXException.class)
    public void createObjectNameWhenObjectNameIsNull() throws Exception {
        JMXHelper.createObjectName(null);
    }

    @Test(expected = JMXException.class)
    public void createObjectNameWhenObjectNameIsMalFormed() throws Exception {
        JMXHelper.createObjectName("bad/format");
    }

    @Test
    public void createObjectNameWhenObjectNameIsValid() throws Exception {
        String expectedName = "org.sempere.management.MyApplicationName:Name=MyName,Type=MyType";
        assertEquals(expectedName, JMXHelper.createObjectName(expectedName).toString());
    }

    @Test(expected = JMXException.class)
    public void createServiceUrlFromUrlWhenUrlIsMalFormed() throws Exception {
        JMXHelper.createServiceURL("/jmxrmi");
    }

    @Test
    public void createServiceUrlFromUrlWhenUrlIsValid() throws Exception {
        JMXServiceURL serviceURL = JMXHelper.createServiceURL("service:jmx:rmi://localhost:8686/jmxrmi");
        assertEquals("rmi", serviceURL.getProtocol());
        assertEquals("localhost", serviceURL.getHost());
        assertEquals(8686, serviceURL.getPort());
        assertEquals("/jmxrmi", serviceURL.getURLPath());
    }

    @Test(expected = JMXException.class)
    public void createServiceUrlWhenParametersAreInvalid() throws Exception {
        JMXHelper.createServiceURL("", "localhost", 8686, "/jmxrmi");
    }

    @Test
    public void createServiceUrlWhenParametersAreValid() throws Exception {
        String protocol = "rmi";
        String host = "localhost";
        int port = 8686;
        String urlPath = "/jmxrmi";

        JMXServiceURL serviceURL = JMXHelper.createServiceURL(protocol, host, port, urlPath);
        assertEquals(protocol, serviceURL.getProtocol());
        assertEquals(host, serviceURL.getHost());
        assertEquals(port, serviceURL.getPort());
        assertEquals(urlPath, serviceURL.getURLPath());
    }

    @Test(expected = JMXException.class)
    public void createConnectorWhenIOExceptionOccurs() throws Exception {
        JMXServiceURL serviceURL = JMXHelper.createServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8686/jmxrmi");
        JMXHelper.createConnector(serviceURL, null, true);
    }

    @Test
    public void createConnectorWhenNoExceptionOccurs() throws Exception {
        JMXServiceURL serviceURL = JMXHelper.createServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8686/jmxrmi");
        JMXHelper.createConnector(serviceURL, null, false);
    }

    @Test
    public void createConnectorEnvironment() throws Exception {
        Map<String, ?> environment = JMXHelper.createConnectorEnvironment("user", "password");
        assertEquals(1, environment.size());

        String[] credentials = (String[]) environment.get(CREDENTIALS);
        assertEquals(2, credentials.length);
        assertEquals("user", credentials[0]);
        assertEquals("password", credentials[1]);
    }

    @Test(expected = JMXException.class)
    public void getConnectionIdWhenIOExceptionOccurs() throws Exception {
        JMXConnector connector = mock(JMXConnector.class);
        doThrow(new IOException("Exception encountered.")).when(connector).getConnectionId();

        JMXHelper.getConnectionId(connector);
    }

    @Test
    public void getConnectionIdWhenNoExceptionOccurs() throws Exception {
        JMXConnector connector = mock(JMXConnector.class);
        String connectionId = "123456789";
        when(connector.getConnectionId()).thenReturn(connectionId);

        assertEquals(connectionId, JMXHelper.getConnectionId(connector));
    }

    @Test(expected = JMXException.class)
    public void getMBeanServerConnectionWhenIOExceptionOccurs() throws Exception {
        JMXConnector connector = mock(JMXConnector.class);
        doThrow(new IOException("Exception encountered.")).when(connector).getMBeanServerConnection();

        JMXHelper.getMBeanServerConnection(connector);
    }

    @Test
    public void getMBeanServerConnectionWhenNoExceptionOccurs() throws Exception {
        JMXConnector connector = mock(JMXConnector.class);
        MBeanServerConnection connection = mock(MBeanServerConnection.class);
        when(connector.getMBeanServerConnection()).thenReturn(connection);

        assertEquals(connection, JMXHelper.getMBeanServerConnection(connector));
    }
}