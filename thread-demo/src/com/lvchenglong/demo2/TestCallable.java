package com.lvchenglong.demo2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

// 线程创建方式三：实现Callable接口

/**
 * Callable的好处
 * 1. 可以定义返回值
 * 2. 可以抛出异常
 */
public class TestCallable implements Callable<Boolean> {
    private String url;  // 网络图片地址
    private String name;  // 保存的文件名

    // 构造器
    public TestCallable(String url,String name){
        this.url = url;
        this.name = name;
    }

    // 下载图片线程的执行体
    @Override
    public Boolean call(){
        WebDownLoader webDownLoader = new WebDownLoader();
        try {
            webDownLoader.downloader(url,name);
            System.out.println("下载了文件名为" + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://dongping-blog.oss-cn-hangzhou.aliyuncs.com/systemImage/1cb3d9ad7ec64d45895bde132002a0ed.png","1.png");
        TestCallable t2 = new TestCallable("https://dongping-blog.oss-cn-hangzhou.aliyuncs.com/maxEmoticon/606a01291ba74a219bdeb6a34ef4175d.png","2.png");
        TestCallable t3 = new TestCallable("https://dongping-blog.oss-cn-hangzhou.aliyuncs.com/appImage/5adec1eb48a64214a2f8282facf26abf.png","3.png");

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);
        // 获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();
        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);
        // 关闭服务
        ser.shutdownNow();
    }
}

class WebDownLoader{
    // 下载方法
    public void downloader(String url,String name) throws IOException {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
