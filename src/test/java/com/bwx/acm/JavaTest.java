package com.bwx.acm;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: beiwenxiang
 * @Description:
 * @Date: create in 2020/6/5 15:30
 */
public class JavaTest {
    public static void main(String[] args) throws IOException {
        InputStreamReader in =new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String aa = br.readLine();
        System.out.println(aa);
    }
    @Test
    public void  test2052() throws IOException {
       int width = 9,height = 2;
       for (int i = 0;i<=height+1;i++){

           for (int j = 0;j<=width+1;j++){
               if ((j==0&&i==0)||(i==0&&j==width+1)||(i==height+1&&j==0)||(i==height+1&&j==width+1)){
                   System.out.print("+");
               }else if (i==0||i==height+1){
                   System.out.print("-");

               }else {
                   if (j==0||j==width+1){
                       System.out.print("l");

                   }
                   else {
                       System.out.print(" ");

                   }
               }

           }
           System.out.print("\n");

       }
       System.out.println(width+""+height);
    }
}
