package com.concurrent.pool.thread;

/*
 * 线程池接口
 */
public interface ThreadPool<Job extends Runnable> {

    //提交一个任务
    void excute(Job job);

    //关闭线程池
    void shutdown();

    //增加工作线程
    void addWorkers(int num);

    //减少工作线程
    void removeWorkers(int num);


}
