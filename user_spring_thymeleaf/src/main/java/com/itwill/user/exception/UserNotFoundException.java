package com.itwill.user.exception;

import com.itwill.user.User;

public class UserNotFoundException extends Exception {


	public UserNotFoundException(String msg) {
		super(msg);
	}
}
