package com.concurrent;


import com.concurrent.pool.db.ConnectionPool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {

    private static ConnectionPool pool = new ConnectionPool(10);

    private static CountDownLatch start = new CountDownLatch(1);

    private static CountDownLatch end;

    /**
     * 资源有限（连接池中有10个连接）的情况下，随着客户端线程的增加，客户端无法连接的比例逐渐升高。
     */
    public static void main(String[] args) throws InterruptedException {
        int count = 20;
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        AtomicInteger got = new AtomicInteger();
        AtomicInteger noGot = new AtomicInteger();
        for(int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, noGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total tread counts: " + threadCount);
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + noGot);
    }

    public static class ConnectionRunner implements Runnable{

        //循环重新获取连接的次数
        private int count;

        //成功获取到连接的次数
        private AtomicInteger got;

        //获取失败的次数
        private AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await(); //线程等待在此，用于使得所有线程能同时启动
            } catch (Exception ex) {

            }
            while (count > 0) {
                try {
                    Connection connection = pool.fectchConnection(100);
                    if(connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }

    }


}
