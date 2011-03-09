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

import java.security.Key;

/**
 * Interface that defines methods for encryption.
 * 
 * @author bsempere
 */
public interface Encrypter {

    /**
     * Encrypts the given text using an internal passphrase
     *
     * @param clearText the text to be encrypted
     * @return String
     */
    String encrypt(String clearText);

    /**
     * Decrypts the given text using an internal passphrase
     *
     * @param cipherText the text to be decrypted
     * @return String
     */
    String decrypt(String cipherText);

    /**
     * Encrypts the given text using the given passPhrase
     *
     * @param clearText the text to be encrypted
     * @param passPhrase the passPhrase to be used during encryption
     * @return String
     */
    String encrypt(String clearText, String passPhrase);

    /**
     * Decrypts the given text using the given passPhrase
     *
     * @param cipherText the text to be decrypted
     * @param passPhrase the passPhrase to be used during decryption
     * @return String
     */
    String decrypt(String cipherText, String passPhrase);

    /**
     * Encrypts the given text using the given key
     *
     * @param clearText the text to be encrypted
     * @param key the key to be used
     * @return String
     */
    String encrypt(String clearText, Key key);

    /**
     * Decrypts the given text using the given key
     *
     * @param cipherText the text to be decrypted
     * @param key the key to be used
     * @return String
     */
    String decrypt(String cipherText, Key key);

    // *************************************************************************
    //
    // Convenience methods for Salt Algorithm
    //
    // *************************************************************************

    /**
     * Get a RGS
     * 
     * @return byte[]
     */
    byte[] getRandomlyGeneratedSalt();

    /**
     * Get a RGS of the specified length
     *
     * @param length the length of the wanted RGS
     * @return byte[]
     */
    byte[] getRandomlyGeneratedSalt(int length);

    /**
     * Get a password hash from a password and a RGS
     * 
     * @param password
     * @param rgs
     * @return byte[]
     */
    byte[] getPasswordHash(byte[] password, byte[] rgs);

    /**
     * Verifies that the given password was encrypted using the given parameters
     *
     * @param password
     * @param pwh the password hash
     * @param rgs the randomly generated salt
     * @return boolean
     */
    boolean verifyPassword(byte[] password, byte[] pwh, byte[] rgs);
}
