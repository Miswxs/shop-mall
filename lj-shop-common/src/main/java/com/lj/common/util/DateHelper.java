package com.lj.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 */
public final class DateHelper {

	public static final String YEAR_MONTH_DAY_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 在当前时间加减指定字段时间值
	 * @param calendarField 加减字段
	 * @param value 加减值
	 * @return 返回日期字符串
	 */
	public static Date nowAdd(int calendarField, int value) {
		GregorianCalendar gerCalendar = new GregorianCalendar();
		gerCalendar.add(calendarField, value);
		Date date = gerCalendar.getTime();
		return date;
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getCurrentTime() {
		GregorianCalendar gerCalendar = new GregorianCalendar();
		Date date = gerCalendar.getTime();
		return date;
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentTime(String format) {
		if (StringUtils.isEmpty(format)){
			format = YEAR_MONTH_DAY_HH_MM_SS;
		}
		GregorianCalendar gerCalendar = new GregorianCalendar();
		Date date = gerCalendar.getTime();
		SimpleDateFormat sdf =new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static void main(String[] args){
		System.out.print(DateHelper.getCurrentTime("yyyyMMddHHmmssSSS"));
	}
    
} 
