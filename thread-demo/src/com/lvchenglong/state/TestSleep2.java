package com.lvchenglong.state;

import java.text.SimpleDateFormat;
import java.util.Date;

// 模拟倒计时
public class TestSleep2{

    public static void tenDown() throws InterruptedException {
        int num = 10;
        Date startTime = new Date(System.currentTimeMillis());  // 获取当前系统时间
        while (true){
            Thread.sleep(1000);
            System.out.println(num--);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
            startTime = new Date(System.currentTimeMillis()); // 更新当前时间
            System.out.println("----------");
            if (num <= 0){
                break;
            }
        }
    }

    public static void main(String[] args){
        try {
            tenDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
