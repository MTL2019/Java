package com.jw.mutithread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class CreateThread3 implements Callable<Boolean> {


    private String url;
    private String filename;

    public CreateThread3(String url,String filename){
        this.url = url;
        this.filename = filename;
    }

    //下载图片的执行体
    @Override
    public Boolean call() {
        WebDownloader1 webDownloader = new WebDownloader1();
        webDownloader.downloader(url,filename);
        System.out.println("下载了文件，名为：" + filename);

        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CreateThread3 t1 = new CreateThread3("https://t7.baidu.com/it/u=887525017,4144689287&fm=193&f=GIF", "fengjing1");
        CreateThread3 t2 = new CreateThread3("https://t7.baidu.com/it/u=2925116833,2991570114&fm=193&f=GIF", "fengjing2");
        CreateThread3 t3 = new CreateThread3("https://tenfei01.cfp.cn/creative/vcg/800/new/VCG211150818362.jpg", "fengjing3");

        //创建执行服务
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> b1 = executor.submit(t1);
        Future<Boolean> b2 = executor.submit(t2);
        Future<Boolean> b3 = executor.submit(t3);

        //获取结果
        Boolean r1 = b1.get();
        Boolean r2 = b2.get();
        Boolean r3 = b3.get();
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);

        //结束服务
        executor.shutdownNow();

    }
}

//下载器
class WebDownloader1{
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
