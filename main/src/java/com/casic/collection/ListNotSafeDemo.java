package com.casic.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author niugaofeng
 * @version 1.0
 * @ClassName: ListNotSafeDemo
 * @Description TODO
 * @date 2019-09-19 14:42
 **/
public class ListNotSafeDemo {

    public static void main(String[] args) {

        List<String> list= new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();

        }
    }
}
