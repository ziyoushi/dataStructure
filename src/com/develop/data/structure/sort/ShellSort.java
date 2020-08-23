package com.develop.data.structure.sort;

import java.util.Arrays;

/**
 * @author changchen
 * @create 2020-08-23 10:27
 */
public class ShellSort {
    public static void main(String[] args) {
        //原始数组 8,9,1,7,2,3,5,4,6,0
        int[] array = {8,9,1,7,2,3,5,4,6,0};
        shellSort(array);
    }

    //希尔排序 位移交换
    public static void shellSort(int[] array){
        int temp = 0;
        int count = 0;
        for (int gap = array.length/2; gap >0; gap /=2) {
            //希尔排序第一轮
            //第一轮排序 是将10个数据分成5组
            for (int i = gap; i < array.length; i++) {
                // 遍历各组中的所有元素(共5组，每组有2个元素)步长5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素 说明交换
                    if (array[j] > array[j+gap]){
                        temp = array[j];
                        array[j] = array[j+gap];
                        array[j+gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第"+(++count)+"轮后的结果="+ Arrays.toString(array));
        }


    }

}
