package com.work.general.util;

import java.util.List;

/**
 * 字符串工具类
 *
 * @author sunguohua
 */
public class StringUtil {

    /*
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str.trim()) || "null".equals(str.trim()) || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 所有字符串不为空返回false，有一个为空返回true
     *
     * @param str
     * @return
     */
    public static boolean isEmptyMultipleStr(String... str) {
        for (String st : str) {
            if (null == st || "".equals(st.trim()) || "null".equals(st.trim()) || st.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }


    /*
     * 判断字符串是否为空
     */
    public static boolean isEmpty(Object str) {
        if (null == str || "".equals(str) || "null".equals(str)) {
            return true;
        }
        return false;
    }

    /*
     * 判断list对象是否为空
     */
    public static boolean listIsEmpty(List list) {
        return (list == null || list.size() == 0) ? true : false;
    }


    public static String toString(Object obj) {
        return ((obj == null) ? "" : obj.toString());
    }

    public static String toString(Object obj1, Object obj2) {
        return isEmpty(obj1) ? toString(obj2) : toString(obj1);
    }
}
