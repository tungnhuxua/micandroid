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
package org.sempere.commons.base;

import org.junit.*;
import org.sempere.commons.checker.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Unit tests class for CollectionHelper.
 *
 * @author bsempere
 */
public class CollectionHelperTest {

    @Test
    public void complete() throws Exception {
        List<String> values = new ArrayList<String>(Arrays.asList("1", "2", "3"));
        CollectionHelper.complete(values, "N/A", 5);
        assertEquals(5, values.size());
        assertEquals("1", values.get(0));
        assertEquals("2", values.get(1));
        assertEquals("3", values.get(2));
        assertEquals("N/A", values.get(3));
        assertEquals("N/A", values.get(4));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getMaxSize() throws Exception {
        List<String> values1 = Arrays.asList("1", "2", "3");
        List<String> values2 = null;
        List<String> values3 = Arrays.asList("1", "2", "3", "4", "5");
        assertEquals(5, CollectionHelper.getMaxSize(values1, values2, values3));
    }

    @Test
    public void findAllWhenEmptyResult() {
        List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c"));
        Collection<String> filtered = CollectionHelper.findAll(list, new Checker<String>() {

            public boolean check(String obj) {
                return "z".equals(obj);
            }
        });
        assertEquals(0, filtered.size());
    }

    @Test
    public void findAll() {
        List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c"));
        Collection<String> filtered = CollectionHelper.findAll(list, new Checker<String>() {

            public boolean check(String obj) {
                return "a".equals(obj);
            }
        });
        assertEquals(1, filtered.size());
        assertEquals("a", filtered.iterator().next());
    }
}
