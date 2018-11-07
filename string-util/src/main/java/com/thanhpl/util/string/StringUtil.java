package com.thanhpl.util.string;

public class StringUtil {
	
	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		}
		if (str.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static int length(String str) {
		if (str == null) {
			return -1;
		}
		return str.length();
	}
	
	public static boolean isEqual(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return false;
		}
		if (str1.equalsIgnoreCase(str2)) {
			return true;
		}
		return false;
	}
	
	public static String trim(String str) {
		if (str == null) {
			return null;
		}
		return str.trim();
	}
	
	public static String defaultString(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		try {
			Long.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}
		return str.substring(start, end);
	}
	
	public static String[] split(String str, String divider) {
		if (str == null) {
			return null;
		}
		return str.split(divider);
	}
	
	// Format string with zeros at first
	public static String format0First(String str, int length) {
		if (str == null) {
			return null;
		}
		int strLength = str.length();
		int gap = length - strLength;
		if (gap <= 0) {
			return str;
		}
		StringBuilder zeroSb = new StringBuilder();
		while (gap > 0) {
			zeroSb.append("0");
			gap--;
		}
		zeroSb.append(str);
		return zeroSb.toString();
	}
	
	// Format string with zeros at the end
	public static String format0After(String str, int length) {
		if (str == null) {
			return null;
		}
		int strLength = str.length();
		int gap = length - strLength;
		if (gap <= 0) {
			return str;
		}
		StringBuilder zeroSb = new StringBuilder();
		zeroSb.append(str);
		while (gap > 0) {
			zeroSb.append("0");
			gap--;
		}
		return zeroSb.toString();
	}
}
