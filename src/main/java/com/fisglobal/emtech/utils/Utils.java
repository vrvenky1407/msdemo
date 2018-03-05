package com.fisglobal.emtech.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Utils {
	public static <T> T convert(String urlencoded, Class<T> type) {
		try {
			Gson gson = new Gson();
			Map<String, Object> map = asMap(urlencoded);
			String json = gson.toJson(map);
			return gson.fromJson(json, type);
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
	}

	public static Map<String, Object> asMap(String urlencoded) throws UnsupportedEncodingException {
		return asMap(urlencoded, "UTF-8");
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> asMap(String urlencoded, String encoding) throws UnsupportedEncodingException {

		Map<String, Object> map = new LinkedHashMap<>();

		for (String keyValue : urlencoded.trim().split("&")) {

			String[] tokens = keyValue.trim().split("=");
			String key = tokens[0];
			String value = tokens.length == 1 ? null : URLDecoder.decode(tokens[1], encoding);

			String[] keys = key.split("\\.");
			Map<String, Object> pointer = map;

			for (int i = 0; i < keys.length - 1; i++) {

				String currentKey = keys[i];
				Map<String, Object> nested = (Map<String, Object>) pointer.get(keys[i]);

				if (nested == null) {
					nested = new LinkedHashMap<>();
				}

				pointer.put(currentKey, nested);
				pointer = nested;
			}

			pointer.put(keys[keys.length - 1], value);
		}

		return map;
	}
}
