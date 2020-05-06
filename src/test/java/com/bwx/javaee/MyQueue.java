package com.bwx.javaee;

public class MyQueue {
    private Object[] queArray;
    private int maxSize;
    private int front;
    private int rear;
    private int nItems;

    public MyQueue(int s) {
        maxSize = s;
        queArray = new Object[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;

    }

    public void insert(int value) {
        if (isFull()) {
            System.out.println("no space");
        } else {
            if (rear == maxSize - 1) {
                rear = -1;
            }
            queArray[++rear] = value;
            nItems++;
        }

    }

    public Object remove() {
        Object removeVal = null;
        if (!isEmpty()) {
            removeVal = queArray[front];
            queArray[front] = null;
            front++;
            if (front == maxSize) {
                front = 0;
            }
            nItems--;
            return removeVal;
        }
        return removeVal;
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public Object peekFront() {
        return queArray[front];
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return (nItems == 0);
    }
}
