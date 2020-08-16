package com.develop.data.structure.recursion;

/**
 * @author changchen
 * @create 2020-08-16 15:13
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);

        int res = function(2);
        System.out.println(res);
    }

    //打印问题
    public static void test(int num){
        if (num > 2){
            test(num -1);
        }else {
            System.out.println("num="+num);
        }
    }

    //阶乘问题
    public static int function(int num){
        if (num == 1){
            return 1;
        }else {
            return function(num-1)*num;
        }
    }

}
