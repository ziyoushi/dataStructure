package com.develop.data.structure.stack;

/**
 * @author changchen
 * @create 2020-08-05 19:19
 */
public class Calculator {
    public static void main(String[] args) {

        String expression = "30+2*6-2";
        //定义两个栈 一个数栈 一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keyNum = "";//用于拼接多位数
        //循环扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch做相应的处理
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //符号栈中有操作符
                    //比较ch的优先级和栈中的优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        //如果当前操作符的优先级小于等于 栈中的操作符 就需要从数栈中pop两个数
                        // 从符号栈中pop一个符号 进行计算
                        // 将计算结果存入到数栈中
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //将当前的操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前操作符的优先级 大于 栈中的操作符的优先级 就直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                    //直接入符号栈
                    operStack.push(ch);
                }

            }else {
                //如果是数 直接入数栈
               // numStack.push(ch -48);
                //如果是多位数时，上面的代码会出现问题；
                //分析思路
                //1、当处理多位数时，不能发现是一个数就立即入栈 因为可能是多位数
                //2、当处理数，需要向expression的表达式的index再看一位 如果是数就进行扫描 如果是符号才入栈
                //3、需要定义一个变量 字符串用于拼接
                //处理多位数
                keyNum += ch;

                //如果ch已经是expression的最后一位 直接入栈
                if (index == expression.length() -1){
                    numStack.push(Integer.parseInt(keyNum));
                }else {
                    //判断下一个字符是不是数字 如果是数字就继续扫描 如果是运算符 入栈
                    //ps 只是看后一位 不是index++
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //运算符 直接入栈
                        numStack.push(Integer.parseInt(keyNum));
                        //注意：要清空keyNum
                        keyNum = "";
                    }
                }

            }
            //让index+1 并判断是否扫描到expression的最后一个
            index++;
            if (index >= expression.length()){
                break;
            }

        }
        //当表达式扫描完毕 就顺序从数栈和符号栈中pop出相应的数和符号 并计算
        while (true){
            //如果符号栈为空 则计算到最后的结果 数栈中只有一个结果
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //将数栈的最后数pop，就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式%s = %d",expression,res2);

    }
}

//定义一个类 表示栈
class ArrayStack2 {
    private int maxSize;// 栈的大小
    private int[] stack; //数组模拟栈，数据就放在该数组中
    private int top = -1;//top 表示栈顶 初始化为-1

    //构造器
    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回当前栈顶的值 ps:不是真正pop
    public int peek(){
        return stack[top];
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize -1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈push
    public void push(int value){
        //判断栈是否满
        if (isFull()){
            System.out.println("栈满！");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 将栈顶的数据返回
    public int pop(){
        //判断栈是否为空
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据!");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况(遍历栈 从顶向下遍历)
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据！");
            return;
        }
        for (int i = top; i >= 0; i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级 返回的数字越大 优先级越高
    public int priority(int oper){
        //假定表达式只有+ - * /
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断是否一个运算符
    public boolean isOper(char value){
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
}
