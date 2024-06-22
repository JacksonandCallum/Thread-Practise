package com.lvchenglong.demo1;

// 模拟龟兔赛跑
public class Race implements Runnable{
    // 胜利者
    // static 表明只能有一个胜利者（static只执行一次）
    private static String winner;

    @Override
    public void run(){
        //跑到一共100米
        for (int i = 0; i <= 100; i++) {
            // 模拟兔子休息（每10步休息一次）
            if(Thread.currentThread().getName().equals("兔子") && i%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 判断比赛是否结束
            boolean flag = gameOver(i);
            // 如果比赛结束，停止程序
            if(flag){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "-->跑了" + i + "米");
        }
    }

    // 判断是否完成比赛
    private boolean gameOver(int steps){
        // 判断是否有胜利者
        if(winner != null){ // 已经存在胜利者
            return true;
        }else {
            if(steps >= 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Race race = new Race();

        // "兔子"是这个线程的名称
        new Thread(race,"兔子").start();
        // "乌龟"是这个线程的名称
        new Thread(race,"乌龟").start();
    }
}
