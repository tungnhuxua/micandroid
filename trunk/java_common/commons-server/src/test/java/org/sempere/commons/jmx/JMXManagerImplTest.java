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
import java.lang.management.ManagementFactory;

/**
 * Unit tests class for JMXManagerImpl class.
 *
 * @author bsempere
 */
public class JMXManagerImplTest {

    private JMXManagerImpl manager;

    @Before
    public void before() throws Exception {
        this.manager = new JMXManagerImpl();
    }

    // ********************************************************************************
    //
    // Constructors
    //
    // ********************************************************************************

    @Test
    public void newInstanceWithoutParameter() throws Exception {
        JMXManagerImpl instance = new JMXManagerImpl();
        assertEquals(ManagementFactory.getPlatformMBeanServer(), instance.getMBeanServerConnection());
    }

    // ********************************************************************************
    //
    // MBeans convenience methods
    //
    // ********************************************************************************

    @Test
    public void setMBeanServerConnection() throws Exception {
        MBeanServerConnection connection = mock(MBeanServerConnection.class);

        this.manager.setConnection(connection);
        assertSame(connection, this.manager.getMBeanServerConnection());
    }
}