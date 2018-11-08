package com.thanhpl.helper.hitapi;

import org.json.JSONObject;

public class App 
{
    public static void main( String[] args ) {
    	try {
			JSONObject json = RestHelper.get("http://api.thanhpl.com/public/v1/posts/popular");
			System.out.println(json.toString());
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
