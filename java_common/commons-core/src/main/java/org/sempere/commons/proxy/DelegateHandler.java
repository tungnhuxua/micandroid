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

import java.lang.reflect.*;

/**
 * InvocationHandler that delegates call to the wrapped object.
 *
 * @author bsempere
 */
public class DelegateHandler<T> implements InvocationHandler {

    private T realObject;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public DelegateHandler(T realObject) {
        this.realObject = realObject;
    }

    // *************************************************************************
    //
    // Methods from InvocationHandler interface
    //
    // *************************************************************************

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.realObject, args);
    }

    // *************************************************************************
    //
    // Getters and Setters
    //
    // *************************************************************************

    public T getRealObject() {
        return realObject;
    }

    public void setRealObject(T realObject) {
        this.realObject = realObject;
    }
}