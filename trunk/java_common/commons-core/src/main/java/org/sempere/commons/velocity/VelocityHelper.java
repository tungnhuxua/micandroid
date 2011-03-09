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
package org.sempere.commons.velocity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


/**
 * Helper class that provides methods to work with Apache Velocity Framework.
 *
 * @author sempere
 */
public final class VelocityHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(VelocityHelper.class);

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private VelocityHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // ************************************************************************* 

    public static String transform(String templatePath, Map<String, Object> engineConfigurationMap, Map<String, Object> contextMap) {
        try {
            // Engine configuration
            VelocityEngine engine = new VelocityEngine();
            for (Map.Entry<String, Object> entry : engineConfigurationMap.entrySet()) {
                engine.setProperty(entry.getKey(), entry.getValue());
            }
            engine.init();

            // Context configuration
            VelocityContext context = new VelocityContext();
            for (Map.Entry<String, Object> entry : contextMap.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }

            // Engine execution
            Writer writer = new StringWriter();
            engine.getTemplate(templatePath).merge(context, writer);

            return writer.toString();
        } catch (Exception e) {
            LOGGER.error("Could not execute velocity transformation.", e);
            return null;
        }
    }
}