package com.thanhpl.helper.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	/*
	* y   = year   (yy or yyyy)
	* M   = month  (MM or MMM or MMMM)
	* d   = day in month (dd)
	* h   = hour (0-12)  (hh)
	* H   = hour (0-23)  (HH)
	* m   = minute in hour (mm)
	* s   = seconds (ss)
	* S   = milliseconds (SSS)
	* z   = time zone  text        (e.g. Pacific Standard Time...)
	* Z   = time zone, time offset (e.g. -0800)
	*/
	
	// Sample date formats
	public static final String FULL_TIMEZONE = "yyyy-MM-dd HH:mm:ss.SSS Z";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String ddMMyyyy = "ddMMyyyy";
	public static final String HHmmss = "HH:mm:ss"; // 14:00:00
	public static final String hhmmss = "hh:mm:ss"; // 2:00:00 PM
	public static final String HHmmssSSS = "HH:mm:ss.SSS"; // 14:05:10.999
	public static final String MMMd = "MMM d"; // Oct 5
	public static final String MMMMdyyyy = "MMMM d, yyyy"; // October 5, 2018
	
	// Date separators
	public static final char slash = '/';
	public static final char minus = '-';
	
	public static String toString(Date date, String format) {
		if (date == null) {
			return "";
		}
		if (format == null || format.trim().isEmpty()) {
			format = yyyyMMdd; // default
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String toString(Date date, String format, char separator) {
		if (date == null) {
			return "";
		}
		if (separator == ' ') {
			separator = slash; // default
		}
		if (yyyyMMdd.equals(format)) {
			return toString(date, "yyyy" + separator + "MM" + separator + "dd");
		} else if (ddMMyyyy.equals(format)) {
			return toString(date, "dd" + separator + "MM" + separator + "yyyy");
		}
		return "";
	}
	
	public static Date toDate(String date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}
}
