package com.util;

import java.io.*;
import java.math.BigInteger;

public class SerializeUtil {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        String ss = "aabb";
//        byte[] s = ss.getBytes();
//        System.out.println(s);
//        String sss = new String(s);
//        System.out.println(sss);

//        A a = new A();
//        a.setName("wang");
//        byte[] s = serialize(a);
//        System.out.println(s);
//        A aa = (A)deserialize(s);
//        System.out.println(aa);

//        A a = new A();
//        a.setName("wang");
//        String s = serialize(a);
//        System.out.println(s);
//        A aa = (A)deserialize(s);
//        System.out.println(aa);

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        BigInteger bi = new BigInteger("0");
//        oos.writeObject(bi);
//        byte[] str = baos.toByteArray();
//        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(str)));
//        Object obj = ois.readObject();
//        System.out.println(obj);

        A a = new A();
        a.setName("wang");
        String s = serialize1(a);
        A aa = (A)serializeToObject1(s);
        System.out.println(aa);
    }

    public static String serialize1(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        String string = byteArrayOutputStream.toString("ISO-8859-1");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return string;
    }
    public static Object serializeToObject1(String str) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }


//    public static String serialize(Object object) throws UnsupportedEncodingException {
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = null;
//        try {
//            oos = new ObjectOutputStream(baos);
//            oos.writeObject(object);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes = baos.toByteArray();
//        String str = bytes.toString();
//        return str;
//    }
//
//    /**
//     * 反序列化
//     * @param str
//     * @return
//     */
//    public static Object deserialize(String str) throws UnsupportedEncodingException {
//        byte[] bytes = str.getBytes();
//        ObjectInputStream ois = null;
//        Object obj = null;
//        try {
//            ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
//            obj = ois.readObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return obj;
//    }
    public static byte[] serialize(Object object) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    /**
     * 反序列化
     * @param str
     * @return
     */
    public static Object deserialize(byte[] bytes) {

        ObjectInputStream ois = null;
        Object obj = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
            obj = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
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
