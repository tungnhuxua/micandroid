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
package org.sempere.commons.velocity;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.*;

/**
 * ^            ^
 * Unit tests class for AbstractRegistry class.
 *
 * @author bsempere
 */
public class VelocityHelperTest {

    @Test
    public void transform() throws Exception {
        Map<String, Object> engineConfigurationMap = new HashMap<String, Object>();
        engineConfigurationMap.put("resource.loader", "myLoader");
        engineConfigurationMap.put("myLoader.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("param1", "world");
        context.put("param2", "Velocity");

        assertEquals("Hello world! Welcome to Velocity!", VelocityHelper.transform("/org/sempere/commons/velocity/velocityHelper.vm", engineConfigurationMap, context));
    }
}
