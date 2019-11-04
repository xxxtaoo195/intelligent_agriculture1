package com.topwulian.model;

/**
 * 返回代码
 */
public enum RespCode {
	
	FAIL("1","保存失败"),
	SUCCESS("0","保存成功");
	
	RespCode(){
	}
	RespCode(String code, String message){
		this.message = message;
		this.code = code;
	}
	String code;
	String message;
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}
