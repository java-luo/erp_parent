package com.itcast.erp.common;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Md5加密工具类
 * @author javaluo
 *
 */
public class Md5Util {
	/**
	 * 通过shiro实现Md5加密
	 * @param str
	 * @return
	 */
	//md5加密的次数
	private static int hashlterations=2;
	
	public static String Md5Encode(String str){
		//没有传入盐值    就传递默认的盐值和加密次数
		return Md5Encode(str,"luotong",hashlterations);
	}
	public static String Md5Encode(String str,String salt){
		//没有传入加密次数    就传递默认加密次数
		return Md5Encode(str,salt,hashlterations);
	}
	/**
	 * MD5加密
	 * @param str 需要被加密的字符串
	 * @param salt  盐值
	 * @param hashlterations  加密的次数
	 * @return 加密后的数据
	 */
	public static String Md5Encode(String str,String salt,int hashlterations){
		System.out.println(str+salt+hashlterations);
		Md5Hash md5=new Md5Hash(str,salt,hashlterations);
		return md5.toString();
	}
}
