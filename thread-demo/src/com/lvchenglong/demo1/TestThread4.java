package com.lvchenglong.demo1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

// 实现多线程同步下载图片
public class TestThread4 implements Runnable{
    private String url;  // 网络图片地址
    private String name;  // 保存的文件名

    // 构造器
    public TestThread4(String url,String name){
        this.url = url;
        this.name = name;
    }

    // 下载图片线程的执行体
    @Override
    public void run(){
        WebDownloader webDownloader = new WebDownloader();
        try {
            webDownloader.downloader(url,name);
            System.out.println("下载了文件名为" + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        TestThread4 t1 = new TestThread4("https://dongping-blog.oss-cn-hangzhou.aliyuncs.com/systemImage/1cb3d9ad7ec64d45895bde132002a0ed.png","1.png");
        TestThread4 t2 = new TestThread4("https://dongping-blog.oss-cn-hangzhou.aliyuncs.com/maxEmoticon/606a01291ba74a219bdeb6a34ef4175d.png","2.png");
        TestThread4 t3 = new TestThread4("https://dongping-blog.oss-cn-hangzhou.aliyuncs.com/appImage/5adec1eb48a64214a2f8282facf26abf.png","3.png");
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
}

// 下载器
class WebDownloader{
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
