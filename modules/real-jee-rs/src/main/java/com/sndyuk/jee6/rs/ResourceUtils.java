package com.sndyuk.jee6.rs;

public final class ResourceUtils {

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
