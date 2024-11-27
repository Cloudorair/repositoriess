package com.itwill.user.exception;

import com.itwill.user.User;

public class ExistedUserException extends Exception{
	private User fuser;
	
	public User getFuser() {
		return fuser;
	}

	public void setFuser(User fuser) {
		this.fuser = fuser;
	}
	public ExistedUserException(String msg) {
		super(msg);
	}
}
