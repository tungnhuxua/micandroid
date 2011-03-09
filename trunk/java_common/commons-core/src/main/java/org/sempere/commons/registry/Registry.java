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
package org.sempere.commons.registry;

/**
 * Interface that defines methods to register "Registrable" instances
 *
 * @author bsempere
 */
public interface Registry<T extends Registrable> extends Iterable<T> {

    /**
     * Returns the registry name
     */
    String getRegistryName();

    /**
     * Registers an object
     *
     * @param registrable the object to register
     */
    void register(T registrable);

    /**
     * Unregisters an object
     *
     * @param registrable the object to unregister
     */
    void unregister(T registrable);

    /**
     * Clear this registry (remove all its registered objects)
     */
    void clear();

    /**
     * Looks up an object
     *
     * @param objectName the name of the object to retrieve
     */
    T lookup(String objectName);
}