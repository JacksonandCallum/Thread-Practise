package com.lvchenglong.state;

/**
 * 侧式线程礼让
 * 礼让不一定成功，看cpu心情
 */
public class TestYield {
    public static void main(String[] args){
        myYield myYield = new myYield();
        new Thread(myYield,"a").start();
        new Thread(myYield,"b").start();
    }
}

class myYield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程结束执行");
    }
}
