package com.thanhpl.helper.hitapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class RestfulHelper {
	
	private static final String POST_METHOD = "POST";
	private static final String GET_METHOD = "GET";
	private static final String JSON_TYPE = "application/json";

	public static JSONObject post(String endpointUrl, JSONObject paramJson) throws Exception {
		if (endpointUrl == null || endpointUrl.trim().isEmpty()) {
			return null;
		}
		if (paramJson == null) {
			return null;
		}

		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStream os = null;
		InputStreamReader ipr = null;
		try {
			URL url = new URL(endpointUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(POST_METHOD);
			conn.setRequestProperty("Content-Type", JSON_TYPE);

			os = conn.getOutputStream();
			os.write(paramJson.toString().getBytes());

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			ipr = new InputStreamReader((conn.getInputStream()));
			br = new BufferedReader(ipr);
			String responseData = "";
			String outputLine;
			while ((outputLine = br.readLine()) != null) {
				responseData += outputLine;
			}

			JSONObject resJson = new JSONObject(responseData);
			return resJson;
		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e) {
			throw e; 
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			try {
				if (os != null) {
					os.close();
					os.flush();
				}
				if (br != null) {
					br.close();
				}
				if (ipr != null) {
					ipr.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}
}
