package com.develop.data.structure.sort;

import java.util.Arrays;

/**
 * @author changchen
 * @create 2020-08-19 18:41
 */
public class SelectSort {
    public static void main(String[] args) {

        int[] array = {101,34,119,1};

        System.out.println("排序前的数组"+Arrays.toString(array));
        selectSort(array);
        System.out.println("排序后的数组"+Arrays.toString(array));
    }

    //选择排序 由小到大排序
    public static void selectSort(int[] array){

        //使用逐步推到的方式
        //原始数组 101，34，119，1
        //第一轮排序 1，34，119，101

        //算法 先简单=>复杂 可以把一个复杂的算法 先拆分成简单的问题 再逐步解决
        //时间复杂度O(n^2)
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            //假定第一个数为最小的数
            int min = array[i];
            for (int j = i +1; j < array.length; j++) {
                if (min > array[j]){
                    //如果假定的最小值 大于循环里的值 说明 假定的这个值不是最小值 重置最小值 最小值的索引
                    min = array[j];
                    minIndex = j;
                }
            }

            //第一轮循环结束后 将最小值 放在array[0] 交换
            //将默认的最小的值 后实际最小的进行交换
            if (minIndex != i){
                array[minIndex] = array[i];
                array[i] = min;
            }
            System.out.println("第"+(i+1)+"轮排序");

        }

    }

}
