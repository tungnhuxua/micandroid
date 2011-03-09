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
package org.sempere.commons;

import org.junit.Test;


import static junit.framework.Assert.*;

/**
 *
 * Unit tests class for Pair class.
 *
 * @author bsempere
 */
public class PairTest {

    @Test
    public void newInstance() throws Exception {
        Pair<Long, String> myPair = new Pair<Long, String>(1L, "value1");

        assertEquals(Long.valueOf(1L), myPair.getPair1());
        assertEquals("value1", myPair.getPair2());
    }
}
