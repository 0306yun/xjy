package com.xjy.util;

import java.util.Random;

/**
 * @Author 云
 * @create 2023/6/10 14:32
 */
public class StringUtil {
    public static String getRandomStr(int n){
        Random r=new Random();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i <128 ; i++) {
            if(Character.isLetterOrDigit((char)i)){
                sb.append((char)i);
            }
        }

        StringBuilder sb1=new StringBuilder();
        for (int i = 0; i <n ; i++) {
            sb1.append ( sb.charAt(r.nextInt(sb.length())) );
        }
        return sb1.toString();
    }
}
