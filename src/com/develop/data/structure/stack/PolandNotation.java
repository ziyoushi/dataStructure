package com.develop.data.structure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author changchen
 * @create 2020-08-12 19:23
 */
public class PolandNotation {
    public static void main(String[] args) {
        //定义一个逆波兰表达式
        //(3+4)x5-6 => 3 4 + 5 x 6 -
        //说明为了方便 逆波兰表达式 的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 x 6 -";
        //思路分析
        //1、先将"3 4 + 5 x 6 -" => 放到ArrayList中
        //2、将ArrayList 传递给一个方法 遍历ArrayList 配合栈 完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("rnList="+list);
        int cal = cal(list);
        System.out.println(cal);

    }

    public static List<String> getListString(String suffixExpression){

        List<String> list = new ArrayList<>();
        String[] split = suffixExpression.split(" ");
        for (String ele : split) {
            list.add(ele);
        }
        return list;

    }
    //完成逆波兰表达式的计算
    /**
     * 1、从左至右扫描 将3和4压入堆栈
     * 2、遇到+ 运算符 ，因此弹出4和3 计算运算结果 再压入栈中
     * 3、将5入栈
     * 4、运算符x 弹出5和7 运算结果压入栈
     * 5、将6入栈
     * 6、最后的运算符- 将35和6弹出 运算 得出最后结果
     */
    public static int cal(List<String> list){
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //使用正则表达式取出数 该正则表示多位数
            if (item.matches("\\d+")){
                //入栈
                stack.push(item);
            }else {
                //从栈中弹出两个数 计算 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item){
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "x":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                }
                stack.push(String.valueOf(res));
            }
            
        }
        //最后留在栈中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

}
