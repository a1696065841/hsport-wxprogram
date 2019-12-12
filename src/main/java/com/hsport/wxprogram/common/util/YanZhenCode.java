package com.hsport.wxprogram.common.util;

import java.util.Random;

public class YanZhenCode {
 
    private final static int codeLength =5;
    
    /**
     * @see 产生随机验证码
     * @return 验证码字符串
     */
	  public static String getCode(){
		  
		 Random rand = new  Random();
		 int  a ;
		 String  result ="";
		 for( int j =0; j<codeLength; j++ ){
			 a = Math.abs( rand.nextInt()%9 );
			 result += String.valueOf(a);
		 }
		 return  result;
	  }
}
