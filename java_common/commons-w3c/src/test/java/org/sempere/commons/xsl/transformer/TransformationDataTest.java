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

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests class for TransformationData class.
 * 
 * @author bsempere
 */
public class TransformationDataTest {

	@Test
	public void isEmptyWhenXslContentIsNull() throws Exception {
		TransformationData transformationData = new TransformationData();
		transformationData.setXslContent(null);
		transformationData.setXmlContent("<root></root>");
		assertTrue(transformationData.isEmpty());
	}

	@Test
	public void isEmptyWhenXslContentIsEmpty() throws Exception {
		TransformationData transformationData = new TransformationData();
		transformationData.setXslContent("");
		transformationData.setXmlContent("<root></root>");
		assertTrue(transformationData.isEmpty());
	}

	@Test
	public void isEmptyWhenXmlContentIsNull() throws Exception {
		TransformationData transformationData = new TransformationData();
		transformationData.setXslContent("<xsl></xsl>");
		transformationData.setXmlContent(null);
		assertTrue(transformationData.isEmpty());
	}

	@Test
	public void isEmptyWhenXmlContentIsEmpty() throws Exception {
		TransformationData transformationData = new TransformationData();
		transformationData.setXslContent("<xsl></xsl>");
		transformationData.setXmlContent("");
		assertTrue(transformationData.isEmpty());
	}

	@Test
	public void isNotEmpty() throws Exception {
		TransformationData transformationData = new TransformationData();
		transformationData.setXslContent("<xsl></xsl>");
		transformationData.setXmlContent("<root></root>");
		assertFalse(transformationData.isEmpty());
	}
}
