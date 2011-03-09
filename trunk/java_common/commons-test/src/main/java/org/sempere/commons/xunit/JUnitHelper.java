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
package org.sempere.commons.xunit;

import org.hibernate.*;
import org.junit.*;

import java.lang.reflect.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Helper class that provides methods to work with JUnit.
 *
 * @author bsempere
 */
public final class JUnitHelper {

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private JUnitHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public static void assertLazy(Object object) {
        try {
            object.toString();
            fail("Object should be a lazy object.");
        } catch (LazyInitializationException e) {
            // Nothing to do here
        }
    }

    public static void assertNull(String objectName, Object object) {
        Assert.assertNull("[" + objectName + "] should be null.", object);
    }

    public static void assertNotNull(String objectName, Object object) {
        Assert.assertNotNull("[" + objectName + "] should not be null.", object);
    }

    public static void assertEmpty(Collection<?> collection) {
        Assert.assertEquals("Collection should be empty.", 0, collection.size());
    }

    public static void assertNotEmpty(Collection<?> collection) {
        Assert.assertFalse("Collection should not be empty.", collection.isEmpty());
    }

    public static void assertEmpty(Object... elements) {
        Assert.assertEquals("Collection should be empty.", 0, elements.length);
    }

    public static void assertNotEmpty(Object... elements) {
        Assert.assertTrue("Collection should not be empty.", elements.length > 0);
    }

    public static void assertSizeEquals(int expectedSize, Collection<?> collection) {
        Assert.assertEquals("Collection size should be equal to [" + expectedSize + "].", expectedSize, collection.size());
    }

    public static void assertSizeEquals(int expectedSize, Object... elements) {
        Assert.assertEquals("Collection size should be equal to [" + expectedSize + "].", expectedSize, elements.length);
    }

    public static void assertProxy(Class<?> clazz) {
        assertTrue("Class [" + clazz + "] should be a proxy.", Proxy.isProxyClass(clazz));
    }

    public static void assertNotProxy(Class<?> clazz) {
        assertFalse("Class [" + clazz + "] should not be a proxy.", Proxy.isProxyClass(clazz));
    }

    public static void assertProxy(Object object) {
        assertProxy(object.getClass());
    }

    public static void assertNotProxy(Object object) {
        assertNotProxy(object.getClass());
    }
}
