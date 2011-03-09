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

/**
 * Helper class that provides methods to work with booleans.
 *
 * @author bsempere
 */
public final class BooleanHelper {

    public static final Character CHARACTER_YES = 'Y';
    public static final Character CHARACTER_NO = 'N';
    public static final Character CHARACTER_TRUE = 'T';
    public static final Character CHARACTER_FALSE = 'F';
    public static final Character CHARACTER_ONE = '1';
    public static final Character CHARACTER_ZERO = '0';

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private BooleanHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************
    
    public static boolean getBoolean(Character character) {
        return CHARACTER_YES.equals(character) || CHARACTER_TRUE.equals(character) || CHARACTER_ONE.equals(character);
    }

    public static Character getCharacterYesNo(boolean bool) {
        return (bool) ? CHARACTER_YES : CHARACTER_NO;
    }

    public static Character getCharacterTrueFalse(boolean bool) {
        return (bool) ? CHARACTER_TRUE : CHARACTER_FALSE;
    }

    public static Character getCharacterOneZero(boolean bool) {
        return (bool) ? CHARACTER_ONE : CHARACTER_ZERO;
    }
}
