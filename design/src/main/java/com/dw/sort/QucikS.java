package com.dw.sort;

import java.util.Arrays;
import java.util.Random;

public class QucikS {

    /**
     * （1）在待排序的N个记录中任取一个元素(通常取第一个记录)作为基准，称为基准记录；
     * （2）定义两个索引 left 和 right 分别表示“首索引” 和 “尾索引”，key 表示“基准值”；
     * （3）首先，尾索引向前扫描，直到找到比基准值小的记录(left != righ)，并替换首索引对应的值；
     * （4）然后，首索引向后扫描，直到找到比基准值大于的记录(left != righ)，并替换尾索引对应的值；
     * （5）若在扫描过程中首索引等于尾索引(left = right),则一趟排序结束;将基准值(key)替换首索引对应的值;
     * （6）再进行下一趟排序时，待排序列被分成两个区:[0,left-1],[righ+1,end]
     * （7）对每一个分区重复步骤2~6，直到所有分区中的记录都有序，排序成功。
     *
     * @param arr
     */
    static void sort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int left = leftIndex, right = rightIndex;
        int key = arr[left];

        while (left < right) {

            while (right > left && arr[right] > key) {
                right--;
            }
            arr[left] = arr[right];

            while (left < right && arr[left] < key) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = key;
        // 递归
        sort(arr, leftIndex, left - 1);
        System.out.println();
        Arrays.stream(arr).forEach(x -> System.out.print(x + "  "));
        sort(arr, right + 1, rightIndex);
    }

    public static void main(String[] args) {
        Random random = new Random(20);
        int xx[] = new int[10];
        for (int i = 0; i < 10; i++) {

            xx[i] = random.nextInt(100);
        }
        Arrays.stream(xx).forEach(x -> System.out.print(x + "  "));
        sort(xx, 0, xx.length-1);
        System.out.println();
        Arrays.stream(xx).forEach(x -> System.out.print(x + "  "));
    }
}
