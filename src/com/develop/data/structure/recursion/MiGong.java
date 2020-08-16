package com.develop.data.structure.recursion;

/**
 * @author changchen
 * @create 2020-08-16 15:43
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组 模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1 表示墙
        //上下 全部置为1
        for (int i = 0; i < 7 ; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map,1,1);

        //输出新的地图 小球走过 并标识过的地图
        System.out.println("小球走过,并标识过的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

    //使用递归回溯来给小球找路
    //说明
    //1、map表示地图
    //2、i,j表示从地图的哪个位置开始出发 (1,1)
    //3、如果小球能到map[6][5]位置说明通路找到
    //4、约定：当map[i][j] 为0表示该点没有走过 当为1表示墙 2 表示通路可以走 3表示该点已经走过 路不通
    //5、在走迷宫时 需要确定一个策略(方法) 下->右->上->左，如果该点走不通再回溯
    /**
     *
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路就返回true,否则就返回false
     */
    public static boolean setWay(int[][] map, int i, int j){
        if (map[6][5] == 2){
            //说明通路已经找到ok
            return true;
        }else {
            if (map[i][j] == 0){
                //当前这个点 还没有走过 按策略走
                //假定这个点可以走通
                map[i][j] = 2;
                //向下走
                if (setWay(map,i+1,j)){
                    return true;
                }else if (setWay(map,i,j+1)){
                    //向右走
                    return true;
                }else if (setWay(map,i-1,j)){
                    //向上走
                    return true;
                }else if (setWay(map,i,j-1)){
                    //向左走
                    return true;
                }else {
                    //说明该点是走不通的 死路
                    map[i][j] = 3;
                    return false;
                }

            }else {
                //map[i][j] != 0 ==> 1，2，3
                return false;
            }

        }

    }

}
