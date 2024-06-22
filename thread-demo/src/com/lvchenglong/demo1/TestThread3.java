package com.lvchenglong.demo1;

// 创建线程方式二：实现Runnable接口，重写run方法，执行线程需要丢入Runnable接口实现类，调用start方法

public class TestThread3 implements Runnable{
    @Override
    public void run(){
        // run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码---" + i);
        }
    }

    public static void main(String[] args){

        // 创建Runnable接口实现类对象
        TestThread3 testThread3 = new TestThread3();

        // 创建线程对象，通过线程对象来开启我们的线程，代理
        Thread thread = new Thread(testThread3);

        // 调用start方法，开启线程
        thread.start();

        // 简写
        // new Thread(testThread3).start();

        // main线程，主线程
        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程---" + i);
        }
    }
}
