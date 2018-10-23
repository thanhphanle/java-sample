package com.thanhpl.helper.date;

import java.util.Date;

public class App {
	public static void main(String[] args) {
		System.out.println(DateHelper.toString(new Date(), DateHelper.FULL_TIMEZONE));
		System.out.println(DateHelper.toString(new Date(), DateHelper.MMMMdyyyy));
		System.out.println(DateHelper.toString(new Date(), DateHelper.HHmmssSSS));
		System.out.println(DateHelper.toString(new Date(), DateHelper.yyyyMMdd, DateHelper.slash));
		System.out.println(DateHelper.toString(new Date(), DateHelper.yyyyMMdd, DateHelper.minus));
	}
}
