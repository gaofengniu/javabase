package com.casic.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author niugaofeng
 * @version 1.0
 * @ClassName: QueueDemo
 * @Description TODO
 * @date 2019-09-23 13:37
 **/
public class QueueDemo {

    public static void main(String[] args) throws Exception {

        BlockingQueue<String> stringSynchronousQueue=new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t put a");
                stringSynchronousQueue.put("a");

                System.out.println(Thread.currentThread().getName()+"\t put b");
                stringSynchronousQueue.put("b");

                System.out.println(Thread.currentThread().getName()+"\t put c");
                stringSynchronousQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {

            try {
                try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t take");
                stringSynchronousQueue.take();

                try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t take");
                stringSynchronousQueue.take();

                try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t take");
                stringSynchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "BBB").start();

//        BlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<>(3);
//        System.out.println(arrayBlockingQueue.offer("a", 2, TimeUnit.SECONDS));
//        System.out.println(arrayBlockingQueue.offer("a", 2, TimeUnit.SECONDS));
//        System.out.println(arrayBlockingQueue.offer("a", 2, TimeUnit.SECONDS));
//        System.out.println(arrayBlockingQueue.offer("a", 2, TimeUnit.SECONDS));
//        arrayBlockingQueue.put("a");
//        arrayBlockingQueue.take();
//        arrayBlockingQueue.take();
//        arrayBlockingQueue.take();
//        arrayBlockingQueue.take();
//        System.out.println(arrayBlockingQueue.offer("a"));
//        System.out.println(arrayBlockingQueue.offer("b"));
//        System.out.println(arrayBlockingQueue.offer("c"));
//        System.out.println(arrayBlockingQueue.offer("d"));
//        System.out.println(arrayBlockingQueue.peek());
//        System.out.println(arrayBlockingQueue.poll());
//        System.out.println(arrayBlockingQueue.poll());
//        System.out.println(arrayBlockingQueue.poll());
//        System.out.println(arrayBlockingQueue.poll());

    }
}
