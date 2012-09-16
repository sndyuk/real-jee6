package com.sndyuk.jee6.web;

public final class WebUtils {

	public static Long toId(String id) {
		if (id == null) {
			return null;
		}
		try {
			return Long.parseLong(id);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
