package com.develop.data.structure.queue;

import java.util.Scanner;

/**
 * @author changchen
 * @create 2020-07-25 9:47
 * 使用数组模拟队列
 * 队列FIFO 先进先出
 * ps:用数组模拟队列可行，
 * 但是存在的问题是如果将数据全部取出后，无法再向队列中添加数据
 * 原因：原来的数组只能使用一次，不能重复使用。
 * 优化使用数组模拟环形队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建新的队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入需要添加到队列的数据！");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int data = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",data);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("取出的头数据是%d\n",head);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    default:
                        break;
            }
        }
        System.out.println("程序退出！");

    }
}
/**
 * 思路分析：
 *  1、将尾指针后移：rear+1,当front=rear说明队列是空的
 *  2、当尾指针rear小于队列的最大下表maxSize-1,则可以向队列中添加数据，否认无法添加数据；
 *  当rear==maxSize-1,说明队列已满，不能再添加数据
 */
class ArrayQueue{
    /** 表示数组的最大容量*/
    private int maxSize;
    /** 队列头指针*/
    private int front;
    /** 队列尾指针*/
    private int rear;
    /** 该数组用来存放数据，模拟队列*/
    private int[] arr;

    /**
     * 创建队列的构造器 初始化队列
     * @param arrMaxSize
     */
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        /** 指向队列头部，front是指向队列头的前一个位置*/
        front = -1;
        /** 指向队列尾部，指向队列尾部具体数据(即就是队列最后一个数据)*/
        rear = -1;
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull(){
        return rear == maxSize -1;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return front == rear;
    }

    /**
     * 添加数据到队列 从rear位置来加
     * @param data
     */
    public void addQueue(int data){
        //判断队列是否满 满则不能加入
        if (isFull()){
            System.out.println("队列已满！");
            return;
        }
        rear++;//rear后移
        arr[rear] = data;
    }

    /**
     * 获取队列数据 出队列 从front位置来取
     * @return
     */
    public int getQueue(){
        //判断队列是否为空 为空则不能取数据
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据！");
        }
        front++;//front本身指向队列头的位置，front后移，来取到队列的第一个数据
        return arr[front];
    }

    /**
     * 显示队列的所有数据 遍历
     */
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空,没有数据！");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    /**
     * 显示队列的头数据，ps:不是取出数据
     * @return
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front+1];
    }

}
