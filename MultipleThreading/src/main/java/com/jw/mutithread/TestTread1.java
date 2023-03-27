package com.jw.mutithread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习使用多线程，同步下载多张图片；使用commons io依赖
public class TestTread1 extends Thread{

    private String url;
    private String filename;

    public TestTread1(String url,String filename){
        this.url = url;
        this.filename = filename;
    }

    //下载图片的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,filename);
        System.out.println("下载了文件，名为：" + filename);
    }

    public static void main(String[] args) {
        TestTread1 t1 = new TestTread1("https://t7.baidu.com/it/u=887525017,4144689287&fm=193&f=GIF", "fengjing1");
        TestTread1 t2 = new TestTread1("https://t7.baidu.com/it/u=2925116833,2991570114&fm=193&f=GIF", "fengjing2");
        TestTread1 t3 = new TestTread1("https://tenfei01.cfp.cn/creative/vcg/800/new/VCG211150818362.jpg", "fengjing3");

        //线程同时进行，不一定按顺序完整执行
        t1.start();
        t2.start();
        t3.start();
    }

}

//下载器
class WebDownloader{
    //下载方法
    public void downloader(String url,String filename) {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception, downloader方法下载出错");
        }
    }
}
