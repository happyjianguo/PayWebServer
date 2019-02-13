package com.work.general.util;

import java.io.*;

public class SerializaUtil {

    public static void main(String[] args) {
        A a = new A();
        a.setName("wang");
        String s = serialize(a);
        A aa = (A)deserialize(s);
        System.out.println(aa);
    }

    public static String serialize(Object obj) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        String string = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            string = byteArrayOutputStream.toString("ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != objectOutputStream) {
                    objectOutputStream.close();
                }
                if (null != byteArrayOutputStream) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    public static Object deserialize(String str) {
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        Object object = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != objectInputStream) {
                    objectInputStream.close();
                }
                if (null != byteArrayInputStream) {
                    byteArrayInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return object;
    }


    static class A  implements Serializable{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "A{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
