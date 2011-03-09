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

import org.sempere.commons.checker.*;

import java.lang.reflect.*;

/**
 * Handler relying to a Checker instance in order to check the validity of an object (T).
 *
 * @author bsempere
 */
public abstract class CheckerHandler<T> extends DelegateHandler<T> {

    // Checker
    private Checker<T> checker;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public CheckerHandler(Checker<T> checker, T object) {
        super(object);
        this.checker = checker;
    }

    // *************************************************************************
    //
    // Abstract methods
    //
    // *************************************************************************

    public abstract void handleCheckResult(boolean result);

    // *************************************************************************
    //
    // Methods from InvocationHandler interface
    //
    // *************************************************************************

    public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.handleCheckResult(this.checker.check(this.getRealObject()));
        return super.invoke(proxy, method, args);
    }

    // *************************************************************************
    //
    // Getters and Setters
    //
    // *************************************************************************

    public Checker<T> getChecker() {
        return checker;
    }

    public void setChecker(Checker<T> checker) {
        this.checker = checker;
    }
}