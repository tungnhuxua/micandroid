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
package org.sempere.commons.bean;

/**
 * Bean that represents a label.
 *
 * @author bsempere
 */
public class LabelBean extends AbstractBean {

    private static final long serialVersionUID = -1648944406424429422L;

    private String value;
    private String shortLabel;
    private String label;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public LabelBean() {
    }

    public LabelBean(String value, String label) {
        this(value, label, label);
    }

    public LabelBean(String value, String shortLabel, String label) {
        this.value = value;
        this.shortLabel = shortLabel;
        this.label = label;
    }

    // *************************************************************************
    //
    // Getters and Setters
    //
    // *************************************************************************

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getShortLabel() {
        return shortLabel;
    }

    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
