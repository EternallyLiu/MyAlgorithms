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

    /**
     *反转输出一个单链表（双向链表我们这里作为单链表使用）
     * 遍历反向输出单链表
     */
    public Node ReserverNodeList1(Node node) {
        if (node == null && node.next == null) {
            return node;
        }
        Node pre = node;//保存上一节点
        Node cur = node.next;//保存当前节点
        Node tmp = null;//临时结点，用于保存当前结点的指针域（即下一结点）
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = tmp;
        }
        node.next = null;

        return pre;
    }

    /**
     *反转输出一个单链表（双向链表我们这里作为单链表使用）
     * 递归反向输出单链表
     */
    public Node ReserverNodeList2(Node node){
        if (node == null && node.next == null) {
            return node;
        }
        Node reNode = ReserverNodeList2(node);//找到当前node节点的后一个节点，从尾节点开始
        reNode.next = node;//使后面的节点指向前面的节点
        node.next = null;//将前面的节点置为null
        return node;
    }

    /**
     * 刪除单链表含有相同元素的节点
     * */
    public Node deleteDuplication(Node head){
        if (head == null && head.next == null) {
            return head;
        }
        Node cur = null;
        //含有重复元素
        if (head.e == head.next.e) {
            cur = head.next.next;
            //我们直接跳过含有重复元素的节点，使其直接指向第一个不是重复元素的节点，即遍历到没有重复结点的位置
            while (cur != null && cur.e == head.e){
                cur = cur.next;
            }
            return deleteDuplication(cur);
        }
        //不含有重复元素，直接将下一个位置进行递归调用
        cur = head.next;
        head.next = deleteDuplication(cur);
        return head;
    }
}
