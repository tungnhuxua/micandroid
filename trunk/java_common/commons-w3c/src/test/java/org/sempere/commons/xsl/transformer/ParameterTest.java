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

import static junit.framework.Assert.*;

import org.junit.Test;

/**
 * Unit tests class for Parameter class.
 * 
 * @author bsempere
 */
public class ParameterTest {

	@Test
	public void isEmptyWhenNameIsNull() throws Exception {
		Parameter bean = new Parameter();
		bean.setName(null);
		bean.setValue("value");
		assertTrue(bean.isEmpty());
	}

	@Test
	public void isEmptyWhenNameIsEmpty() throws Exception {
		Parameter bean = new Parameter();
		bean.setName("");
		bean.setValue("value");
		assertTrue(bean.isEmpty());
	}

	@Test
	public void isEmptyWhenValueIsNull() throws Exception {
		Parameter bean = new Parameter();
		bean.setName("name");
		bean.setValue(null);
		assertTrue(bean.isEmpty());
	}

	@Test
	public void isEmptyWhenValueIsEmpty() throws Exception {
		Parameter bean = new Parameter();
		bean.setName("name");
		bean.setValue("");
		assertFalse(bean.isEmpty());
	}

	@Test
	public void isNotEmpty() throws Exception {
		Parameter bean = new Parameter();
		bean.setName("name");
		bean.setValue("value");
		assertFalse(bean.isEmpty());
	}
}
