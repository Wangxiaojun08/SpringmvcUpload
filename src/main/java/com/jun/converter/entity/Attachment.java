package com.jun.converter.entity;

public class Attachment {

	private String name;
	private String requestUrl;
	private int size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Attachment() {
	}

	public Attachment(String name, String requestUrl, int size) {
		super();
		this.name = name;
		this.requestUrl = requestUrl;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Attachment [name=" + name + ", requestUrl=" + requestUrl + ", size=" + size + "]";
	}
	
}
