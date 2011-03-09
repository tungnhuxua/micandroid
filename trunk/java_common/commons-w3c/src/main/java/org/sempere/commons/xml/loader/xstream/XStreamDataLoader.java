/*
 * =============================================================================
 * Copyright by Benjamin Sempere,
 * All rights reserved.
 * =============================================================================
 * Author  : Benjamin Sempere
 * E-mail  : benjamin@sempere.org
 * Homepage: www.sempere.org
 * =============================================================================
 */
package org.sempere.commons.xml.loader.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang.CharEncoding;
import org.sempere.commons.xml.loader.AbstractDataLoader;
import org.sempere.commons.xml.loader.DataLoaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data loader implementation based on XStream API.
 *
 * @author bsempere
 */
public abstract class XStreamDataLoader<T> extends AbstractDataLoader<T> {

    private static Logger LOGGER = LoggerFactory.getLogger(XStreamDataLoader.class);

    protected XStream xstream;

    /**
     * Class constructor
     */
    public XStreamDataLoader() {
    }

    @Override
    public void initSerializer() throws DataLoaderException {
        LOGGER.info("Initializes XStream object");
        this.xstream = new XStream(new DomDriver(CharEncoding.UTF_8));
    }

    @Override
    public void addSerializationRules() throws DataLoaderException {
        LOGGER.info("Adds XStream rules");
        this.addXStreamRules();
    }

    @Override
    public String serialize(T data) throws DataLoaderException {
        if (data == null) {
            throw new IllegalArgumentException("Cannot serialize null data object.");
        }

        LOGGER.debug("Object to serialize: " + data);
        return xstream.toXML(data);
    }

    @Override
    public void initDeserializer() throws DataLoaderException {
        LOGGER.info("Initializes XStream object");
        this.xstream = new XStream(new DomDriver(CharEncoding.UTF_8));
    }

    @Override
    public void addDeserializationRules() throws DataLoaderException {
        LOGGER.info("Adds XStream rules");
        this.addXStreamRules();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(String data) throws DataLoaderException {
        if (data == null) {
            throw new IllegalArgumentException("Cannot deserialize null data string.");
        }

        LOGGER.debug("Data to deserialize: " + data);
        return (T) xstream.fromXML(data);
    }

    // ********************************************************************************
    //
    // Convenience methods
    //
    // ********************************************************************************

    public abstract void addXStreamRules() throws DataLoaderException;
}