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
package org.sempere.commons.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests class for SingletonContextLoader class.
 *
 * @author bsempere
 */
public class SingletonContextLoaderTest {

    public static String[] CONFIG_FILES = {"/org/sempere/commons/spring/spring.xml"};

    @Before
    public void before() throws Exception {
        SingletonContextLoader.getInstance().releaseContext();
    }

    @Test
    public void loadContextWhenContextIsAlreadyLoaded() throws Exception {
        ClassPathXmlApplicationContext context = mock(ClassPathXmlApplicationContext.class);
        SingletonContextLoader.getInstance().context = context;
        SingletonContextLoader.getInstance().contextLoaded = true;

        SingletonContextLoader.getInstance().loadContext(null);

        assertEquals(context, SingletonContextLoader.getInstance().context);
        assertTrue("Context should be loaded.", SingletonContextLoader.getInstance().contextLoaded);
    }

    @Test
    public void loadContextWhenContextIsNotLoaded() throws Exception {
        SingletonContextLoader.getInstance().loadContext(CONFIG_FILES);

        assertNotNull("Context should not be null.", SingletonContextLoader.getInstance().context);
        assertTrue("Context should be loaded.", SingletonContextLoader.getInstance().contextLoaded);
    }

    @Test
    public void releaseContextWhenContextIsAlreadyReleased() throws Exception {
        SingletonContextLoader.getInstance().releaseContext();

        assertNull("Context should be null.", SingletonContextLoader.getInstance().context);
        assertFalse("Context should not be loaded.", SingletonContextLoader.getInstance().contextLoaded);
    }

    @Test
    public void releaseContextWhenContextIsNotReleased() throws Exception {
        SingletonContextLoader.getInstance().context = mock(ClassPathXmlApplicationContext.class);
        ;
        SingletonContextLoader.getInstance().contextLoaded = true;

        SingletonContextLoader.getInstance().releaseContext();

        assertNull("Context should be null.", SingletonContextLoader.getInstance().context);
        assertFalse("Context should not be loaded.", SingletonContextLoader.getInstance().contextLoaded);
    }
}
