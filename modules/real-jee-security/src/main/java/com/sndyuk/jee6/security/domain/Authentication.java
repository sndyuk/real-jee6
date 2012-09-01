package com.sndyuk.jee6.security.domain;

import com.sndyuk.jee6.security.domain.dto.UserDto;

public interface Authentication {

	public UserDto login(String user, String password);
}