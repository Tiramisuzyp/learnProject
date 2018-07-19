package com.concurrent.pool.db;


import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectionPool(int initPoolSize) {
        if(initPoolSize > 0){
            for(int i = 0; i < initPoolSize; i++){
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 释放连接，并通知其他等待中的线程
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fectchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            //无限超时
            if(mills < 0) {
                while(pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills; //超时时间
                while(pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining); //等待时长为remaining毫秒后，线程再次从等待队列回到同步队列。此时等待时长已经耗光，退出循环。
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }


}
