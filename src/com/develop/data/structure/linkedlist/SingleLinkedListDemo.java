package com.develop.data.structure.linkedlist;

/**
 * @author changchen
 * @create 2020-07-26 9:52
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"公孙胜","入云龙");

        SingleLinkedList singleLinked = new SingleLinkedList();
        singleLinked.add(hero1);
        singleLinked.add(hero2);
        singleLinked.add(hero3);
        singleLinked.add(hero4);
        //显示
        singleLinked.list();

    }
}

/**
 * 定义SingleLinkedList管理Hero
 */
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动,不存放具体数据
    private HeroNode head = new HeroNode(0,"","");
    //添加节点到单向链表
    /**
     * 分析思路:当不考虑编号顺序时
     *  1、找到当前链表的最后节点
     *  2、将最后这个节点的next域指向新节点
     */
    public void add(HeroNode heroNode){

        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if (temp.next == null){
                break;
            }
            //如果没有找到temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向链表的最后
        temp.next = heroNode;
    }

    /**
     * 显示链表[遍历]
     */
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            //判断是否到链表最后
            if (temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //temp后移
            temp = temp.next;

        }

    }


}


/**
 * 定义HeroNode，每个HeroNode对象就是一个节点
 */
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    /**
     * 构造器
     * @param no
     * @param name
     * @param nickName
     */
    public HeroNode(int no,String name,String nickName){
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
