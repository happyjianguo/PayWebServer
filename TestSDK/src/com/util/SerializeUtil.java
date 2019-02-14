package com.util;

import java.lang.reflect.Method;

public class SerializeUtil {

    public static void main(String[] args) throws Exception {
        A a = new A();
        a.setName("sss");
        Object[] objects = {a};
        for (Object object : objects) {
            System.out.println(object.toString());
            String type = object.getClass().getName();//参数类型
            System.out.println(type);
            Class<?> cls = Class.forName(type);
            System.out.println(cls);
            Object obj = cls.newInstance();
            System.out.println(obj);
            System.out.println(getter(object, "name"));
        }

    }
    public static Object getter(Object obj, String toGet){
        Method target = null;
        Object result = null;
        try {
            String s = "get"+upperFirst(toGet);
            System.out.println(s);
            target = obj.getClass().getMethod(s);
            result = target.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String upperFirst(String toUpper){
        return toUpper.substring(0, 1).toUpperCase()+ toUpper.substring(1);
    }


}
