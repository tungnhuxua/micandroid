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
package org.sempere.commons.proxy;

import org.junit.*;
import org.sempere.commons.checker.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

/**
 * Unit tests class for CheckerHandler.
 *
 * @author bsempere
 */
public class CheckerHandlerTest {

    private CheckerHandler<String> handler;

    @Before
    public void before() throws Exception {
        this.handler = new CheckerHandlerStub("MyString");
    }

    @Test
    public void invokeProxyShouldCallHandleCheckResultFirst() throws Throwable {
        Method method = "MyString".getClass().getMethod("toString", new Class[]{});
        assertEquals("MyHandledString", this.handler.invoke(null, method, null));
    }

    @Test
    public void getCheckerShouldReturnUnderlyingCheckersObject() throws Exception {
        assertTrue("Handler should be instance of CheckerStub.", this.handler.getChecker() instanceof CheckerStub);
    }

    // *************************************************************************
    //
    // Fixtures, Stubs, ...
    //
    // *************************************************************************

    private class CheckerHandlerStub extends CheckerHandler<String> {

        private CheckerHandlerStub(String object) {
            super(new CheckerStub(), object);
        }

        @Override
        public void handleCheckResult(boolean result) {
            this.setRealObject("MyHandledString");
        }
    }

    private class CheckerStub implements Checker<String> {

        public boolean check(String obj) {
            return false;
        }
    }
}

