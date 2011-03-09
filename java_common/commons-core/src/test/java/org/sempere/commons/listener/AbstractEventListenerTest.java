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
package org.sempere.commons.listener;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests class for AbstractEventListener.
 *
 * @author bsempere
 */
public class AbstractEventListenerTest {

    private AbstractEventListener listener;

    @Before
    public void before() throws Exception {
        this.listener = mock(AbstractEventListener.class);
    }

    @Test
    public void getNameShouldReturnSimpleClassName() {
        when(this.listener.getName()).thenCallRealMethod();
        assertEquals(this.listener.getClass().getSimpleName(), this.listener.getName());
    }

    @Test
    public void onEventWithoutContextShouldCallOnEventWithContextSetToNull() {
        AbstractEvent event = mock(AbstractEvent.class);
        doCallRealMethod().when(this.listener).onEvent(event);
        
        this.listener.onEvent(event);
        verify(this.listener).onEvent(event, null);
    }
}
