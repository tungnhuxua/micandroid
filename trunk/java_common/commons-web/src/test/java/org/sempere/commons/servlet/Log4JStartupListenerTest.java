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
package org.sempere.commons.servlet;

import org.junit.*;

import static org.mockito.Mockito.*;

/**
 * Unit tests class on Log4JStartupListener class.
 *
 * @author sempere
 */
public class Log4JStartupListenerTest {

    private Log4JStartupListener listener;

    @Before
    public void beforeTest() throws Exception {
        this.listener = mock(Log4JStartupListener.class);
    }

    @Test
    public void contextInitializedShouldCallInitializeLogger() {
        doCallRealMethod().when(this.listener).contextInitialized(null);
        this.listener.contextInitialized(null);
        verify(this.listener).initializeLogger();

    }

    @Test
    public void initializeLoggerWhenLoggerFileNameIsNull() {
        when(this.listener.getLoggerFileName()).thenReturn(null);
        doCallRealMethod().when(this.listener).initializeLogger();
        this.listener.initializeLogger();
    }
    
    @Test
    public void initializeLoggerWhenLoggerFileNameIsEmpty() {
        when(this.listener.getLoggerFileName()).thenReturn(" ");
        doCallRealMethod().when(this.listener).initializeLogger();
        this.listener.initializeLogger();
    }

    @Test
    public void initializeLoggerWhenLoggerFileNameCorrespondsToFileThatDoesNotExist() {
        when(this.listener.getLoggerFileName()).thenReturn("log4jStartupListener.unknown");
        doCallRealMethod().when(this.listener).initializeLogger();
        this.listener.initializeLogger();
    }

    @Test
    public void initializeLoggerWhenLoggerFileNameCorrespondsToPropertiesFile() {
        when(this.listener.getLoggerFileName()).thenReturn("log4jStartupListener.properties");
        doCallRealMethod().when(this.listener).initializeLogger();
        this.listener.initializeLogger();
    }

    @Test
    public void initializeLoggerWhenLoggerFileNameCorrespondsToXMLFile() {
        when(this.listener.getLoggerFileName()).thenReturn("log4jStartupListener.xml");
        doCallRealMethod().when(this.listener).initializeLogger();
        this.listener.initializeLogger();
    }
}
