package com.dw.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * （1）找到所有数中最小值下标
 * （2）第一趟循环将最小值的下标与第一个位置的数值交换位置，这样每次找到的最小值则固定到第一个
 * （3）第二趟循环从第二个开始继续上边的步骤，因为上一趟已经将最小的找到并放到了第一个的位置，因此第二趟只需从第二个数值开始比较。
 */
public class SelectSort implements DSort{

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]< arr[i]){
                    // 交换
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

        }
    }

    public static void main(String[] args) {
        Random random = new Random(20);
        int xx[] = new int[10];
        DSort dSort = new SelectSort();
        for (int i = 0; i < 10; i++) {
            xx[i] = random.nextInt(100);
        }
        Arrays.stream(xx).forEach(x -> System.out.print(x + "  "));
        dSort.sort(xx);
        System.out.println();
        Arrays.stream(xx).forEach(x -> System.out.print(x + "  "));
    }
}
