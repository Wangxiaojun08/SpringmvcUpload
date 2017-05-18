package com.jun.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String aaa;
	private String username;
	private MultipartFile image;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public User(String username, MultipartFile image) {
		super();
		this.username = username;
		this.image = image;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

}
