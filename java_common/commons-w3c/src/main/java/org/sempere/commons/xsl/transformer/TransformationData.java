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

import org.apache.commons.lang.StringUtils;
import org.sempere.commons.print.PrintableObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Bean that encapsulates all needed data for a XSL transformation.
 *
 * @author bsempere
 */
public class TransformationData extends PrintableObject implements Transformable {

    private String xslContent;
    private String xmlContent;
    private String encoding;

    private Collection<Parameter> parameters = new ArrayList<Parameter>();

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public TransformationData() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public boolean isEmpty() {
        return StringUtils.isBlank(this.getXslContent()) || StringUtils.isBlank(this.getXmlContent());
    }

    // *************************************************************************
    //
    // Getters and Setters
    //
    // *************************************************************************

    public String getXslContent() {
        return xslContent;
    }

    public void setXslContent(String xslContent) {
        this.xslContent = xslContent;
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Collection<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<Parameter> parameters) {
        this.parameters = parameters;
    }
}
