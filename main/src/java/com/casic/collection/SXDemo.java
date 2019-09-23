package com.casic.collection;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author niugaofeng
 * @version 1.0
 * @ClassName: SXDemo
 * @Description TODO
 * @date 2019-09-23 14:50
 **/
public class SXDemo {

    public static void main(String[] args) {
        ShareData shareData=new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 2; i++) {
                shareData.increment();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.decrement();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.decrement();
            }
        }, "CC").start();
    }
}

class ShareData{

    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void increment() {

       lock.lock();
       try{
           while (number!=0){
               condition.await();
           }

           number+=5;

           System.out.println(Thread.currentThread().getName()+"\t 生产一个="+number);

           condition.signalAll();

       }catch (Exception e){
           e.printStackTrace();
       }finally{
           lock.unlock();
       }

    }

    public void decrement() {

        lock.lock();
        try{
            while (number ==0){
                condition.await();
            }

            number--;

            System.out.println(Thread.currentThread().getName()+"\t 消费一个="+number);

            condition.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

}
