package com.work.general.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String defaultSimpleFormater = "yyyy-MM-dd hh:mm:ss";
	public static String defaultSimpleFormater2 = "yyyyMMdd HH:mm:ss";
	public static final String YYMM = "yyyyMM";

	public static final String DDHHMMSS = "ddHHmmss";

	public static final String YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";

	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

	public static final String YYMMDD = "yyMMdd";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String YYYYMMDDFORMAT = "yyyy-MM-dd";

	public static final String YYYYMMDDHHHMMSSFORMAT = "yyyy-MM-ddHH:mm:ss";

	public static final String HHMMSS = "HHmmss";

	public static final String YYYY = "yyyy";

	public static final int YEAR = 0;

	public static final int MONTH = 1;

	public static final int DAY = 2;

	public static final int HOUR = 3;

	public static final int MINUTE = 4;

	public static final int SECOND = 5;

	/**
	 * Ĭ�ϼ������ַ���
	 * 
	 * @return
	 */
	public static String getDefaultSimpleFormater() {
		return defaultSimpleFormater;
	}

	/**
	 * ����Ĭ�ϼ����ڸ�ʽ�ַ���
	 * 
	 * @param defaultFormatString
	 */
	public static void setDefaultSimpleFormater(String defaultFormatString) {
		DateUtil.defaultSimpleFormater = defaultFormatString;
	}

	/**
	 * ��ʽ������
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String format(Date date, String formatString) {
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat df = new SimpleDateFormat(formatString);
			return df.format(date);
		}
	}

	/**
	 * ��ʽ������(ʹ��Ĭ�ϸ�ʽ)
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, defaultSimpleFormater);
	}

	/**
	 * ת��������
	 * 
	 * @param dateString
	 * @param formatString
	 * @return
	 */
	public static Date parse(String dateString, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * ת��������(ʹ��Ĭ�ϸ�ʽ)
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parse(String dateString) {
		return parse(dateString, defaultSimpleFormater);
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public static Date yesterday() {
		return addDay(-1);
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public static Date tomorrow() {
		return addDay(1);
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * ���ռ�
	 * 
	 * @param value
	 * @return
	 */
	public static Date addDay(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, value);
		return now.getTime();
	}

	/**
	 * ���ռ�,ָ������
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addDay(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.DAY_OF_YEAR, value);
		return now.getTime();
	}

	/**
	 * ��ȡ����1��
	 * 
	 * @return
	 */
	public static Date getCureentMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getCurrentDayStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();

	}

	public static Date getCurrentDayEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();

	}

	/**
	 * ��ȡ�������һ��
	 * 
	 * @return
	 */
	public static Date getCureentMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * ��ȡָ�����ڵ�1��
	 * 
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * ��ȡָ�������·ݵ����һ��
	 * 
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getMonthLastDay2(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * ���¼�
	 * 
	 * @param value
	 * @return
	 */
	public static Date addMonth(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, value);
		return now.getTime();
	}

	/**
	 * ���¼�,ָ������
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addMonth(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MONTH, value);
		return now.getTime();
	}

	/**
	 * �����
	 * 
	 * @param value
	 * @return
	 */
	public static Date addYear(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, value);
		return now.getTime();
	}

	/**
	 * �����,ָ������
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addYear(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.YEAR, value);
		return now.getTime();
	}

	/**
	 * ��Сʱ��
	 * 
	 * @param value
	 * @return
	 */
	public static Date addHour(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR_OF_DAY, value);
		return now.getTime();
	}

	/**
	 * ��Сʱ��,ָ������
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addHour(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.HOUR_OF_DAY, value);
		return now.getTime();
	}

	/**
	 * �����Ӽ�
	 * 
	 * @param value
	 * @return
	 */
	public static Date addMinute(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, value);
		return now.getTime();
	}

	/**
	 * �����Ӽ�,ָ������
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addMinute(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MINUTE, value);
		return now.getTime();
	}

	/**
	 * ���
	 * 
	 * @return
	 */
	public static int year() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}

	/**
	 * �·�
	 * 
	 * @return
	 */
	public static int month() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MONTH);
	}

	/**
	 * ��(��)
	 * 
	 * @return
	 */
	public static int day() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Сʱ(��)
	 * 
	 * @return
	 */
	public static int hour() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.HOUR);
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public static int minute() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MINUTE);
	}

	/**
	 * ��
	 * 
	 * @return
	 */
	public static int second() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.SECOND);
	}

	/**
	 * ���ڼ�(��ݼ�)
	 * 
	 * @return
	 */
	public static int weekday() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * ��������?
	 * 
	 * @return
	 */
	public static boolean isAm() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.AM_PM) == 0;
	}

	/**
	 * ��������?
	 * 
	 * @return
	 */
	public static boolean isPm() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.AM_PM) == 1;
	}

	public static String getDDHHMMSS() {
		return getDateStr(DDHHMMSS);
	}

	public static String getDateStr(String formatPattern) {
		Date date = new Date();
		return getDateStr(date, formatPattern);
	}

	public static String getDateYYYYMMDD() {
		Date date = new Date();
		return getDateStr(date, YYYYMMDD);
	}

	public static String getDateHHMMSS() {
		Date date = new Date();
		return getDateStr(date, HHMMSS);
	}

	private static String getDateStr(Date date, String formatPattern) {

		if (date == null) {
			date = new Date();
		}

		SimpleDateFormat df = new SimpleDateFormat(formatPattern);
		String dateStr = df.format(date);

		return dateStr;
	}

	/**
	 * 
	 * �ַ���ת���ڶ���
	 * 
	 * @param dateStr
	 *            �����ַ���
	 * @param formatPattern
	 *            ���ڸ�ʽ
	 * @return
	 * @see 1.0
	 * @since 1.0
	 */
	public static Date str2Date(String dateStr, String formatPattern) {
		SimpleDateFormat df = new SimpleDateFormat(formatPattern);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * ���ڶ���ת�ַ���
	 * 
	 * @param date
	 *            ���ڶ���
	 * @param formatPattern
	 *            ��ʽ
	 * @return
	 * @see 1.0
	 * @since 1.0
	 */
	public static String date2Str(Date date, String formatPattern) {
		SimpleDateFormat df = new SimpleDateFormat(formatPattern);
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * ����ģʽ����һ���µ�SimpleDateFormat,��ֹ�ڶ��̵߳�ʱ����ֻ���
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSimpleDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	/**
	 * ���ϵͳ��ǰ����ʱ��
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat sdf = getSimpleDateFormat(YYYYMMDDHHMMSS);
		return sdf.format(Calendar.getInstance().getTime());
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getDateStr("yyyyMMddHHmmss"));
	}
}
