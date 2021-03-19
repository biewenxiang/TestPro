package com.bwx.javaee;

import org.junit.Test;
import scala.Char;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static sun.misc.Version.print;

/**
 * @Author: beiwenxiang
 * @Description:
 * @Date: create in 2020/10/30 16:35
 */
public class HashCodeTest {


    public boolean equals(Object obj) {
        return (this == obj);
    }

    @Test
    public void testFactorial() {

        int n = 50;
        float aa = 1;
        for (int i = 1; i <= n; i++) {
            aa = i * aa;
        }
        System.out.println(aa);
        factori(n, 10000);

    }

    public void factori(int n, int arraynum) {
        int result[] = new int[arraynum];
        result[0] = 1;
        int carry = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < arraynum; j++) {
                result[j] = result[j] * i + carry;
                carry = result[j] / 10;
                result[j] = result[j] % 10;
            }
        }
        boolean flag = true;
        int flagi = 1;
        for (int i = arraynum - 1; i >= 0; i--) {
            if (result[i] != 0) {
                flagi = i;
                break;
            }

        }
        for (int i = flagi; i >= 0; i--) {
            System.out.print(result[i] + "");

        }
    }

    public void BigNumAdd(String aa, String bb) {
        int result[] = new int[aa.length() + bb.length()];
        if (aa.length() < bb.length()) {
            String tempstr = aa;
            aa = bb;
            bb = tempstr;
        }
        StringBuffer sba = new StringBuffer(aa).reverse();
        StringBuffer sbb = new StringBuffer(bb).reverse();
//        char alen[]= sba.;
//        char blen[] = bb.toCharArray();

//        String aas[] = aa.split("/t");

        int temp = 0;
        for (int i = 0; i < sbb.length(); i++) {
            int a1 = Integer.parseInt(String.valueOf(sba.charAt(i))) + Integer.parseInt(String.valueOf(sbb.charAt(i))) + temp;
            result[i] = a1 % 10;
            temp = a1 / 10;
        }
        if (sba.length() > sbb.length()) {
            for (int i = sbb.length(); i < sba.length(); i++) {
                int a1 = Integer.parseInt(String.valueOf(sba.charAt(i))) + temp;
                result[i] = a1 % 10;
                temp = a1 / 10;
            }
        }
        if (temp > 0) {
            result[sba.length()] = temp;
        }

        int flagi = 1;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] != 0) {
                flagi = i;
                break;
            }

        }
        for (int i = flagi; i >= 0; i--) {
            System.out.print(result[i] + "");

        }

    }

    @Test
    public void add() {
        String aa = "9999";
        String bb = "9999";
        BigNumAdd(aa, bb);
    }

    @Test
    public void addList() {
        List<Integer> list = new LinkedList();
        int n = 100;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) >= n) {
                list.add(i, n);
                break;
            }
        }
    }
    private static List<Integer> list = new LinkedList();

    public static void addList(int i) {
        synchronized (list){
            int j = 0;
            for (Integer integer : list) {
                if (integer > i) {
                    break;
                }
                j++;
            }
            list.add(j, i);
        }
    }
    public static int findBinarySearch(LinkedList<Integer> list, int find) {
        int start = 0;
        int end = list.size() - 1;
        int temp = 0;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
//            if (find == list.get(middle)) {
//                temp = middle;
//            }
            if (find >=list.get(middle)) {
                start = middle + 1;
            }
            if (find <list.get(middle)) {
                end = middle - 1;
            }
            temp = middle;

        }
        list.add(temp,find);
        return -1;
    }

    @Test
    public void testfind(){
        List<Integer> aa = new LinkedList<>();
        aa.add(1);
        aa.add(3);
        aa.add(5);
        aa.add(7);
        aa.add(9);
        aa.add(10);
        aa.add(18);
        findBinarySearch((LinkedList<Integer>) aa,7);




    }

}
