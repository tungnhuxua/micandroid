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

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Unit tests class for AbstractRegistry class.
 *
 * @author bsempere
 */
public class AbstractRegistryTest {

    public AbstractRegistry<RegistrableStub> registry;

    @Before
    public void before() throws Exception {
        this.registry = new RegistryStub();
    }

    @Test
    public void iterator() throws Exception {
        RegistrableStub object = new RegistrableStub();
        this.registry.register(object);

        Iterator<RegistrableStub> it = this.registry.iterator();
        assertSame(object, it.next());
        assertFalse("No more object should be store in registry.", it.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerObjectWhenObjectIsNullShouldThrowException() throws Exception {
        this.registry.register(null);
    }

    @Test(expected = ObjectAlreadyExistsException.class)
    public void registerObjectWhenObjectIsAlreadyRegisteredShouldThrowException() throws Exception {
        RegistrableStub object = new RegistrableStub();
        this.registry.register(object);
        this.registry.register(object);
    }

    @Test
    public void registerObjectWhenObjectIsNotRegisteredShouldAddItToObjectsMap() throws Exception {
        RegistrableStub object = new RegistrableStub();

        this.registry.register(object);
        assertEquals(1, this.registry.objectsMap.size());
        assertTrue("Objects map should contain object with name [" + object.getRegistrationName() + "].", this.registry.objectsMap.containsKey(object.getRegistrationName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unregisterObjectWhenObjectIsNullShouldThrowException() throws Exception {
        this.registry.unregister(null);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void unregisterObjectWhenObjectIsNotRegisteredShouldThrowException() throws Exception {
        RegistrableStub object = new RegistrableStub();
        this.registry.unregister(object);
    }

    @Test
    public void unregisterObjectWhenObjectIsRegisteredShouldRemoveItFromObjectsMap() throws Exception {
        RegistrableStub object = new RegistrableStub();

        this.registry.register(object);
        this.registry.unregister(object);
        assertEquals(0, this.registry.objectsMap.size());
        assertFalse("Registrables map should not contain Registrable with name [" + object.getRegistrationName() + "].", this.registry.objectsMap.containsKey(object.getRegistrationName()));
    }

    @Test
    public void clearRegistryShouldClearObjectsMap() throws Exception {
        this.registry.register(new RegistrableStub());
        this.registry.clear();
        assertEquals(0, this.registry.objectsMap.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void lookupObjectWhenObjectNameIsNullShouldThrowException() throws Exception {
        this.registry.lookup(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lookupObjectWhenObjectNameIsEmptyShouldThrowException() throws Exception {
        this.registry.lookup(" ");
    }

    @Test(expected = ObjectNotFoundException.class)
    public void lookupObjectWhenObjectNameIsNotRegisteredShouldThrowException() throws Exception {
        this.registry.lookup("unregisteredName");
    }

    @Test
    public void lookupObjectWhenObjectNameIsRegistered() throws Exception {
        RegistrableStub expectedObject = new RegistrableStub();

        this.registry.register(expectedObject);
        Registrable actualObject = this.registry.lookup(expectedObject.getRegistrationName());
        assertSame(expectedObject, actualObject);
    }

    // ********************************************************************************
    //
    // Test fixtures, stubs, ...
    //
    // ********************************************************************************

    private class RegistryStub extends AbstractRegistry<RegistrableStub> {

        public String getRegistryName() {
            return "RegistryStub";
        }
    }

    private class RegistrableStub implements Registrable {

        public String getRegistrationName() {
            return "RegistrableStub";
        }
    }
}
