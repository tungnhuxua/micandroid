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
package org.sempere.commons.print;

import java.io.OutputStream;
import java.io.Writer;

/**
 * Interface that defines methods to print an object.
 *
 * @author bsempere
 */
public interface Printer {

    /**
     * Prints an object in a String
     *
     * @param object the object to be printed
     * @return String
     */
    String print(Object object);

    /**
     * Prints an object in a String
     *
     * @param object       the object to be printed
     * @param outputStream the stream the object will be printed in
     */
    void print(Object object, OutputStream outputStream);

    /**
     * Prints an object in a writer
     *
     * @param object the object to be printed
     * @param writer the writer the object will be printed in
     */
    void print(Object object, Writer writer);
}
