package com.develop.data.structure.sort;

import java.util.Arrays;

/**
 * @author changchen
 * @create 2020-08-18 18:59
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, -2};

        //冒泡排序时间复杂度O(n^2)
        //第一趟排序 将最大的数排在最后
        //临时变量用于交换
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {

                //如果前面的数 比后面的数大 则交换
                if (array[j] > array[j + 1]) {
                    //交换
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"次排序");
            System.out.println(Arrays.toString(array));

        }

    }
}
