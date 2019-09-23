package com.casic.collection;

import java.util.concurrent.CountDownLatch;

/**
 * @author niugaofeng
 * @version 1.0
 * @ClassName: LockDemo
 * @Description TODO
 * @date 2019-09-23 10:05
 **/
public class LockDemo {

    public static void main(String[] args) throws InterruptedException {

//        CountDownLatch countDownLatch=new CountDownLatch(6);
//
//        for (int i = 1; i <= 6; i++) {
//
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName()+"\t ");
//                countDownLatch.countDown();
//            }, String.valueOf(i)).start();
//
//        }
//
//        countDownLatch.await();
//
//        System.out.println("------完成了-------");

        MyCircularQueue myCircularQueue=new MyCircularQueue(4);
        myCircularQueue.enQueue(1);
        myCircularQueue.enQueue(2);
        myCircularQueue.enQueue(3);
        myCircularQueue.enQueue(3);

        myCircularQueue.deQueue();
        myCircularQueue.deQueue();
        myCircularQueue.deQueue();

        myCircularQueue.enQueue(1);
        myCircularQueue.enQueue(2);
        myCircularQueue.enQueue(3);


    }
}

class MyCircularQueue {

    private int[] data;
    private int head;
    private int tail;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        data = new int[k];
        head = -1;
        tail = -1;
        size = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull() == true) {
            return false;
        }
        if (isEmpty() == true) {
            head = 0;
        }
        System.out.println("----前---->"+tail+"\t size="+size+"\t tial+1="+(tail+1));
        tail = (tail + 1) % size;
        System.out.println("----后---->"+tail);
        data[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty() == true) {
            return -1;
        }
        return data[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty() == true) {
            return -1;
        }
        return data[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head == -1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }
}
