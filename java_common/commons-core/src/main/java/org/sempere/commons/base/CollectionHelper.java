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

import org.sempere.commons.checker.*;

import java.util.*;

/**
 * Helper class for dealing with java collections.
 *
 * @author bsempere
 */
public final class CollectionHelper {

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private CollectionHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public static <T> void complete(Collection<T> collection, T completor, int expectedSize) {
        if (collection.size() < expectedSize) {
            int sizeDiff = expectedSize - collection.size();
            for (int i = 0; i < sizeDiff; i++) {
                collection.add(completor);
            }
        }
    }

    public static <T> int getMaxSize(Collection<T>... collections) {
        int maxSize = 0;
        for (Collection<T> collection : collections) {
            if (collection != null) {
                maxSize = Math.max(maxSize, collection.size());
            }
        }
        return maxSize;
    }

    public static <T> Collection<T> findAll(Collection<T> collection, Checker<T> checker) {
        Collection<T> filteredCollection = new ArrayList<T>();
        for (T obj : collection) {
            if (checker.check(obj)) {
                filteredCollection.add(obj);
            }
        }
        return filteredCollection;
    }
}
