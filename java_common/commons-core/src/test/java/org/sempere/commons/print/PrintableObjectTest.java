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
import static org.mockito.Mockito.*;

import java.io.OutputStream;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests class for PrintableObject class.
 * 
 * @author bsempere
 */
public class PrintableObjectTest {

	private PrintableObject printableObject;

	@Before
	public void before() throws Exception {
		this.printableObject = new PrintableObject() {
		};
	}

	@Test
	public void printShouldCallPrintOnPrinter() throws Exception {
		String expectedResult = "<printableObject/>";

		Printer printer = mock(Printer.class);
		when(printer.print(this.printableObject)).thenReturn(expectedResult);

		this.printableObject.setPrinter(printer);
		assertEquals(expectedResult, this.printableObject.print());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void printInOuputStreamShouldCallPrintInOuputStreamOnPrinter() throws Exception {
		Printer printer = mock(Printer.class);
		doThrow(new UnsupportedOperationException()).when(printer).print(this.printableObject, (OutputStream) null);

		this.printableObject.setPrinter(printer);
		this.printableObject.print((OutputStream) null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void printInWriterShouldCallPrintInWriterOnPrinter() throws Exception {
		Printer printer = mock(Printer.class);
		doThrow(new UnsupportedOperationException()).when(printer).print(this.printableObject, (Writer) null);

		this.printableObject.setPrinter(printer);
		this.printableObject.print((Writer) null);
	}
}
