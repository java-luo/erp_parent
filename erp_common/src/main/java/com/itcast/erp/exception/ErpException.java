package com.itcast.erp.exception;
/**
 * 创建自定义异常类
 * @author javaluo
 *
 */
public class ErpException extends RuntimeException{
	
	public ErpException(String msg){
		super(msg);
	}

}
