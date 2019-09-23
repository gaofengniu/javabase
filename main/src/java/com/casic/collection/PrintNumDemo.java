package com.casic.collection;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author niugaofeng
 * @version 1.0
 * @ClassName: PrintNumDemo
 * @Description TODO
 * @date 2019-09-23 15:13
 **/
public class PrintNumDemo {

    public static void main(String[] args) {

        ShareData1 shareData1=new ShareData1();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareData1.print5();
            }
        }, "AAA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareData1.print10();
            }
        }, "BBB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareData1.print15();
            }
        }, "CCC").start();

    }

}

class ShareData1{

    private int flag=1;
    private Lock lock=new ReentrantLock();
    private Condition conditionA=lock.newCondition();
    private Condition conditionB=lock.newCondition();
    private Condition conditionC=lock.newCondition();

    public void print5(){

        lock.lock();
        try{

            while (flag!=1){
                conditionA.await();
            }

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+i);
            }

            flag=2;
            conditionB.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print10(){

        lock.lock();
        try{

            while (flag!=2){
                conditionB.await();
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+i);
            }

            flag=3;
            conditionC.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(){

        lock.lock();
        try{

            while (flag!=3){
                conditionC.await();
            }

            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+i);
            }

            flag=1;
            conditionA.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }


}
