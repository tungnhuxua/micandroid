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
 * Exception class that is thrown when an object does not exist in a registry.
 *
 * @author bsempere
 */
public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -968460130531579220L;

    public <T extends Registrable> ObjectNotFoundException(Registry<T> registry, String objectName) {
        super("No object with name [" + objectName + "] is registered in the registry [" + registry.getRegistryName() + "].");
    }

    public <T extends Registrable> ObjectNotFoundException(Registry<T> registry, Registrable registrable) {
        this(registry, registrable.getRegistrationName());
    }
}