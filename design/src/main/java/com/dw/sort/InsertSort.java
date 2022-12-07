package com.dw.sort;

import java.util.Arrays;

public class InsertSort {

    /**
     * 1 核心思想：插入排序通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入 ，如此重复，直至完成序列排序。
     * 2 算法分析：
     * 1. 从序列第一个元素开始，该元素可以认为已经被排序
     * 2. 取出下一个元素，设为待插入元素，在已经排序的元素序列中从后向前扫描，如果该元素（已排序）大于待插入元素，将该元素移到下一位置。
     * 3. 重复步骤2，直到找到已排序的元素小于或者等于待排序元素的位置，插入元素
     * 4. 重复2，3步骤，完成排序
     * ————————————————
     * 原文链接：https://blog.csdn.net/hlc246/article/details/81076183
     *
     * @param arr
     */
    static void sort(int[] arr) {
        // 第二个数开始
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                // 待插入的元素
                int temp = arr[i];
                // 往前遍历
                for (int j = i; j >= 0; j--) {
                    if (j > 0 && arr[j - 1] > temp) {
                        arr[j] = arr[j - 1];
                    } else {
                        arr[j] = temp;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        sort(a);
        Arrays.stream(a).forEach(value -> System.out.println(value));
    }
}
