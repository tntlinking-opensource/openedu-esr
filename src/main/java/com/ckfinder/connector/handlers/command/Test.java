package com.ckfinder.connector.handlers.command;

public class Test {

	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		
		System.out.println(Aes.Encrypt("123"));
		
		System.out.println(Aes.Decrypt("AkJI24CA9S1TFyiZitx5tw=="));
		
		String xn = "2017-2018";
		String xq = "1";
		int ceint = Integer.parseInt(xn.substring(0,4)) - Integer.parseInt("2017");
		System.out.println(ceint);
		
		
		
	}

}
