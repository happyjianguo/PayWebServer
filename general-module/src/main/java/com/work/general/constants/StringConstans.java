package com.work.general.constants;

/**
 * 字符串常量类
 *
 * @author sunguohua
 */
public interface StringConstans {

    public static interface CHARSET {
        String CHARSET_UTF_8 = "UTF-8";
    }

    public static interface ORDER_STATUS {
        /**订单初始状态*/
        String STATUS_01 = "01";
        /**订单状态成功*/
        String STATUS_02 = "02";
        /**订单失败*/
        String STATUS_03 = "03";
    }

}
