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

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;
import org.sempere.commons.Pair;

/**
 * Unit tests class for ReflectionPrinter class.
 * 
 * @author bsempere
 */
public class ReflectionPrinterTest {

	private ReflectionPrinter printer;

	@Before
	public void before() throws Exception {
		this.printer = new ReflectionPrinter() {
		};
	}

	@Test
	public void printObject() throws Exception {
		String expectedResult = "Pair[pair1=myPropName,pair2=Pair[pair1=myNestedPropName,pair2=myNestedPropValue]]";

		Pair<String, Pair<String, String>> property = new Pair<String, Pair<String, String>>("myPropName", new Pair<String, String>("myNestedPropName", "myNestedPropValue"));
		assertEquals(expectedResult, this.printer.print(property));
	}

	@Test
	public void printObjectInOutputStream() throws Exception {
		String expectedResult = "Pair[pair1=myPropName,pair2=Pair[pair1=myNestedPropName,pair2=myNestedPropValue]]";

		Pair<String, Pair<String, String>> property = new Pair<String, Pair<String, String>>("myPropName", new Pair<String, String>("myNestedPropName", "myNestedPropValue"));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		this.printer.print(property, outputStream);
		outputStream.close();

		assertEquals(expectedResult, new String(outputStream.toByteArray()));
	}

	@Test
	public void printObjectInWriter() throws Exception {
		String expectedResult = "Pair[pair1=myPropName,pair2=Pair[pair1=myNestedPropName,pair2=myNestedPropValue]]";

		Pair<String, Pair<String, String>> property = new Pair<String, Pair<String, String>>("myPropName", new Pair<String, String>("myNestedPropName", "myNestedPropValue"));
		StringWriter writer = new StringWriter();

		this.printer.print(property, writer);
		writer.close();

		assertEquals(expectedResult, writer.getBuffer().toString());
	}
}
