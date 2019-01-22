package com.thanhpl.api.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

// Rest API Client
public class SimpleRestClient {

	/*
	 * POST Content-Type: application/json
	 */
	public static void post() throws Exception {
		String endpointUrl = "http://localhost:8080/rest/example";
		JSONObject paramJson = new JSONObject();

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

			// Process response json
			JSONObject resJson = new JSONObject(responseData);
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

	/*
	 * GET Content-Type: application/json
	 */
	public static void get() throws Exception {
		String endpointUrl = "http://localhost:8080/rest/example";

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

			// Process response json
			JSONObject resJson = new JSONObject(responseData);
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

	/*
	 * POST Content-Type: application/x-www-form-urlencoded
	 */
	public static void postUrlencoded() throws Exception {
		String endpointUrl = "http://localhost:8080/rest/example";

		HttpURLConnection conn = null;
		BufferedReader bufReader = null;
		OutputStream outStr = null;
		InputStreamReader inStrReader = null;
		try {
			URL url = new URL(endpointUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			String params = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("abcd", "UTF-8");
			params += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("123456", "UTF-8");
			params += "&" + URLEncoder.encode("grant_type", "UTF-8") + "=" + URLEncoder.encode("password", "UTF-8");
			params += "&" + URLEncoder.encode("client_id", "UTF-8") + "=" + URLEncoder.encode("abcd1234", "UTF-8");

			conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));

			outStr = conn.getOutputStream();
			outStr.write(params.getBytes(StandardCharsets.UTF_8));

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException(
						"Failed: HTTP error code : " + conn.getResponseCode() + " - " + conn.getResponseMessage());
			}

			inStrReader = new InputStreamReader((conn.getInputStream()));
			bufReader = new BufferedReader(inStrReader);
			String responseData = "";
			String outputLine;
			while ((outputLine = bufReader.readLine()) != null) {
				responseData += outputLine;
			}
			
			// Process response json
			JSONObject responseJson = new JSONObject(responseData);
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
}
