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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


/**
 * Helper class that provides methods to work with java reflection.
 *
 * @author bsempere
 */
public final class ReflectionHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionHelper.class);

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************    

    private ReflectionHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    /**
     * Instantiates the given class
     *
     * @param clazzName the class to be instantiated
     * @return <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String clazzName) {
        return (T) newInstance(loadClass(clazzName));
    }

    /**
     * Instantiates the given class
     *
     * @param clazzName      the class to be instantiated
     * @param parameterTypes the constructor parameters types
     * @param parameters     the constructor parameters
     * @return <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String clazzName, Class<?>[] parameterTypes, Object... parameters) {
        return (T) newInstance(loadClass(clazzName), parameterTypes, parameters);
    }

    /**
     * Instantiates the given class
     *
     * @param clazz the class to be instantiated
     * @return <T>
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot instantiate class [" + clazz.getName() + "].", e);
        }
    }

    /**
     * Instantiates the given class
     *
     * @param clazz          the class to be instantiated
     * @param parameterTypes the constructor parameters types
     * @param parameters     the constructor parameters
     * @return <T>
     */
    public static <T> T newInstance(Class<T> clazz, Class<?>[] parameterTypes, Object... parameters) {
        try {
            return clazz.getConstructor(parameterTypes).newInstance(parameters);
        } catch (Exception e) {
            throw new RuntimeException("Cannot instantiate class [" + clazz.getName() + "].", e);
        }
    }

    /**
     * Loads the given class from the current class loader
     *
     * @param clazzName the class to be loaded
     * @return <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> loadClass(String clazzName) {
        try {
            return (Class<T>) Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load class [" + clazzName + "] in the current classLoader.", e);
        }
    }

    /**
     * Indicates if the given class exists in the current class loader
     *
     * @param clazzName the name of the class to be found
     * @return boolean
     */
    public static boolean exists(String clazzName) {
        boolean res = false;
        try {
            Class.forName(clazzName);
            res = true;
        } catch (ClassNotFoundException e) {
            // Nothing to do here
        }

        return res;
    }

    /**
     * Indicates if a class is a subclass of another
     *
     * @param childClass
     * @param parentClass
     * @return boolean
     */
    public static boolean isSubClass(Class<?> childClass, Class<?> parentClass) {
        boolean isSubClass = false;
        if (childClass != null && childClass.getSuperclass() != null && parentClass != null) {
            if (childClass.getSuperclass().getName().equals(parentClass.getName())) {
                isSubClass = true;
            } else {
                isSubClass = isSubClass(childClass.getSuperclass(), parentClass);
            }
        }

        return isSubClass;
    }

    /**
     * Invokes a method on an object instance
     *
     * @param methodName the name of the method to be invoked
     * @param instance   the instance the invocation is performed on
     * @return <T>
     */
    @SuppressWarnings("unchecked")
	public static <T> T invokeMethod(String methodName, Object instance) {
        return (T) invokeMethod(methodName, new Class<?>[]{}, instance, new Object[]{});
    }

    /**
     * Invokes a method on an object instance
     *
     * @param methodName     the name of the method to be invoked
     * @param parameterTypes the types of the parameters
     * @param instance       the instance the invocation is performed on
     * @param parameters     the method's parameters
     * @return <T>
     */
    @SuppressWarnings("unchecked")
	public static <T> T invokeMethod(String methodName, Class<?>[] parameterTypes, Object instance, Object... parameters) {
        T result = null;
        try {
            Method method = instance.getClass().getMethod(methodName, parameterTypes);
            result = (T) invokeMethod(method, instance, parameters);
        } catch (NoSuchMethodException e) {
            LOGGER.error("Could not invoke method with name [" + methodName + "].", e);
        }

        return result;
    }

    /**
     * Invokes a method on an object instance
     *
     * @param method     the method to be invoked
     * @param instance   the instance the invocation is performed on
     * @param parameters the method's parameters
     * @return <T>
     */
    @SuppressWarnings("unchecked")
	public static <T> T invokeMethod(Method method, Object instance, Object... parameters) {
        T result = null;
        try {
            result = (T) method.invoke(instance, parameters);
        } catch (Exception e) {
            LOGGER.error("Could not invoke method with name [" + method.getName() + "].", e);
        }

        return result;
    }
}
