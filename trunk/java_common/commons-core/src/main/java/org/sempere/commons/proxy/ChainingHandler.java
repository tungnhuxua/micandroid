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
import java.util.*;

/**
 * Handler relying to a Checker instance in order to check the validity of an object.
 *
 * @author bsempere
 */
public class ChainingHandler<T> extends DelegateHandler<T> {

    // handlers chain
    private List<InvocationHandler> handlers = new ArrayList<InvocationHandler>();

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public ChainingHandler(T delegate) {
        super(delegate);
    }

    public ChainingHandler(T delegate, List<InvocationHandler> handlers) {
        this(delegate);
        this.handlers = handlers;
    }

    public ChainingHandler(T delegate, InvocationHandler... handlers) {
        this(delegate);
        if (handlers != null) {
            this.handlers = Arrays.asList(handlers);
        }
    }

    // *************************************************************************
    //
    // Methods from InvocationHandler interface
    //
    // *************************************************************************

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (InvocationHandler handler : this.handlers) {
            handler.invoke(proxy, method, args);
        }
        return super.invoke(proxy, method, args);
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public void addHandler(InvocationHandler handler) {
        this.handlers.add(handler);
    }

    public void addHandlers(List<InvocationHandler> handlers) {
        if (handlers != null) {
            this.handlers.addAll(handlers);
        }
    }

    public void addHandlers(InvocationHandler... handlers) {
        this.addHandlers(Arrays.asList(handlers));
    }
}