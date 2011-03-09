package org.sempere.commons.base;

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


import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Unit tests class for ReflectionHelper class.
 *
 * @author bsempere
 */
public class ReflectionHelperTest {

    @Test
    public void newInstanceWithParametersWhenClassExists() throws Exception {
        String myString = ReflectionHelper.newInstance(String.class.getName(), new Class[]{String.class}, "myText");
        assertEquals("myText", myString);
    }

    @Test(expected = RuntimeException.class)
    public void newInstanceWithParametersWhenClassDoesNotExist() throws Exception {
        ReflectionHelper.newInstance("org.sempere.commons.reflection.unknown", new Class[]{}, new Object[]{});
    }

    @Test
    public void newInstanceWithoutParametersWhenClassExists() throws Exception {
        String myString = ReflectionHelper.newInstance(String.class.getName());
        assertEquals("", myString);
    }

    @Test(expected = RuntimeException.class)
    public void newInstanceWithoutParametersWhenClassDoesNotExist() throws Exception {
        ReflectionHelper.newInstance("org.sempere.commons.reflection.unknown");
    }

    @Test(expected = RuntimeException.class)
    public void loadClassWhenClassDoesNotExist() throws Exception {
        ReflectionHelper.loadClass("org.sempere.commons.reflection.unknown");
    }

    @Test
    public void loadClassWhenClassExists() throws Exception {
        Class<String> myStringClass = ReflectionHelper.loadClass(String.class.getName());
        assertEquals(String.class.getName(), myStringClass.getName());
    }

    @Test
    public void existsWhenClassNameDoesNotExist() throws Exception {
        assertFalse(ReflectionHelper.exists("org.sempere.commons.reflection.unknown"));
    }

    @Test
    public void existsWhenClassNameExists() throws Exception {
        assertTrue(ReflectionHelper.exists(this.getClass().getName()));
    }

    @Test
    public void isSubClassWhenChildClassIsNull() throws Exception {
        boolean res = false;

        res = ReflectionHelper.isSubClass(null, Object.class);
        assertFalse(res);
    }

    @Test
    public void isSubClassWhenParentClassIsNull() throws Exception {
        boolean res = false;

        res = ReflectionHelper.isSubClass(Calendar.class, null);
        assertFalse(res);
    }

    @Test
    public void isSubClass() throws Exception {
        assertTrue(ReflectionHelper.isSubClass(GregorianCalendar.class, Object.class));
    }

    @Test
    public void isNotSubClass() throws Exception {
        assertFalse(ReflectionHelper.isSubClass(Calendar.class, GregorianCalendar.class));
    }

    @Test
    public void invokeMethodWithMethodNameWhenExceptionOccursShouldReturnNull() throws Exception {
        assertNull("Invocation result should be null.", ReflectionHelper.invokeMethod("invokeMethodWithMethodNameWhenExceptionOccursShouldReturnNull", "Hello"));
    }

    @Test
    public void invokeMethodWithMethodNameWhenNoExceptionOccurs() throws Exception {
        assertEquals("Hello", ReflectionHelper.invokeMethod("toString", "Hello"));
    }

    @Test
    public void invokeMethodWhenExceptionOccursShouldReturnNull() throws Exception {
        assertNull("Invocation result should be null.", ReflectionHelper.invokeMethod(this.getClass().getMethod("invokeMethodWhenExceptionOccursShouldReturnNull"), "Hello"));
    }

    @Test
    public void invokeMethodWhenNoExceptionOccurs() throws Exception {
        assertEquals("Hello", ReflectionHelper.invokeMethod(String.class.getMethod("toString"), "Hello"));
    }
}
