package com.develop.data.structure.queue;

import java.util.Scanner;

/**
 * @author changchen
 * @create 2020-07-25 15:20
 * 使用数组模拟环形队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        /**
         * 思路分析：
         *  1、front变量的含义做调整：front初始值为0
         *      front指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素。
         *  2、rear变量的含义做调整：rear的初始值为0
         *      rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间做约定。
         *  3、当队列满时，条件(rear+1)%maxSize == front
         *  4、队列为空的条件：rear==front
         *  5、队列中数据的有效个数：(rear + maxSize - front)% maxSize
         */
        //创建新的队列 实际队列最大容量是3 有一个位置是用来约定的
        CircleArrayQueue queue = new CircleArrayQueue(4);
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

class CircleArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加元素到队列
    public void addQueue(int data){
        //判断队列是否满 满则不能加入
        if (isFull()){
            System.out.println("队列已满！");
            return;
        }
        //rear指向的是
        arr[rear] = data;
        //将rear后移 这里需要考虑取模
        rear = (rear + 1) % maxSize;
    }

    public int getQueue(){
        //判断队列是否为空 为空则不能取数据
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据！");
        }
        /**
         * front是指向队列的第一个元素
         * 1、先将front对应的值保存的一个临时变量
         * 2、front后移 考虑取模
         * 3、将临时保存的变量返回
         */
        int value = arr[front];
        //front后移
        front = (front + 1) % maxSize;
        return value;
    }

    //查看
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空,没有数据！");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i%maxSize]);
        }
    }

    //返回有效个数的大小
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front];
    }

}
