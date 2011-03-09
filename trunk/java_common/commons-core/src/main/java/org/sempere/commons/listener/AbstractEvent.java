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

import org.slf4j.*;

import java.util.*;

/**
 * Base implementation of Event.
 *
 * @author bsempere
 */
public abstract class AbstractEvent<T extends EventListener> implements Event<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEvent.class);
    private Map<String, T> listeners = new HashMap<String, T>();

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public AbstractEvent() {
    }

    // *************************************************************************
    //
    // Methods from Event interface 
    //
    // *************************************************************************

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public void registerListener(T listener) {
        if (listener != null) {
            LOGGER.debug("About to register listener with name [" + listener.getName() + "].");
            this.listeners.put(listener.getName(), listener);
            LOGGER.debug("Listener with name [" + listener.getName() + "] successfully registered.");
        } else {
            LOGGER.debug("No listener to register because listener is null.");

        }
    }

    public void registerListeners(Collection<T> listeners) {
        if (listeners != null) {
            for (T listener : listeners) {
                this.registerListener(listener);
            }
        }
    }

    public void unregisterListener(T listener) {
        if (listener != null) {
            LOGGER.debug("About to unregister listener with name [" + listener.getName() + "].");
            this.listeners.remove(listener.getName());
            LOGGER.debug("Listener with name [" + listener.getName() + "] successfully unregistered.");
        } else {
            LOGGER.debug("No listener to unregister because listener is null.");
        }
    }

    public void unregisterListeners(Collection<T> listeners) {
        if (listeners != null) {
            for (T listener : listeners) {
                this.unregisterListener(listener);
            }
        }
    }

    public void unregisterAllListeners() {
        LOGGER.debug("About to unregister all listeners.");
        this.listeners.clear();
        LOGGER.debug("All listeners unregistered.");
    }

    public void notifyListeners() {
        for (Map.Entry<String, T> entry : listeners.entrySet()) {
            T listener = entry.getValue();

            LOGGER.debug("About to notify listener with name [" + listener.getName() + "].");
            listener.onEvent(this);
            LOGGER.debug("Listener with name [" + listener.getName() + "] notified successfully.");
        }
    }

    public void notifyListeners(Object context) {
        for (Map.Entry<String, T> entry : listeners.entrySet()) {
            T listener = entry.getValue();

            LOGGER.debug("About to notify listener with name [" + listener.getName() + "].");
            listener.onEvent(this, context);
            LOGGER.debug("Listener with name [" + listener.getName() + "] notified successfully.");
        }
    }
}
