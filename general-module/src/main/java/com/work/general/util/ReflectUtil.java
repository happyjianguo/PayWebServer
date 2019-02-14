package com.work.general.util;

import java.lang.reflect.Method;

public class ReflectUtil {

    public static void setter(Object obj, String toSet, Object value, Class<?> paraTypes) {
        Method target = null;
        try {
            target = obj.getClass().getMethod("set" + upperFirst(toSet), paraTypes);
            target.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getter(Object obj, String toGet) {
        Method target = null;
        Object result = null;
        try {
            target = obj.getClass().getMethod("get" + upperFirst(toGet));
            result = target.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String upperFirst(String toUpper) {
        return toUpper.substring(0, 1).toUpperCase() + toUpper.substring(1);
    }
}
