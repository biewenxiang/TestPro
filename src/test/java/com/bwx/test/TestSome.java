package com.bwx.test;

import org.junit.Test;
import sun.text.resources.fr.FormatData_fr;

import java.lang.reflect.Array;
import java.util.*;

public class TestSome {
    @Test
    public void testString(){
        String aa = "123456741";
        char[] chars = aa.toCharArray();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0;i<chars.length;i++){

//            System.out.println(chars[i]);
            list.add(Integer.parseInt(String.valueOf(chars[i])));
        }
        Arrays.sort(chars);
        Collections.sort(list);
        for (char i:chars){
            System.out.println(i);
        }
        System.out.println(chars);
    }

    @Test
    public void testSome(){


        String sss = "101010100,15,,预计未来15天白天平均气温为4℃,,较冷,2018年11月29日-2018年12月13日\n";
        String[] ssp = sss.split(",");
        String[] aa = {"stationid","type", "level", "msg1", "msg2", "msg_label", "time"};
        for (int i = 0;i<aa.length;i++){
            System.out.println("tips."+aa[i]+"="+"00"+i);
        }

    }
}
