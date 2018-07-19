package com.concurrent.pool.thread;

import java.util.LinkedList;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    //线程池最大限制数
    private static final int MAX_WORKER_NUMERS = 10;

    //线程池默认工作线程数
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    //线程池最小限制数
    private static final int MIN_WORKER_NUMBERS = 1;

    //JobList：任务列表
    private LinkedList<Job> jobs = new LinkedList();

    //WorkerList：工作线程列表
    private LinkedList<Worker> workers;

    public DefaultThreadPool(int num) {

    }

    /**
     * 提交任务
     * @param job
     */
    @Override
    public void excute(Job job) {
        //锁住jobs

        //notify：通知工作线程
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void addWorkers(int num) {

    }

    @Override
    public void removeWorkers(int num) {

    }

    /**
     * 初始化线程池
     * @param num
     */
    private void initThreadPool(int num) {

    }


    class Worker implements Runnable {

        @Override
        public void run() {
            //消费jobs


        }
    }

}
