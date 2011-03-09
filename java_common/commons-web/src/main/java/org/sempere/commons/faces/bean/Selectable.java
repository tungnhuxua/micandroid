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

import java.util.Collection;

import javax.faces.model.SelectItem;

/**
 * Interface that defines methods to build a selection list (collection of SelectItem instances) from an object.
 * 
 * @author bsempere
 */
public interface Selectable {

	/**
	 * Returns a SelectItem list with codes for labels
	 * 
	 * @return Collection<SelectItem>
	 */
	Collection<SelectItem> getSelectItemsWithCodesForLabels();

	/**
	 * Returns a SelectItem list with short labels
	 * 
	 * @return Collection<SelectItem>
	 */
	Collection<SelectItem> getSelectItemsWithShortLabels();

	/**
	 * Returns a SelectItem list with long labels
	 * 
	 * @return Collection<SelectItem>
	 */
	Collection<SelectItem> getSelectItemsWithLongLabels();
}
