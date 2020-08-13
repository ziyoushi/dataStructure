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

        //将中缀表达式转换成后缀表达式
        //将中缀表达式转成List
        String expression = "1+((2+3)*4)-5";

        List<String> ls = toInfixExpressionList(expression);
        System.out.println(ls);
        //将得到的中缀表达式list转换成后缀表达式list


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

    //将中缀表达式List 转成后缀表达式list =>  [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => 1 2 3 + 4 * + 5 -
    public static List<String> parseSufixExpressionLisst(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明：因为s2这个栈 没有pop操作 后面我们还需要逆序输出 直接用List<String>替代s2
        //Stack<String> s2 = new Stack<>();//存储中间结果的栈
        List<String> s2 = new ArrayList<>();
        //遍历ls
        for (String item : ls) {
            //如果是一个数 就加入到s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if ("(".equals(item)){
                s1.push(item);
            }else if (")".equals(item)){
                //如果是), 则依次弹出s1栈顶的运算符 并压入s2 直到遇到左括号为止 此时将一对括号丢弃
                while (!"(".equals(s1.peek())){
                    s2.add(s1.pop());
                }
                //将( 弹出s1栈
                s1.pop();
            }else {
                //当item的优先级小于s1栈顶运算符 将s1栈顶运算符弹出并压入s2中 再次转到4.1与s1中的新的栈顶运算符比较
                //缺少比较优先级的方法
                while (s1.size() != 0){

                }
            }
            
        }


        return null;

    }

    //将中缀表达式转换成中缀List
    public static List<String> toInfixExpressionList(String s){
        List<String> list = new ArrayList<>();
        //定义一个变量 用于遍历 中缀表达式字符串
        int i = 0;
        //对多位数的拼接
        String str;
        //每遍历到一个字符就放到c中
        char c;
        do {
            //如果c是一个非数字 需要加入到list
            if ( (c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57){
                list.add(""+c);
                i++;//i需要后移
            }else {
                //需要拼接
                //如果是一个数 需要考虑多位数
                //先将str置空
                str = "";
                while (i< s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <=57){
                    //拼接
                    str += c;
                    i++;
                }
                list.add(str);
            }

        }while (i < s.length());
        return list;
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

//编写一个类 Operation 可以返回一个运算符对应的优先级
class Opreation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

}
