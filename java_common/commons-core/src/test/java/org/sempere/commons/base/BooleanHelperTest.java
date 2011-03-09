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
package org.sempere.commons.base;

import static org.sempere.commons.base.BooleanHelper.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests class for BooleanHelper.
 * 
 * @author bsempere
 */
public class BooleanHelperTest {

    @Test
    public void getBooleanfromNull() {
        assertFalse(BooleanHelper.getBoolean(null));
    }

    @Test
    public void getBooleanfromTrue() {
        assertTrue(BooleanHelper.getBoolean(CHARACTER_TRUE));
    }

    @Test
    public void getBooleanfromFalse() {
        assertFalse(BooleanHelper.getBoolean(CHARACTER_FALSE));
    }

    @Test
    public void getBooleanfromWrongString() {
        assertFalse(BooleanHelper.getBoolean('c'));
    }

    @Test
    public void getCharacterYesNoWhenTrue() {
        assertEquals(CHARACTER_YES, BooleanHelper.getCharacterYesNo(true));
    }

    @Test
    public void getCharacterYesNoWhenFalse() {
        assertEquals(CHARACTER_NO, BooleanHelper.getCharacterYesNo(false));
    }

    @Test
    public void getCharacterTrueFalseWhenTrue() {
        assertEquals(CHARACTER_TRUE, BooleanHelper.getCharacterTrueFalse(true));
    }

    @Test
    public void getCharacterTrueFalseWhenFalse() {
        assertEquals(CHARACTER_FALSE, BooleanHelper.getCharacterTrueFalse(false));
    }

    @Test
    public void getCharacterOneZeroWhenTrue() {
        assertEquals(CHARACTER_ONE, BooleanHelper.getCharacterOneZero(true));
    }

    @Test
    public void getCharacterOneZeroWhenFalse() {
        assertEquals(CHARACTER_ZERO, BooleanHelper.getCharacterOneZero(false));
    }
}
