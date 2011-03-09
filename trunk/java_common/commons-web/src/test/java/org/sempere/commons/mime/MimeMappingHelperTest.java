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
package org.sempere.commons.mime;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests class for MimeMappingHelper class.
 * 
 * @author bsempere
 */
public class MimeMappingHelperTest {

	@Test
	public final void getMimeTypeWhenExtensionIsNull() throws Exception {
		assertEquals(MimeMappingHelper.TEXT_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(null));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsEmpty() throws Exception {
		assertEquals(MimeMappingHelper.TEXT_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(""));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsText() throws Exception {
		assertEquals(MimeMappingHelper.TEXT_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(MimeMappingHelper.TEXT_MIME_TYPE));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsHtml() throws Exception {
		assertEquals(MimeMappingHelper.HTML_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(MimeMappingHelper.HTML_EXTENSION));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsExcel() throws Exception {
		assertEquals(MimeMappingHelper.EXCEL_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(MimeMappingHelper.EXCEL_EXTENSION));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsCsv() throws Exception {
		assertEquals(MimeMappingHelper.TEXT_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(MimeMappingHelper.CSV_EXTENSION));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsPowerPoint() throws Exception {
		assertEquals(MimeMappingHelper.PPT_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(MimeMappingHelper.PPT_EXTENSION));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsWord() throws Exception {
		assertEquals(MimeMappingHelper.WORD_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(MimeMappingHelper.WORD_EXTENSION));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsPdf() throws Exception {
		assertEquals(MimeMappingHelper.PDF_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(MimeMappingHelper.PDF_EXTENSION));
	}

	@Test
	public final void getMimeTypeWhenExtensionIsRtf() throws Exception {
		assertEquals(MimeMappingHelper.RTF_MIME_TYPE, MimeMappingHelper.getMimeTypeForExt(MimeMappingHelper.RTF_EXTENSION));
	}
}
