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
package org.sempere.commons.encryption;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.security.Key;
import static org.sempere.commons.encryption.DefaultEncrypter.*;
import javax.crypto.KeyGenerator;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests class for DefaultEncrypter class.
 * 
 * @author bsempere
 */
public class DefaultEncrypterTest {

	private DefaultEncrypter encrypter;

	@Before
	public void before() throws Exception {
		this.encrypter = new DefaultEncrypter();
	}

	// ********************************************************************************
	//
	// Test methods for encryption with parameters: String
	//
	// ********************************************************************************

	@Test(expected = IllegalStateException.class)
	public void encryptWithPassPhraseProviderWhenProviderIsNotSet() throws Exception {
		this.encrypter.setProvider(null);
		this.encrypter.encrypt("MyCipherText");
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithPassPhraseProviderWhenTextIsNull() throws Exception {
		this.encrypter.setProvider(this.createPassPhraseProvider());
		this.encrypter.encrypt(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithPassPhraseProviderWhenTextIsEmpty() throws Exception {
		this.encrypter.setProvider(this.createPassPhraseProvider());
		this.encrypter.encrypt(" ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithPassPhraseProviderWhenPassPhraseIsNull() throws Exception {
		this.encrypter.setProvider(this.createNullPassPhraseProvider());
		this.encrypter.encrypt("MyCipherText");
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithPassPhraseProviderWhenPassPhraseIsEmpty() throws Exception {
		this.encrypter.setProvider(this.createEmptyPassPhraseProvider());
		this.encrypter.encrypt("MyCipherText");
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithPassPhraseProviderWhenTextIsNull() throws Exception {
		this.encrypter.setProvider(this.createPassPhraseProvider());
		this.encrypter.decrypt(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithPassPhraseProviderWhenTextIsEmpty() throws Exception {
		this.encrypter.setProvider(this.createPassPhraseProvider());
		this.encrypter.decrypt(" ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithPassPhraseProviderWhenPassPhraseIsNull() throws Exception {
		this.encrypter.setProvider(this.createNullPassPhraseProvider());
		this.encrypter.decrypt("MyCipherText");
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithPassPhraseProviderWhenPassPhraseIsEmpty() throws Exception {
		this.encrypter.setProvider(this.createEmptyPassPhraseProvider());
		this.encrypter.decrypt("MyCipherText");
	}

	@Test
	public void encryptWithPassPhraseProviderWhenTextIsValid() throws Exception {
		this.encrypter.setProvider(this.createPassPhraseProvider());      

		String expectedClearText = "MyCipherText";
		String cipherText = this.encrypter.encrypt(expectedClearText);
		String actualClearText = this.encrypter.decrypt(cipherText);
		assertEquals(expectedClearText, actualClearText);
	}

	// ********************************************************************************
	//
	// Test methods for encryption with parameters: String, String
	//
	// ********************************************************************************

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithPassPhraseWhenTextIsNull() throws Exception {
		this.encrypter.encrypt(null, "MyPassPhrase");
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithPassPhraseWhenTextIsEmpty() throws Exception {
		this.encrypter.encrypt(" ", "MyPassPhrase");
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithPassPhraseWhenPassPhraseIsNull() throws Exception {
		this.encrypter.encrypt("MyCipherText", (String) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithPassPhraseWhenPassPhraseIsEmpty() throws Exception {
		this.encrypter.encrypt("MyCipherText", " ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithPassPhraseWhenTextIsNull() throws Exception {
		this.encrypter.decrypt(null, "MyPassPhrase");
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithPassPhraseWhenTextIsEmpty() throws Exception {
		this.encrypter.decrypt(" ", "MyPassPhrase");
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithPassPhraseWhenPassPhraseIsNull() throws Exception {
		this.encrypter.decrypt("MyCipherText", (String) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithPassPhraseWhenPassPhraseIsEmpty() throws Exception {
		this.encrypter.decrypt("MyCipherText", " ");
	}

	@Test
	public void encryptAndDecryptWithPassPhraseWhenTextAndPassPhraseAreValid() throws Exception {
		String expectedClearText = "MyCipherText";
		String cipherText = this.encrypter.encrypt(expectedClearText, "MyPassPhrase");
		String actualClearText = this.encrypter.decrypt(cipherText, "MyPassPhrase");
		assertEquals(expectedClearText, actualClearText);
	}

	// ********************************************************************************
	//
	// Test methods for encryption with parameters: String, Key
	//
	// ********************************************************************************

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithKeyWhenTextIsNull() throws Exception {
		this.encrypter.encrypt(null, this.createBlowfishKey());
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithKeyWhenTextIsEmpty() throws Exception {
		this.encrypter.encrypt(" ", this.createBlowfishKey());
	}

	@Test(expected = IllegalArgumentException.class)
	public void encryptWithKeyWhenKeyIsNull() throws Exception {
		this.encrypter.encrypt("MyCipherText", (Key) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithKeyWhenTextIsNull() throws Exception {
		this.encrypter.decrypt(null, this.createBlowfishKey());
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithKeyWhenTextIsEmpty() throws Exception {
		this.encrypter.decrypt(" ", this.createBlowfishKey());
	}

	@Test(expected = IllegalArgumentException.class)
	public void decryptWithKeyWhenKeyIsNull() throws Exception {
		this.encrypter.decrypt("MyCipherText", (Key) null);
	}

	@Test
	public void encryptAndDecryptWithDESKeyWhenTextAndKeyAreValid() throws Exception {
		String expectedClearText = "MyCipherText";
		Key key = this.createDESKey();

		String cipherText = this.encrypter.encrypt(expectedClearText, key);
		String actualClearText = this.encrypter.decrypt(cipherText, key);
		assertEquals(expectedClearText, actualClearText);
	}

	@Test
	public void encryptAndDecryptWithDESedeKeyWhenTextAndKeyAreValid() throws Exception {
		String expectedClearText = "MyCipherText";
		Key key = this.createDESedeKey();

		String cipherText = this.encrypter.encrypt(expectedClearText, key);
		String actualClearText = this.encrypter.decrypt(cipherText, key);
		assertEquals(expectedClearText, actualClearText);
	}

	@Test
	public void encryptAndDecryptWithTripleDESKeyWhenTextAndKeyAreValid() throws Exception {
		String expectedClearText = "MyCipherText";
		Key key = this.createTripleDESKey();

		String cipherText = this.encrypter.encrypt(expectedClearText, key);
		String actualClearText = this.encrypter.decrypt(cipherText, key);
		assertEquals(expectedClearText, actualClearText);
	}

	@Test
	public void encryptAndDecryptWithBlowfishKeyWhenTextAndKeyAreValid() throws Exception {
		String expectedClearText = "MyCipherText";
		Key key = this.createBlowfishKey();

		String cipherText = this.encrypter.encrypt(expectedClearText, key);
		String actualClearText = this.encrypter.decrypt(cipherText, key);
		assertEquals(expectedClearText, actualClearText);
	}

	// *************************************************************************
	//
	// Convenience methods for Salt Algorithm
	//
	// *************************************************************************

	@Test
	public void getRandomlyGeneratedSalt() throws Exception {
		assertEquals(DEFAULT_RGS_LENGTH, this.encrypter.getRandomlyGeneratedSalt().length);
	}

	@Test
	public void getRandomlyGeneratedSaltWithFixedLength() throws Exception {
		int expectedLength = 50;
		assertEquals(expectedLength, this.encrypter.getRandomlyGeneratedSalt(expectedLength).length);
	}

	@Test
	public void getPasswordHash() throws Exception {
		byte[] rgs = this.encrypter.getRandomlyGeneratedSalt();
		assertNotNull("PWH should not be null.", this.encrypter.getPasswordHash("MyPass".getBytes(), rgs));
	}

	@Test
	public void verifyPasswordWhenPasswordIsOK() throws Exception {
		byte[] password = "MyPass".getBytes();
		byte[] rgs = this.encrypter.getRandomlyGeneratedSalt();
		byte[] pwh = this.encrypter.getPasswordHash(password, rgs);

		assertTrue("Password should be OK.", this.encrypter.verifyPassword(password, pwh, rgs));
	}

	@Test
	public void verifyPasswordWhenPasswordIsNotOK() throws Exception {
		byte[] password = "MyPass".getBytes();
		byte[] rgs = this.encrypter.getRandomlyGeneratedSalt();
		byte[] pwh = this.encrypter.getPasswordHash(password, rgs);

		assertFalse("Password should not be OK.", this.encrypter.verifyPassword("AnotherPass".getBytes(), pwh, rgs));
	}

	// ********************************************************************************
	//
	// Test fixtures, stubs, ...
	//
	// ********************************************************************************

	private PassPhraseProvider createPassPhraseProvider() throws Exception {
		PassPhraseProvider provider = mock(PassPhraseProvider.class);
		when(provider.getPassPhrase()).thenReturn("MyPassPhraseFromProvider");

		return provider;
	}

	private PassPhraseProvider createNullPassPhraseProvider() throws Exception {
		PassPhraseProvider provider = mock(PassPhraseProvider.class);
		when(provider.getPassPhrase()).thenReturn(null);

		return provider;
	}

	private PassPhraseProvider createEmptyPassPhraseProvider() throws Exception {
		PassPhraseProvider provider = mock(PassPhraseProvider.class);
		when(provider.getPassPhrase()).thenReturn(" ");

		return provider;
	}

	private Key createDESKey() throws Exception {
		return KeyGenerator.getInstance("DES").generateKey();
	}

	private Key createDESedeKey() throws Exception {
		return KeyGenerator.getInstance("DESede").generateKey();
	}

	private Key createTripleDESKey() throws Exception {
		return KeyGenerator.getInstance("TripleDES").generateKey();
	}

	private Key createBlowfishKey() throws Exception {
		return KeyGenerator.getInstance("Blowfish").generateKey();
	}
}
