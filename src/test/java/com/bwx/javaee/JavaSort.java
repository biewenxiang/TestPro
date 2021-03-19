package com.bwx.javaee;

import org.jcodings.transcode.specific.From_GB18030_Transcoder;
import org.junit.Test;

public class JavaSort {
    public static void main(String[] args) {
        for (int i = 0;i<10;i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    for (int m = 0; m < 1000; m++) {
                        HashCodeTest.addList(m);
                    }
                }
            };
            thread.start();
        }
        System.out.print(2);

    }

    @Test
    public void testSort() {

        int[] nums = {4, 2, 8, 9, 5, 7, 6, 1, 3};
        bubbleSort(nums);
//        choiceSort(nums);
//        insertSort(nums);
        println(nums);
    }

    public static void println(int[] o) {
        for (int a : o) {
            System.out.print(a + " ");

        }

    }

    public static int[] bubbleSort(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 1; j < nums.length - i; j++) {
                int temp = nums[j];
                if (nums[j] < nums[j - 1]) {

                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
            System.out.print("第" + (i + 1) + "轮排序后的结果为:");
            display(nums);
        }

        return nums;
    }

    public static int[] choiceSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
            System.out.print("第" + (i + 1) + "轮排序后的结果为:");
            display(nums);

        }
        return nums;
    }

    public static int[] insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] < nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
//                    break;
                }
            }
            System.out.print("第" + (i + 1) + "轮排序后的结果为:");
            display(nums);


        }


        return nums;
    }

    //遍历显示数组
    public static void display(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Test
    public void testi() {
        MyQueue myQueue = new MyQueue(5);
        myQueue.insert(1);
        myQueue.insert(2);
        myQueue.insert(3);
        System.out.println(myQueue.peekFront()); //1
        myQueue.remove();//queArray数组数据为[null,2,3]
        System.out.println(myQueue.peekFront()); //2

        myQueue.insert(4);//queArray数组数据为[4,2,3]
        myQueue.insert(5);//队列已满,queArray数组数据为[4,2,3]
        myQueue.remove();//queArray数组数据为[null,2,3]
        myQueue.remove();//queArray数组数据为[null,2,3]
        myQueue.remove();//queArray数组数据为[null,2,3]

        myQueue.insert(5);//队列已满,queArray数组数据为[4,2,3]

        myQueue.insert(5);//队列已满,queArray数组数据为[4,2,3]
        myQueue.insert(5);//队列已满,queArray数组数据为[4,2,3]
        myQueue.remove();//queArray数组数据为[null,2,3]


    }

    @Test
    public void testfactorial() {

//        System.out.println(getFactorialFor(130));
        int[] aa = {1, 2, 3, 4, 5, 6};
        int find = 6;
        System.out.println(findBinarySearch(aa, find));

        System.out.println(findBinarySearch2(aa, find, 0, aa.length - 1));
    }

    public static int getFactorialFor(int n) {
        int temp = 1;
        if (n > 0) {
            temp = n * getFactorialFor(n - 1);
            n--;
        } else {
            return 1;
        }
        return temp;
    }

    public static int findBinarySearch(int[] nums, int find) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (find == nums[middle]) {
                return middle;
            }
            if (find > nums[middle]) {
                start = middle + 1;
            }
            if (find < nums[middle]) {
                end = middle - 1;
            }
        }
        return -1;
    }

    public static int findBinarySearch2(int[] nums, int find, int start, int end) {
        int mid = (end - start) / 2 + start;
        if (find == nums[mid]) {
            return mid;
        } else if (start > end) {
            return -1;
        } else {
            if (find < nums[mid]) {
                return findBinarySearch2(nums, find, start, mid - 1);
            } else if (find > nums[mid]) {//查找值比当前值大
                return findBinarySearch2(nums, find, mid + 1, end);
            }
        }
        return -1;
    }

    public static void move(int n, String a, String b, String c) {
        if (n == 1) {
            System.out.println("将盘子" + n + "从塔座" + a + "移动到目标塔座" + c);
        } else {
            move(n - 1, a, c, b);
            System.out.println("将盘子" + n + "从塔座" + a + "移动到目标塔座" + c);
            move(n - 1, b, a, c);
        }

    }

    @Test
    public void testmove() {

//        System.out.println(getFactorialFor(130));
        move(4, "a", "b", "c");


    }

    //归并排序
    public static int[] sortArray(int[] aa, int[] b) {
        int[] cc = new int[aa.length + b.length];

        int aanum = 0, bnum = 0, cNum = 0;
        ;
        while (aanum < aa.length && bnum < b.length) {
            if (aa[aanum] >= b[bnum]) {
                cc[cNum++] = b[bnum++];
            } else {
                cc[cNum++] = aa[aanum++];
            }
        }
        while (aanum == aa.length && bnum < b.length) {
            cc[cNum++] = b[bnum++];
        }
        while (bnum == b.length && aanum < aa.length) {
            cc[cNum++] = aa[aanum++];
        }
        return cc;
    }

    public static int[] mergeSort(int[] c, int start, int last) {
        if (last > start) {
            //也可以是(start+last)/2，这样写是为了防止数组长度很大造成两者相加超过int范围，导致溢出
            int mid = start + (last - start) / 2;
            mergeSort(c, start, mid);//左边数组排序
            mergeSort(c, mid + 1, last);//右边数组排序
            merge(c, start, mid, last);//合并左右数组
        }
        return c;
    }

    public static void merge(int[] c, int start, int mid, int last) {
        int[] temp = new int[last - start + 1];//定义临时数组
        int i = start;//定义左边数组的下标
        int j = mid + 1;//定义右边数组的下标
        int k = 0;
        while (i <= mid && j <= last) {
            if (c[i] < c[j]) {
                temp[k++] = c[i++];
            } else {
                temp[k++] = c[j++];
            }
        }
        //把左边剩余数组元素移入新数组中
        while (i <= mid) {
            temp[k++] = c[i++];
        }
        //把右边剩余数组元素移入到新数组中
        while (j <= last) {
            temp[k++] = c[j++];
        }

        //把新数组中的数覆盖到c数组中
        for (int k2 = 0; k2 < temp.length; k2++) {
            c[k2 + start] = temp[k2];
        }
    }

    public static int pow(int a, int b) {
        if (a <= 1) {
            return a;
        }
        if (b > 1) {
            int m = a * a;
            int n = b / 2;
            if (b % 2 == 0) {
                return pow(m, n);
            } else {
                return pow(m, n) * a;
            }
        } else if (b == 1) {
            return a;
        }
        return 1;
    }

    @Test
    public void testpow() {

//        System.out.println(getFactorialFor(130));
//        System.out.println(pow(3,1000000000));
//        int[] aa ={2,1,3,7,8,5,6};
        int[] aa = {4, 2, 8, 9, 5, 7, 6, 1, 3};

        System.out.println(aa);
        double m = 30;

        for (int i = 1; i <= 10; i++) {
            m = m * (1.30);
            System.out.println("" + i + ":: " + m);

        }
    }


}
