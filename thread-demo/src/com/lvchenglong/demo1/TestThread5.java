package com.lvchenglong.demo1;

// 多线程同时操作同一个对象
// 例子：买火车票

// 发现问题：多个线程操作同一个资源的情况下，线程不安全，数据紊乱（不同的人抢到同一张票）
public class TestThread5 implements Runnable{

    // 票数
    private int ticketNums = 10;

    @Override
    public void run(){
        while(true){
            // 对当前对象进行同步
            // 确保每次只有一个线程可以执行同步块中的代码，从而避免了多个线程同时操作ticketNums导致的数据不一致问题。
            // 对票数检查和更新进行同步
            synchronized (this){
                if(ticketNums <= 0){
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "-->拿到了第" + ticketNums-- + "票");
            }
            // 模拟延时
            try {
                Thread.sleep(200);  // 0.2s
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args){
        TestThread5 ticket = new TestThread5();

        new Thread(ticket,"小明").start();
        new Thread(ticket,"老师").start();
        new Thread(ticket,"黄牛").start();
    }
}
