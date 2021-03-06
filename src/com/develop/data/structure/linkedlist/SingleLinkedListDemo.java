package com.develop.data.structure.linkedlist;

/**
 * @author changchen
 * @create 2020-07-26 9:52
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");

        SingleLinkedList singleLinked = new SingleLinkedList();
        //没有按照编号添加
        /*singleLinked.add(hero1);
        singleLinked.add(hero2);
        singleLinked.add(hero3);
        singleLinked.add(hero4);*/

        System.out.println("按照编号添加");
        singleLinked.addByOrder(hero1);
        singleLinked.addByOrder(hero2);
        singleLinked.addByOrder(hero3);
        singleLinked.addByOrder(hero4);
        //显示
        System.out.println("修改前的显示");
        singleLinked.list();
        //修改
        HeroNode newHeroNode = new HeroNode(2, "卢二哥", "玉麒麟！！！");
        singleLinked.update(newHeroNode);

        //显示
        System.out.println("修改后的显示");
        singleLinked.list();

        //删除
        singleLinked.del(1);
        singleLinked.del(4);
        singleLinked.del(3);
        singleLinked.del(2);
        System.out.println("删除后的显示");
        singleLinked.list();

    }
}

/**
 * 定义SingleLinkedList管理Hero
 */
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动,不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");
    //添加节点到单向链表

    /**
     * 分析思路:当不考虑编号顺序时
     * 1、找到当前链表的最后节点
     * 2、将最后这个节点的next域指向新节点
     */
    public void add(HeroNode heroNode) {

        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向链表的最后
        temp.next = heroNode;
    }

    //第二种添加hero节点方式
    //按照排名顺序将英雄插入到指定位置(如果有这个排名，则添加英雄失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此需要通过辅助变量来帮助找到添加的位置
        //因为单链表，我们找的temp是位于添加位置的前一个节点 否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到 就在temp后面插入
                //所以heroNode应该加在temp和temp.next之间
                break;
            }else if (temp.next.no == heroNode.no){
                //说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;//temp后移
        }
        //遍历完链表 判断flag是否能够添加
        if (flag){
            //不能添加，说明编号已经存在
            System.out.printf("准备插入的英雄的编号%d已经存在,不能加入\n",heroNode.no);
        }else {
            //插入到链表temp后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    /**
     * 修改节点信息 根据no编号来修改 即no编号不能改
     * 说明：
     *  1、根据newHeroNode的no来修改
     *
     */
    public void update(HeroNode newHeroNode){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }
        //根据no找到需要修改的节点
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if (temp == null){
                break;//说明 已经遍历到最后一个
            }
            if (temp.no == newHeroNode.no){
                //说明找到需要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根据flag 判断是否找到需要修改的节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            //没有找到
            System.out.printf("没有找到编号%d的节点,不能修改\n",newHeroNode.no);
        }
    }

    //删除节点
    /**
     * 删除节点
     * 思路分析：
     *  1、找到需要删除的节点的前一个节点temp
     *  2、temp.next = temp.next.next
     *  3、被删除的节点将不会有其他引用指向，会被垃圾回收机制回收
     * 说明：
     *  在比较时，是temp.next.no和需要删除的no进行比较
     */
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除的节点
        while (true){
            if (temp.next == null){//已经到链表的最后
                break;
            }
            if (temp.next.no == no){
                //找到需要删除的节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("根据编号%d没有找到待删除的节点,无法删除\n",no);
        }

    }

    /**
     * 显示链表[遍历]
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //temp后移
            temp = temp.next;

        }

    }

    //获取到单链表的节点个数(如果是带头节点的链表，需求不统计头节点)
    /**
     *
     * @param head 链表的头节点
     * @return 返回有效节点个数
     */
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
         return length;
    }

}


/**
 * 定义HeroNode，每个HeroNode对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    /**
     * 构造器
     *
     * @param no
     * @param name
     * @param nickName
     */
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
