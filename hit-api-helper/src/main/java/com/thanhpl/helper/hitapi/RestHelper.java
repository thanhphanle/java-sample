package com.thanhpl.helper.hitapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class RestHelper {

	public static JSONObject post(String endpointUrl, JSONObject paramJson) throws Exception {
		if (endpointUrl == null || endpointUrl.trim().isEmpty()) {
			return null;
		}
		if (paramJson == null) {
			return null;
		}

		HttpURLConnection conn = null;
		BufferedReader bufReader = null;
		OutputStream outStr = null;
		InputStreamReader inStrReader = null;
		try {
			URL url = new URL(endpointUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			outStr = conn.getOutputStream();
			outStr.write(paramJson.toString().getBytes());

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			inStrReader = new InputStreamReader((conn.getInputStream()));
			bufReader = new BufferedReader(inStrReader);
			String responseData = "";
			String outputLine;
			while ((outputLine = bufReader.readLine()) != null) {
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
				if (outStr != null) {
					outStr.close();
					outStr.flush();
				}
				if (bufReader != null) {
					bufReader.close();
				}
				if (inStrReader != null) {
					inStrReader.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}

	public static JSONObject get(String endpointUrl) throws Exception {
		if (endpointUrl == null || endpointUrl.trim().isEmpty()) {
			return null;
		}

		HttpURLConnection conn = null;
		BufferedReader bufReader = null;
		InputStreamReader inStrReader = null;
		try {
			URL url = new URL(endpointUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			inStrReader = new InputStreamReader((conn.getInputStream()));
			bufReader = new BufferedReader(inStrReader);
			String responseData = "";
			String outputLine;
			while ((outputLine = bufReader.readLine()) != null) {
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
				if (bufReader != null) {
					bufReader.close();
				}
				if (inStrReader != null) {
					inStrReader.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}
}
