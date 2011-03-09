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

import java.util.*;

/**
 * Interface that defines an event.
 *
 * @author bsempere
 */
public interface Event<T extends EventListener> {

    /**
     * Returns the name of this event
     *
     * @return String
     */
    String getName();

    /**
     * Registers a listener to this event
     *
     * @param listener the listener to be registered
     */
    void registerListener(T listener);

    /**
     * Registers a collection of listeners to this event
     *
     * @param listeners the listeners to be registered
     */
    void registerListeners(Collection<T> listeners);

    /**
     * Unregisters a listener to this event
     *
     * @param listener the listener to be unregistered
     */
    void unregisterListener(T listener);

    /**
     * Unregisters a collection of listeners to this event
     *
     * @param listeners the listeners to be unregistered
     */
    void unregisterListeners(Collection<T> listeners);

    /**
     * Unregisters all the listeners of this event
     */
    void unregisterAllListeners();

    /**
     * Notifies all the listeners of this event
     */
    void notifyListeners();

    /**
     * Notifies all the listeners of this event with the given data
     *
     * @param context an object that contains additional data of this event
     */
    void notifyListeners(Object context);
}
