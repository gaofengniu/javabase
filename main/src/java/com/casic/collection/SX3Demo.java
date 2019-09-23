package com.casic.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author niugaofeng
 * @version 1.0
 * @ClassName: SX3Demo
 * @Description TODO
 * @date 2019-09-23 15:29
 **/
public class SX3Demo {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue=new LinkedBlockingDeque<>(10);

        MyResource myResource=new MyResource(blockingQueue);

        new Thread(() -> {
            System.out.println("生产线程启动");
            try {
                myResource.myProud();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println("消费者线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}

         myResource.stop();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5s停止");

    }
}

class MyResource{

  private volatile boolean flag=true;
  private AtomicInteger number=new AtomicInteger();

  BlockingQueue<String> blockingQueue=null;

  public MyResource(BlockingQueue<String> blockingQueue){
      System.out.println(blockingQueue.getClass().getName());
      this.blockingQueue=blockingQueue;
  }

  public void myProud() throws Exception {

      String data="";
      boolean rs=false;

      while (flag){
          data=number.incrementAndGet()+"";
          rs=blockingQueue.offer(data,2, TimeUnit.SECONDS);
          System.out.println("-----------");
          if(rs){
              System.out.println(Thread.currentThread().getName()+"\t插入队列成功:"+data);
          }else{
              System.out.println(Thread.currentThread().getName()+"\t插入队列失败");
          }
           try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
      }
      System.out.println("---------总开关关闭了--------"+flag);
  }

  public void myConsumer()throws Exception{

      String data="";

      while (flag){
         data=blockingQueue.poll(2,TimeUnit.SECONDS);
         if(data==null|| data.equalsIgnoreCase("")){
             System.out.println("消费者退出");
             flag=false;
             return;
         }

          System.out.println(Thread.currentThread().getName()+"\t获取成功："+data);
      }

      System.out.println("总开关关闭了");

  }

  public void stop(){
      this.flag=false;
  }

}
