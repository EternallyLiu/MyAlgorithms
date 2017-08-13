package com.example;

/**
 * Created by Liu Pei on 2017/8/13.
 * 这里实现一个自定义的双向链表
 */

public class DoubleLinkedList<E> {
    private Node first;
    private Node last;
    private int length = 0;

    class Node{
            private Node previous;
            private Node next;
            private E e;

            public Node(Node previous, Node next, E e) {
                this.previous = previous;
                this.next = next;
                this.e = e;
            }
        }

    /**
     * 向其中某一个特定节点前插入元素
     */
    public void insertPrevious(E baseElement, E value) {
        Node index = this.first;
        while (index != null) {
            if(index.e == baseElement) break;
            index = index.next;
        }
        Node insertValue = new Node(index.previous,index,value);
        index.previous.next = insertValue;
        index.previous = insertValue;
        length++;
    }

    /**
     * 向某一个特定节点之后插入元素
     */
    public void insertLast(E baseElement, E e) {
        Node index = this.first;
        while (index != null) {
            if(index.e == baseElement) break;
            index = index.next;
        }
        Node insertValue = new Node(index, index.next, e);
        index.next = insertValue;
        index.next.previous = index;
        length++;
    }

    /**
     * 删除一个特定位置的元素
     */
    public void removeElement(E value) {
        Node index = this.first;
        while (index != null) {
            if(index.e == value) break;
            index = index.next;
        }

        index.previous.next = index.next;
        index.next.previous = index.previous;
        length--;
    }

    /**
     * 获取到链表的长度
     * */
    public int getLength() {
        return length;
    }

    /***
     *反转输出一个单链表（双向链表我们这里作为单链表使用）
     */
    public void ReserverNodeList(Node node) {
        Node returnNode = null;
        while (node != null) {
            Node preNode = node.next;
            node.next = returnNode;
            returnNode = node;
            node = preNode;
        }
    }
}
