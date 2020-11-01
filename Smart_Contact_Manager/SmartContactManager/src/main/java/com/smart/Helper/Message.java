package com.smart.Helper;

public class Message {
	
	private String content;
	private String typestring;
	public Message(String content, String typestring) {
		super();
		this.content = content;
		this.typestring = typestring;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTypestring() {
		return typestring;
	}
	public void setTypestring(String typestring) {
		this.typestring = typestring;
	}

}
