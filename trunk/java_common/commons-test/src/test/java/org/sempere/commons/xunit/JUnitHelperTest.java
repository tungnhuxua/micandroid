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
import org.mockito.*;
import org.sempere.commons.proxy.*;

import java.util.*;

/**
 * Unit tests class for JUnitHelper.
 *
 * @author bsempere
 */
public class JUnitHelperTest {

    @Test(expected = AssertionError.class)
    public void assertLazyWhenObjectIsNonLazyShouldFail() {
        JUnitHelper.assertLazy("MyNonLazyObject");
    }

    @Test
    public void assertLazyWhenObjectIsLazyShouldPass() {
        Object myLazyObject = Mockito.mock(Object.class);
        Mockito.when(myLazyObject.toString()).thenThrow(new LazyInitializationException("An error has occured."));

        JUnitHelper.assertLazy(myLazyObject);
    }

    @Test
    public void assertNullWhenObjectIsNullShouldPass() {
        JUnitHelper.assertNull("My Object", null);
    }

    @Test(expected = AssertionError.class)
    public void assertNullWhenObjectIsNotNullShouldFail() {
        JUnitHelper.assertNull("My Object", "MyString");
    }

    @Test
    public void assertEmptyWithCollectionWhenCollectionIsEmptyShouldPass() {
        JUnitHelper.assertEmpty(new ArrayList<String>());
    }

    @Test(expected = AssertionError.class)
    public void assertEmptyWithCollectionWhenCollectionIsNotEmptyShouldFail() {
        JUnitHelper.assertEmpty(Arrays.asList("MyObject1"));
    }

    @Test
    public void assertNotEmptyWithCollectionWhenCollectionIsNotEmptyShouldPass() {
        JUnitHelper.assertNotEmpty(Arrays.asList("MyObject1"));
    }

    @Test(expected = AssertionError.class)
    public void assertNotEmptyWithCollectionWhenCollectionIsEmptyShouldFail() {
        JUnitHelper.assertNotEmpty(new ArrayList<String>());
    }

    @Test
    public void assertEmptyWithArrayWhenArrayIsEmptyShouldPass() {
        JUnitHelper.assertEmpty();
    }

    @Test(expected = AssertionError.class)
    public void assertNotEmptyWithArrayWhenArrayIsEmptyShouldFail() {
        JUnitHelper.assertNotEmpty();
    }

    @Test(expected = AssertionError.class)
    public void assertEmptyWithArrayWhenArrayIsNotEmptyShouldFail() {
        JUnitHelper.assertEmpty("MyObject1");
    }

    @Test
    public void assertNotEmptyWithArrayWhenArrayIsNotEmptyShouldPass() {
        JUnitHelper.assertNotEmpty("MyObject1");
    }

    @Test
    public void assertSizeEqualsWithCollectionWhenCollectionSizeIsEqualToExpectedSizeShouldPass() {
        JUnitHelper.assertSizeEquals(2, Arrays.asList("MyObject1", "MyObject2"));
    }

    @Test(expected = AssertionError.class)
    public void assertSizeEqualsWithCollectionWhenCollectionSizeIsNotEqualToExpectedSizeShouldFail() {
        JUnitHelper.assertSizeEquals(3, Arrays.asList("MyObject1", "MyObject2"));
    }

    @Test
    public void assertSizeEqualsWithArrayWhenArrayLenghtIsEqualToExpectedSizeShouldPass() {
        JUnitHelper.assertSizeEquals(2, "MyObject1", "MyObject2");
    }

    @Test(expected = AssertionError.class)
    public void assertSizeEqualsWithArrayWhenArrayLenghtIsNotEqualToExpectedSizeShouldFail() {
        JUnitHelper.assertSizeEquals(3, "MyObject1", "MyObject2");
    }

    @Test
    public void assertNotNullWhenObjectIsNotNullShouldPass() {
        JUnitHelper.assertNotNull("My Object", "MyString");
    }

    @Test(expected = AssertionError.class)
    public void assertNotNullWhenObjectIsNullShouldFail() {
        JUnitHelper.assertNotNull("My Object", null);
    }

    @Test(expected = AssertionError.class)
    public void assertProxyWhenClassIsNotAProxyShouldFail() {
        JUnitHelper.assertProxy(String.class);
    }

    @Test
    public void assertProxyWhenClassIsAProxyShouldPass() {
        JUnitHelper.assertProxy(ProxyHelper.createProxy(new DelegateHandler<String>("MyString")).getClass());
    }

    @Test(expected = AssertionError.class)
    public void assertNotProxyWhenClassIsAProxyShouldFail() {
        JUnitHelper.assertNotProxy(ProxyHelper.createProxy(new DelegateHandler<String>("MyString")).getClass());
    }

    @Test
    public void assertNotProxyWhenClassIsNotAProxyShouldPass() {
        JUnitHelper.assertNotProxy(String.class);
    }

    @Test(expected = AssertionError.class)
    public void assertProxyWhenInstanceIsNotAProxyShouldFail() {
        JUnitHelper.assertProxy("MyString");
    }

    @Test
    public void assertProxyWhenInstanceIsAProxyShouldPass() {
        JUnitHelper.assertProxy(ProxyHelper.createProxy(new DelegateHandler<String>("MyString")));
    }

    @Test(expected = AssertionError.class)
    public void assertNotProxyWhenInstanceIsAProxyShouldFail() {
        JUnitHelper.assertNotProxy(ProxyHelper.createProxy(new DelegateHandler<String>("MyString")).getClass());
    }

    @Test
    public void assertNotProxyWhenInstanceIsNotAProxyShouldPass() {
        JUnitHelper.assertNotProxy("MyString");
    }
}
