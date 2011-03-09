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

import java.util.ArrayList;
import java.util.Collection;

/**
 * Bean that encapsulates a list of LabelBean instances.
 *
 * @author bsempere
 */
public class LabelsListBean extends AbstractBean {

    private static final long serialVersionUID = -4667473074133056363L;

    private Collection<LabelBean> labels = new ArrayList<LabelBean>();

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public LabelsListBean() {
    }

    // *************************************************************************
    //
    // Getters and Setters
    //
    // *************************************************************************

    public Collection<LabelBean> getLabels() {
        return labels;
    }

    public void setLabels(Collection<LabelBean> labels) {
        this.labels = labels;
    }
}
