package com.develop.data.structure.sparesearray;

/**
 * @author changchen
 * @create 2020-07-22 22:17
 */
public class SparseArray {
    public static void main(String[] args) {

        //创建一个原始二维数组 11 * 11
        //0：表示没有棋子 1：表示黑子 2：表示蓝子
        int chessArr1[][] = new int[11][11];
        //给原数组赋值
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始二维数组如下");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //二维数组压缩成稀疏数组 思路
        //1、遍历原数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length ; j++) {
                //判断非0数据 sum++
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("原数组中非0个数="+sum);
        //根据非0数据的个数创建稀疏数组
        /**
         * 稀疏数组个数
         *    row col value
         * 0   11  11   2
         * 1   1   2    1
         * 2   2   3    2
         * ps:第一行 row表示原数组的行 第一行col表示原数组的列 第一行value表示原数组非0个数
         *    第二行开始 value表示 原数组对应row/col的值
         */
        int spareseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        spareseArr[0][0] = 11;
        spareseArr[0][1] = 11;
        spareseArr[0][2] = sum;
        //遍历原始二维数组 将非0数据存放到稀疏数组中
        //count用来记录第一行非0数据
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    count++;
                    spareseArr[count][0] = i;
                    spareseArr[count][1] = j;
                    spareseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("稀疏数组如下");
        for (int[] row : spareseArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将稀疏数组恢复成原始二位数组
        //思路分析：
        //1、先读取稀疏数组的第一行 根据第一行的数据来创建原始二维数组
        int chessArr2[][] = new int[spareseArr[0][0]][spareseArr[0][1]];
        //2、读取稀疏数组后几行(从第二行开始) 并赋值给原始二维数组
        for (int i = 1; i < spareseArr.length; i++) {
            chessArr2[spareseArr[i][0]][spareseArr[i][1]] = spareseArr[i][2];
        }
        System.out.println("恢复后的原始数组如下");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
