package com.bxh.algorithms.node;

/**
 * Created by bxh on 1/5/17.
 */

/**
 * 双向链表
 */
public class DoublyLinkedList<T> {
    private LinkNode<T> head;       //首结点
    private LinkNode<T> rear;       //尾部指针

    public DoublyLinkedList() {
    }

    public T peekHead() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertFirst(T data) {// 插入 到 链头
        LinkNode<T> newLinkNode = new LinkNode<T>(data);
        if (isEmpty()) {//为空时，第1次插入的新结点为尾结点
            rear = newLinkNode;
        } else {
            head.previous = newLinkNode; //旧头结点的上结点等于新结点
        }
        newLinkNode.next = head; //新结点的下结点旧头结点
        head = newLinkNode; //赋值后，头结点的下结点是旧头结点，上结点null
    }

    public void insertLast(T data) {//在链尾 插入
        LinkNode<T> newLink = new LinkNode<T>(data);
        if (isEmpty()) {
            head = newLink;
        } else {
            rear.next = newLink;
        }
        newLink.previous = rear;
        rear = newLink; //赋值后，尾结点的上结点是旧尾结点，下结点null
    }

    public T deleteHead() {//删除 链头
        if (isEmpty()) return null;
        LinkNode<T> temp = head;
        head = head.next; //变更首结点，为下一结点
        if (head != null) {
            head.previous = null;
        } else {
            rear = null;
        }
        return temp.data;
    }

    public T deleteRear() {//删除 链尾
        if (isEmpty()) return null;
        LinkNode<T> temp = rear;
        rear = rear.previous; //变更尾结点，为上一结点
        if (rear != null) {
            rear.next = null;
        } else {
            head = null;
        }
        return temp.data;
    }

    public T find(T t) {//从头到尾find
        if (isEmpty()) {
            return null;
        }
        LinkNode<T> find = head;
        while (find != null) {
            if (!find.data.equals(t)) {
                find = find.next;
            } else {
                break;
            }
        }
        if (find == null) {
            return null;
        }
        return find.data;
    }

    public T delete(T t) {
        if (isEmpty()) {
            return null;
        }
        LinkNode<T> current = head;
        while (!current.data.equals(t)) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        if (current == head) {
            head = head.next;
            if (head != null) {
                head.previous = null;
            }
        } else if (current == rear) {
            rear = rear.previous;
            if (rear != null) {
                rear.next = null;
            }
        } else {
            //中间的非两端的结点，要移除current
            current.next.previous = current.previous;
            current.previous.next = current.next;
        }
        return current.data;
    }

    public boolean insertAfter(T key, T data) {//插入在key之后, key不存在return false
        if (isEmpty()) {
            return false;
        }
        LinkNode<T> current = head;
        while (!current.data.equals(key)) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }
        LinkNode<T> newLink = new LinkNode<T>(data);
        if (current == rear) {
            rear = newLink;
        } else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        current.next = newLink;
        newLink.previous = current;
        return true;
    }

    public void displayList4Head() {//从头开始遍历
        System.out.println("List (first-->last):");
        LinkNode<T> current = head;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    public void displayList4Rear() {//从尾开始遍历
        System.out.println("List (last-->first):");
        LinkNode<T> current = rear;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
    }

    class LinkNode<T> {//链结点
        T data;     //数据域
        LinkNode<T> next; //后继指针，结点         链域
        LinkNode<T> previous; //前驱指针，结点     链域

        LinkNode(T data) {
            this.data = data;
        }

        void displayLink() {
            System.out.println("the data is " + data.toString());
        }
    }
}
