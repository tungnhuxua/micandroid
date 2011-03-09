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
package org.sempere.commons.faces.bean;

import org.sempere.commons.bean.LabelBean;
import org.sempere.commons.bean.LabelsListBean;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A "Selectable" labels list.
 *
 * @author bsempere
 */
public class SelectableLabelsListBean extends LabelsListBean implements Selectable {

    private static final long serialVersionUID = -620631779788586589L;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public SelectableLabelsListBean() {
    }

    // ********************************************************************************
    //
    // Methods from "Selectable" interface
    //
    // ********************************************************************************

    public Collection<SelectItem> getSelectItemsWithCodesForLabels() {
        Collection<SelectItem> items = new ArrayList<SelectItem>();
        if (this.getLabels() != null) {
            for (LabelBean labelBean : this.getLabels()) {
                SelectItem item = new SelectItem();
                item.setValue(labelBean.getValue());
                item.setLabel(labelBean.getValue());

                items.add(item);
            }
        }

        return items;
    }

    public Collection<SelectItem> getSelectItemsWithShortLabels() {
        Collection<SelectItem> items = new ArrayList<SelectItem>();
        if (this.getLabels() != null) {
            for (LabelBean labelBean : this.getLabels()) {
                SelectItem item = new SelectItem();
                item.setValue(labelBean.getValue());
                item.setLabel(labelBean.getShortLabel());

                items.add(item);
            }
        }

        return items;
    }

    public Collection<SelectItem> getSelectItemsWithLongLabels() {
        Collection<SelectItem> items = new ArrayList<SelectItem>();
        if (this.getLabels() != null) {
            for (LabelBean labelBean : this.getLabels()) {
                SelectItem item = new SelectItem();
                item.setValue(labelBean.getValue());
                item.setLabel(labelBean.getLabel());

                items.add(item);
            }
        }

        return items;
    }
}
