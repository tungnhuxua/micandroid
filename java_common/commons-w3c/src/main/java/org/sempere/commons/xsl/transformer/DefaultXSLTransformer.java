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
package org.sempere.commons.xsl.transformer;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;

/**
 * XSL transformer implementation based on classes provided by the JDK.
 *
 * @author bsempere
 */
public class DefaultXSLTransformer implements XSLTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultXSLTransformer.class);

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public DefaultXSLTransformer() {
    }

    // *************************************************************************
    //
    // Methods from XSLTransformer interface
    //
    // *************************************************************************

    public String transform(Transformable transformable) {
        if (transformable == null) {
            throw new IllegalArgumentException("Cannot transform null transformable.");
        }
        if (StringUtils.isBlank(transformable.getXslContent())) {
            throw new IllegalArgumentException("XSL content cannot be blank.");
        }
        if (StringUtils.isBlank(transformable.getXmlContent())) {
            throw new IllegalArgumentException("XML content cannot be blank.");
        }

        String output = null;
        try {
            // If no encoding is provided, we use the UTF8 encoding
            String encoding = transformable.getEncoding();
            if (StringUtils.isBlank(encoding)) {
                encoding = CharEncoding.UTF_8;
            }

            String xslContent = transformable.getXslContent();
            LOGGER.debug("XSL content is: " + xslContent);

            String xmlContent = transformable.getXmlContent();
            LOGGER.debug("XML content is: " + xmlContent);

            Reader xslReader = new StringReader(transformable.getXslContent());
            Reader xmlReader = new StringReader(transformable.getXmlContent());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Creates a transformer factory
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            // Registers the XSL into the factory
            StreamSource xslSource = new StreamSource(xslReader);
            Templates templates = transformerFactory.newTemplates(xslSource);

            // Creates a transformer instance with the transformable encoding
            Transformer transformer = templates.newTransformer();
            if (!StringUtils.isEmpty(transformable.getEncoding())) {
                transformer.setOutputProperty(OutputKeys.ENCODING, transformable.getEncoding());
            }

            // Set registered parameters to transformer
            Collection<Parameter> parameters = transformable.getParameters();
            if (parameters != null) {
                for (Parameter parameter : parameters) {
                    if (parameter != null && !parameter.isEmpty()) {
                        transformer.setParameter(parameter.getName(), parameter.getValue());
                        LOGGER.debug("Adding parameter [" + parameter + "]");
                    }
                }
            }

            // Prepare transformer (XML and output)
            StreamSource xmlSource = new StreamSource(xmlReader);
            Result result = new StreamResult(outputStream);

            // Execute transformation
            transformer.transform(xmlSource, result);

            // Get result
            output = outputStream.toString();
            LOGGER.debug("Transformation output is: " + output);

        } catch (Exception e) {
            throw new TransformerException("Exception encountered during transformation.", e);
        }

        return output;
    }
}
