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
package org.sempere.commons.mail;

import static junit.framework.Assert.*;

import org.junit.Test;

/**
 * Unit tests class for MailData class.
 * 
 * @author bsempere
 */
public class MailDataTest {

	@Test
	public void isEmptyWhenFromIsNull() throws Exception {
		MailData bean = new MailData();
		bean.setSubject("subject");
		bean.setTo(new String[] { "to@to.to" });

		assertTrue(bean.isEmpty());
	}

	@Test
	public void isEmptyWhenSubjectIsNull() throws Exception {
		MailData bean = new MailData();
		bean.setFrom("from@from.from");
		bean.setTo(new String[] { "to@to.to" });

		assertTrue(bean.isEmpty());
	}

	@Test
	public void isEmptyWhenToIsNull() throws Exception {
		MailData bean = new MailData();
		bean.setFrom("from@from.from");
		bean.setSubject("subject");
		bean.setTo(null);

		assertTrue(bean.isEmpty());
	}

	@Test
	public void isEmptyWhenToIsEmpty() throws Exception {
		MailData bean = new MailData();
		bean.setFrom("from@from.from");
		bean.setSubject("subject");
		bean.setTo(new String[] {});

		assertTrue(bean.isEmpty());
	}

	@Test
	public void isNotEmpty() throws Exception {
		MailData bean = new MailData();
		bean.setFrom("from@from.from");
		bean.setSubject("subject");
		bean.setTo(new String[] { "to@to.to" });

		assertFalse(bean.isEmpty());
	}
}
