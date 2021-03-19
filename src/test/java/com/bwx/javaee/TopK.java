package com.bwx.javaee;

import java.util.Arrays;

/**
 * @Author: biewenxiang
 * @Description:
 * @Date: create in 2021/3/17 16:40
 */
public class TopK {

    /**
     * 找出数组arr中最大的前n个值,不要求这n个值有序
     */
    public static int[] topK(int[] arr, int n) {
        /**
         * 构建堆积
         */
        int[] list = new int[n];
        System.arraycopy(arr, 0, list, 0, n);
        // 在堆顶的始终是最小的值
        for (int i = 0; i < n; i++) {
            int t = i;
            while (t != 0 && list[parent(t)] > list[t]) {
                swap(list, t, t = parent(t));
            }
        }

        /**
         * 小顶堆
         */
        for (int i = n, len = arr.length; i < len; i++) {
            if (arr[i] >= list[0]) {
                // 置换栈顶
                list[0] = arr[i];
                // 调整栈顶
                int t = 0;
                // left(t) < n 防止下标越界
                while ((left(t) < n && list[t] > list[left(t)]) || (right(t) < n && list[t] > list[right(t)])) {
                    // 比较右节点和左节点值值，把小的节点值和父节点值对调
                    if (right(t) < n && list[right(t)] < list[left(t)]) {
                        swap(list, t, t = right(t));
                    } else {
                        swap(list, t, t = left(t));
                    }
                }
            }
        }
        return list;
    }

    private static void swap(int[] list, int i, int j) {
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    /**
     * 父节点索引
     */
    private static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * 左孩子索引
     */
    private static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * 右孩子索引
     */
    private static int right(int i) {
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 23, 4, 5, 11, 12, 13, 100};
        System.out.println("原始数组: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("调整后数组: ");
        System.out.println(Arrays.toString(TopK.topK(arr, 5)));
    }


}
