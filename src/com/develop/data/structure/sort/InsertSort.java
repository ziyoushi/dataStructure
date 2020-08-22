package com.develop.data.structure.sort;

import java.util.Arrays;

/**
 * @author changchen
 * @create 2020-08-22 8:55
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {101, 34, 119, 1};
        insertSort(array);
    }

    //插入排序
    /**
     * 思路：从数组的第二个元素开始进行比较
     * 先保存每次需要插入的原始元素
     * 比较该值(和前一个值进行比较 如果比前一个值小 则将值后移 位置前移)
     * 循环结束后 将 原先保存的值放置到指定的位置上
     * @param array
     */
    public static void insertSort(int[] array) {
        //使用逐步推导的方式来
        //第1轮 {101,34,119,1}=> {34,101,119,1};

        for (int i = 1; i < array.length; i++) {

            // 定义待插入的数
            int insertValue = array[i];
            //即array[1] 的前面的这个数的下标
            int insertIndex = i - 1;

            //给insertValue 找到插入的位置
            //说明
            //insertIndex >= 0 保证在给insertValue 找插入位置 不越界
            //insertValue < array[insertIndex] 待插入的数 还没有找到插入的位置
            //就需要将 array[indexIndex]后移
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            //当退出while循环时 说明插入的位置找到 insertIndex +1
            array[insertIndex + 1] = insertValue;

            System.out.println("第"+i+"轮插入");
            System.out.println(Arrays.toString(array));

        }

    }

}
