package com.itcast.erp.util;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;



public class Md5Test {
	public static void main(String[] args) {
		//参数1:要加密的值,参数2:盐值,参数3:加密次数
		Md5Hash md5=new Md5Hash("password","name",2);
		System.out.println(md5.toString());
		
		
	}
}
