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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests class for DefaultXSLTransformer class.
 * 
 * @author bsempere
 */
public class DefaultXSLTransformerTest {

	private static final String XSL_FILE_PATH = "/org/sempere/commons/xsl/transformer.xsl";
	private static final String XML_FILE_PATH = "/org/sempere/commons/xsl/transformer.xml";

	private static String xslContent;
	private static String xmlContent;

	private DefaultXSLTransformer transformer;

	@BeforeClass
	public static void beforeClass() throws Exception {
		xslContent = IOUtils.toString(DefaultXSLTransformerTest.class.getResourceAsStream(XSL_FILE_PATH));
		xmlContent = IOUtils.toString(DefaultXSLTransformerTest.class.getResourceAsStream(XML_FILE_PATH));
	}

	@Before
	public void before() throws Exception {
		this.transformer = new DefaultXSLTransformer();
	}

	@Test(expected = IllegalArgumentException.class)
	public void transformWhenTransformableIsNull() throws Exception {
		this.transformer.transform(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void transformWhenTransformableIsEmpty() throws Exception {
		this.transformer.transform(new TransformationData());
	}

	@Test
	public void transform() throws Exception {
		Collection<Parameter> parameters = new ArrayList<Parameter>();

		Parameter parameterBean = new Parameter();
		parameterBean.setName("param1");
		parameterBean.setValue("value1");
		parameters.add(parameterBean);

		parameterBean = new Parameter();
		parameterBean.setName("param2");
		parameterBean.setValue("value2");
		parameters.add(parameterBean);

		TransformationData transformationBean = new TransformationData();
		transformationBean.setXslContent(xslContent);
		transformationBean.setXmlContent(xmlContent);
		transformationBean.setParameters(parameters);
		String output = this.transformer.transform(transformationBean);

		assertNotNull(output);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>testvalue1value2", output);
	}
}
