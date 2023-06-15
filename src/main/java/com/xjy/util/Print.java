package com.xjy.util;

/**
 * @Author 云
 * @create 2023/6/10 14:32
 */
public class Print {
    public static void print(String str){
        for (int i = 0; i <str.length() ; i++) {
            System.out.print(str.charAt(i));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
