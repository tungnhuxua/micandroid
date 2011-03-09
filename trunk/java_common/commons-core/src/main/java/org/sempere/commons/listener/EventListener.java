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

/**
 * Interface that defines a listener.
 *
 * @author bsempere
 */
public interface EventListener<T extends Event> {

    /**
     * Returns the name of this listener
     *
     * @return String
     */
    String getName();

    /**
     * Method called when an event is dispatched to this listener
     *
     * @param event the event the listener is listening on
     */
    void onEvent(T event);

    /**
     * Method called when an event is dispatched to this listener
     *
     * @param event   the event the listener is listening on
     * @param context an object that contains extra data
     */
    void onEvent(T event, Object context);
}
