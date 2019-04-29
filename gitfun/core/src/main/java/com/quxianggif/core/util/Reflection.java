package com.quxianggif.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This provides a send method to allow calling method in dynamic way.
 * @author xuehj
 * @since 2019-04-22
 */
public class Reflection {
    /**
     * Disable to create an instance of DynamicExecutor.
     */
    private Reflection() {
    }

    /**
     * This method use java reflect API to execute method dynamically. Most
     * Importantly, it could access the methods with private modifier to break encapsulation.
     * @param object 调用方法的对象
     * @param methodName 调用方法的名称
     * @param parameters 调用方法的参数
     * @param objectClass 使用对象类型来查找调用的方法
     * @param parameterTypes 调用方法的参数类型
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static Object send(Object object, String methodName, Object[] parameters,
                              Class<?> objectClass, Class<?>[] parameterTypes) throws SecurityException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (parameters == null) {
            parameters = new Object[]{};
        }
        if (parameterTypes == null) {
            parameterTypes = new Class[]{};
        }
        Method method = objectClass.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(object, parameters);
    }

    /**
     * 该方法使用Java反射的API，用来动态设置对象的属性值，不能方法私有修饰符的属性
     * @param object 方法的对象
     * @param fieldName 访问的属性名
     * @param value 赋给属性的值
     * @param objectClass 对象的类型
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static void setField(Object object, String fieldName, Object value, Class<?> objectClass)
        throws SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Field objectField = objectClass.getDeclaredField(fieldName);
        objectField.setAccessible(true);
        objectField.set(object, value);
    }

    /**
     * 动态获取属性的值
     * @param object 要访问的对象
     * @param fieldName 访问的属性名
     * @param objectClass 访问对象的类型
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getField(Object object, String fieldName, Class<?> objectClass)
        throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Field objectField = objectClass.getDeclaredField(fieldName);
        objectField.setAccessible(true);
        return objectField.get(object);
    }
}
