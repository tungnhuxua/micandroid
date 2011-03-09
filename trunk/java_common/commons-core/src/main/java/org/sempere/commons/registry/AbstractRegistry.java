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

import org.apache.commons.lang.*;
import org.slf4j.*;

import java.util.*;

/**
 * Base class for any objects registry.
 *
 * @author bsempere
 */
public abstract class AbstractRegistry<T extends Registrable> implements Registry<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRegistry.class);
    protected Map<String, T> objectsMap = new HashMap<String, T>();

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************    

    public AbstractRegistry() {
    }

    // *************************************************************************
    //
    // Methods from Iterable interface 
    //
    // *************************************************************************

    public Iterator<T> iterator() {
        return this.objectsMap.values().iterator();
    }

    // *************************************************************************
    //
    // Methods from Registry interface 
    //
    // *************************************************************************

    public void register(T registrable) {
        if (registrable == null) {
            throw new IllegalArgumentException("Object to register cannot be null.");
        }
        if (this.objectsMap.containsKey(registrable.getRegistrationName())) {
            throw new ObjectAlreadyExistsException(this, registrable);
        }

        this.objectsMap.put(registrable.getRegistrationName(), registrable);
        LOGGER.debug("The object with name [" + registrable.getRegistrationName() + "] was successfully registered in the registry [" + this.getRegistryName() + "].");
    }

    public void unregister(T registrable) {
        if (registrable == null) {
            throw new IllegalArgumentException("Object to unregister cannot be null.");
        }
        if (!this.objectsMap.containsKey(registrable.getRegistrationName())) {
            throw new ObjectNotFoundException(this, registrable);
        }

        this.objectsMap.remove(registrable.getRegistrationName());
        LOGGER.debug("The object with name [" + registrable.getRegistrationName() + "] was successfully unregistered from the registry [" + this.getRegistryName() + "].");
    }

    public void clear() {
        this.objectsMap.clear();
        LOGGER.debug("The registry [" + this.getRegistryName() + "] was successfully cleared.");
    }

    public T lookup(String objectName) {
        if (StringUtils.isBlank(objectName)) {
            throw new IllegalArgumentException("Cannot lookup an object when its name is blank.");
        }
        if (!this.objectsMap.containsKey(objectName)) {
            throw new ObjectNotFoundException(this, objectName);
        }

        T locatee = this.objectsMap.get(objectName);
        LOGGER.debug("The object with name [" + objectName + "] was successfully retrieved from the registry [" + this.getRegistryName() + "].");

        return locatee;
    }
}