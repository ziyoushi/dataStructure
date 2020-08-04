package com.develop.data.structure.stack;

import java.util.Scanner;

/**
 * @author changchen
 * @create 2020-08-04 19:19
 */
public class ArrayStackDemo {
    public static void main(String[] args) {

        //测试
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int num = stack.pop();
                        System.out.printf("出栈的数据%d\n",num);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }

        }
        System.out.println("退出程序");

    }
}

//定义一个类 表示栈
class ArrayStack {
    private int maxSize;// 栈的大小
    private int[] stack; //数组模拟栈，数据就放在该数组中
    private int top = -1;//top 表示栈顶 初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
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

}