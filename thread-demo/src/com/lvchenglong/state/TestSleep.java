package com.lvchenglong.state;

// 模拟网络延时：放大问题的发生性
public class TestSleep implements Runnable{
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
                Thread.sleep(100);  // 0.1s
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args){
        TestSleep ticket = new TestSleep();

        new Thread(ticket,"小明").start();
        new Thread(ticket,"老师").start();
        new Thread(ticket,"黄牛").start();
    }
}
