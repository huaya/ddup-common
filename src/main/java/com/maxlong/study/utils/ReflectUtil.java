package com.maxlong.study.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * Utilities for reflection
 * 
 * 
 */
public class ReflectUtil {


    /**
     * check a class can be load
     * @param clazz
     * @return
     */
    public static boolean classLoadAble(String clazz) {
        try {
            Class.forName(clazz);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Get the property name of a method name. For example the property name of
     * setSomeValue would be someValue. Names not beginning with set or get are
     * not changed.
     * 
     * @param name The name to process
     * @return The property name
     */
    public static String getPropertyName(String name) {
        if(name != null && (name.startsWith("get") || name.startsWith("set"))) {
            StringBuilder b = new StringBuilder(name);
            b.delete(0, 3);
            b.setCharAt(0, Character.toLowerCase(b.charAt(0)));
            return b.toString();
        } else {
            return name;
        }
    }

    /**
     * Load the given class using the default constructor
     * 
     * @param className The name of the class
     * @return The class object
     */
    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch(ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Load the given class using a specific class loader.
     * 
     * @param className The name of the class
     * @param cl The Class Loader to be used for finding the class.
     * @return The class object
     */
    public static Class<?> loadClass(String className, ClassLoader cl) {
        try {
            return Class.forName(className, false, cl);
        } catch(ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Call the no-arg constructor for the given class
     * 
     * @param <T> The type of the thing to construct
     * @param klass The class
     * @return The constructed thing
     */
    public static <T> T callConstructor(Class<T> klass) {
        return callConstructor(klass, new Class<?>[0], new Object[0]);
    }

    /**
     * init a instance return null when Exception
     * @param name
     * @return
     */
    public static <T> T newInstance(String name) {
        try {
            Class clazz = Class.forName(name);
            return (T) clazz.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * init a instance return null when Exception
     * @param clazz
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * Call the constructor for the given class, inferring the correct types for
     * the arguments. This could be confusing if there are multiple constructors
     * with the same number of arguments and the values themselves don't
     * disambiguate.
     * 
     * @param klass The class to construct
     * @param args The arguments
     * @return The constructed value
     */
    public static <T> T callConstructor(Class<T> klass, Object[] args) {
        Class<?>[] klasses = new Class[args.length];
        for(int i = 0; i < args.length; i++)
            klasses[i] = args[i].getClass();
        return callConstructor(klass, klasses, args);
    }

    /**
     * Call the class constructor with the given arguments
     * 
     * @param c The class
     * @param args The arguments
     * @return The constructed object
     */
    public static <T> T callConstructor(Class<T> c, Class<?>[] argTypes, Object[] args) {
        try {
            Constructor<T> cons = c.getConstructor(argTypes);
            return cons.newInstance(args);
        } catch(InvocationTargetException e) {
            throw getCause(e);
        } catch(IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch(NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch(InstantiationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Call the named method
     * 
     * @param obj The object to call the method on
     * @param c The class of the object
     * @param name The name of the method
     * @param args The method arguments
     * @return The result of the method
     */
    public static <T> Object callMethod(Object obj,
                                        Class<T> c,
                                        String name,
                                        Class<?>[] classes,
                                        Object[] args) {
        try {
            Method m = getMethod(c, name, classes);
            return m.invoke(obj, args);
        } catch(InvocationTargetException e) {
            throw getCause(e);
        } catch(IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Get the named method from the class
     * 
     * @param c The class to get the method from
     * @param name The method name
     * @param argTypes The argument types
     * @return The method
     */
    public static <T> Method getMethod(Class<T> c, String name, Class<?>... argTypes) {
        try {
            return c.getMethod(name, argTypes);
        } catch(NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Get the root cause of the Exception
     * 
     * @param e The Exception
     * @return The root cause of the Exception
     */
    private static RuntimeException getCause(InvocationTargetException e) {
        Throwable cause = e.getCause();
        if(cause instanceof RuntimeException)
            throw (RuntimeException) cause;
        else
            throw new IllegalArgumentException(e.getCause());
    }
}
