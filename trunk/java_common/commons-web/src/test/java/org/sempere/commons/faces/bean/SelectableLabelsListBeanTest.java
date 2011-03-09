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

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import javax.faces.model.SelectItem;

import org.junit.Before;
import org.junit.Test;
import org.sempere.commons.bean.LabelBean;
import org.sempere.commons.faces.AbstractJSFTest;

/**
 * Unit tests class for SelectableLabelsListBean class.
 * 
 * @author bsempere
 */
public class SelectableLabelsListBeanTest extends AbstractJSFTest {

	private SelectableLabelsListBean bean;

	@Before
	public void before() throws Exception {
		super.before();
		this.bean = this.createSelectableLabelsListBean();
	}

	@Test
	public void getSelectItemsWithCodesForLabelsWhenLabelsLisIsNull() throws Exception {
		this.bean.setLabels(null);
		assertTrue("Items list should be empty.", this.bean.getSelectItemsWithCodesForLabels().isEmpty());
	}

	@Test
	public void getSelectItemsWithCodesForLabelsWhenLabelsListIsNotNull() throws Exception {
		Collection<SelectItem> items = this.bean.getSelectItemsWithCodesForLabels();
		assertEquals(2, items.size());

		Iterator<SelectItem> it = items.iterator();
		this.verifyOneLabelWithCodesForLabels(it.next());
		this.verifyTwoLabelWithCodesForLabels(it.next());
	}

	@Test
	public void getSelectItemsWithShortLabelsWhenLabelsLisIsNull() throws Exception {
		this.bean.setLabels(null);
		assertTrue("Items list should be empty.", this.bean.getSelectItemsWithShortLabels().isEmpty());
	}

	@Test
	public void getSelectItemsWithShortLabelsWhenLabelsListIsNotNull() throws Exception {
		Collection<SelectItem> items = this.bean.getSelectItemsWithShortLabels();
		assertEquals(2, items.size());

		Iterator<SelectItem> it = items.iterator();
		this.verifyOneLabelWithShortLabels(it.next());
		this.verifyTwoLabelWithShortLabels(it.next());
	}

	@Test
	public void getSelectItemsWithLongLabelsWhenLabelsLisIsNull() throws Exception {
		this.bean.setLabels(null);
		assertTrue("Items list should be empty.", this.bean.getSelectItemsWithLongLabels().isEmpty());
	}

	@Test
	public void getSelectItemsWithLongLabelsWhenLabelsListIsNotNull() throws Exception {
		Collection<SelectItem> items = this.bean.getSelectItemsWithLongLabels();
		assertEquals(2, items.size());

		Iterator<SelectItem> it = items.iterator();
		this.verifyOneLabelWithLongLabels(it.next());
		this.verifyTwoLabelWithLongLabels(it.next());
	}

	// ********************************************************************************
	//
	// Test fixtures, stubs, ...
	//
	// ********************************************************************************

	public SelectableLabelsListBean createSelectableLabelsListBean() {
		SelectableLabelsListBean labelsListBean = new SelectableLabelsListBean();
		labelsListBean.getLabels().add(new LabelBean("1", "one", "1 - one"));
		labelsListBean.getLabels().add(new LabelBean("2", "two", "2 - two"));

		return labelsListBean;
	}

	// ********************************************************************************
	//
	// Verification methods
	//
	// ********************************************************************************

	public void verifyOneLabelWithCodesForLabels(SelectItem item) {
		assertEquals("1", item.getValue());
		assertEquals("1", item.getLabel());
	}

	public void verifyTwoLabelWithCodesForLabels(SelectItem item) {
		assertEquals("2", item.getValue());
		assertEquals("2", item.getLabel());
	}

	public void verifyOneLabelWithShortLabels(SelectItem item) {
		assertEquals("1", item.getValue());
		assertEquals("one", item.getLabel());
	}

	public void verifyTwoLabelWithShortLabels(SelectItem item) {
		assertEquals("2", item.getValue());
		assertEquals("two", item.getLabel());
	}

	public void verifyOneLabelWithLongLabels(SelectItem item) {
		assertEquals("1", item.getValue());
		assertEquals("1 - one", item.getLabel());
	}

	public void verifyTwoLabelWithLongLabels(SelectItem item) {
		assertEquals("2", item.getValue());
		assertEquals("2 - two", item.getLabel());
	}
}
