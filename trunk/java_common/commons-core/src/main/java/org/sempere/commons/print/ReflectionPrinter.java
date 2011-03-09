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
package org.sempere.commons.print;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Class that is responsible for printing objects.
 *
 * @author bsempere
 */
public class ReflectionPrinter implements Printer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionPrinter.class);

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************    

    public ReflectionPrinter() {
    }

    // *************************************************************************
    //
    // Methods from Printer interface 
    //
    // *************************************************************************

    public String print(Object object) {
        return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE, false);
    }

    public void print(Object object, OutputStream outputStream) {
        try {
            IOUtils.write(this.print(object), outputStream);
        } catch (IOException e) {
            LOGGER.warn("Cannot print object [" + this.print(object) + "].", e);
        }
    }

    public void print(Object object, Writer writer) {
        try {
            IOUtils.write(this.print(object), writer);
        } catch (IOException e) {
            LOGGER.warn("Cannot print object [" + this.print(object) + "].", e);
        }
    }
}
