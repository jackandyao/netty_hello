package com.jhp.netty.chapter01.model;

import org.msgpack.annotation.Message;

import java.io.Serializable;

@Message
public class UserInfo implements Serializable {

	public String userName;

	public String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
