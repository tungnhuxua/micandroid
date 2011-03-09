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
package org.sempere.commons.xml.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for any DataLoader.
 *
 * @author bsempere
 */
public abstract class AbstractDataLoader<T> implements DataLoader<T> {

    private static Logger LOGGER = LoggerFactory.getLogger(AbstractDataLoader.class);

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public AbstractDataLoader() {
    }

    // *************************************************************************
    //
    // Methods from DataLoader interface
    //
    // *************************************************************************

    public final String serializeData(T data) throws DataLoaderException {
        LOGGER.debug("About to serialize data: " + data);

        LOGGER.info("Serializer initialization");
        this.initSerializer();

        LOGGER.info("Adds some serialization rules to the serializer");
        this.addSerializationRules();

        LOGGER.info("Serializes the data");
        return this.serialize(data);
    }

    public final T deserializeData(String data) throws DataLoaderException {
        LOGGER.debug("About to deserialize data: " + data);

        LOGGER.info("Deserializer initialization");
        this.initDeserializer();

        LOGGER.info("Adds some deserialization rules to the deserializer");
        this.addDeserializationRules();

        LOGGER.info("Deserializes the data");
        return this.deserialize(data);
    }

    // ********************************************************************************
    //
    // Abstract methods
    //
    // ********************************************************************************

    /**
     * Initializes the serializer
     */
    public abstract void initSerializer() throws DataLoaderException;

    /**
     * Serializes the given data
     *
     * @param data the data to serialize
     * @return String
     */
    public abstract String serialize(T data) throws DataLoaderException;

    /**
     * Adds some serialization rules like converters...
     */
    public abstract void addSerializationRules() throws DataLoaderException;

    /**
     * Initializes the deserializer
     */
    public abstract void initDeserializer() throws DataLoaderException;

    /**
     * Deserializes the given data
     *
     * @param data the data to deserialize
     * @return T
     */
    public abstract T deserialize(String data) throws DataLoaderException;

    /**
     * Adds some deserialization rules like converters...
     */
    public abstract void addDeserializationRules() throws DataLoaderException;
}