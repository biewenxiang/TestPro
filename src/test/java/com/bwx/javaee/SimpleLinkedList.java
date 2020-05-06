package com.bwx.javaee;

import clojure.lang.Obj;
import org.junit.Test;

public class SimpleLinkedList {
    //链表长度
    private int size;
    private Node head;//头结点

    public SimpleLinkedList() {
        size = 0;
        head = null;
    }

    private class Node {
        private Object object;
        private Node next;

        public Node(Object object) {
            this.object = object;
        }
    }

    public Object addHead(Object o) {

        Node node = new Node(o);
        if (size == 0) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;

        return o;
    }

    public Object delHead() {
        Object o = head.object;
        head = head.next;
        size--;
        return o;
    }

    public Node find(Object o) {
        Node current = head;
        int lenght = size;
        while (lenght > 0) {
            if (current.object.equals(o)) {
                return current;
            } else {
                current = current.next;
            }
            lenght--;
        }
        return null;
    }

    public Boolean delete(Object o) {
        Node prev = head;
        Node current = head;
        Node next = current.next;

        int lenght = size;

        while (lenght > 0) {
            if (current.object.equals(o)) {
                prev.next = next;
                size--;
                return true;
            } else {
                prev = current;
                current = current.next;
                next = current.next;
            }
            lenght--;
        }
        return false;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return (size == 0);
    }
    //显示节点信息
    public void display(){
        if(size >0){
            Node node = head;
            int tempSize = size;
            if(tempSize == 1){//当前链表只有一个节点
                System.out.println("["+node.object+"]");
                return;
            }
            while(tempSize>0){
                if(node.equals(head)){
                    System.out.print("["+node.object+"->");
                }else if(node.next == null){
                    System.out.print(node.object+"]");
                }else{
                    System.out.print(node.object+"->");
                }
                node = node.next;
                tempSize--;
            }
            System.out.println();
        }else{//如果链表一个节点都没有，直接打印[]
            System.out.println("[]");
        }

    }
    @Test
    public void testSingleLinkedList(){
        SimpleLinkedList singleList = new SimpleLinkedList();
        singleList.addHead("A");
        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        //打印当前链表信息
        singleList.display();
        //删除C
        singleList.delete("C");
        singleList.display();
        //查找B
        System.out.println(singleList.find("B"));
    }
}
