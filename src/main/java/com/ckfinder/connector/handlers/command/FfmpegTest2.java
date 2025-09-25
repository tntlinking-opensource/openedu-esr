package com.ckfinder.connector.handlers.command;

import java.io.File;

import org.apache.commons.codec.EncoderException;

public class FfmpegTest2 {
    public static void main(String[] args) {
        String sPath = "D:\\mp3test\\2.amr";
        String tPath = "D:\\mp3test\\2.mp3";
        try {
            new FfmpegTest2().changeAmrToMp3(sPath,tPath);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }

    public void changeAmrToMp3(String sourcePath, String targetPath) throws IllegalArgumentException, EncoderException {
        String webroot = "D:\\javaffmpeg\\FFmpeg\\bin";
        Runtime run = null;
        try {
            run = Runtime.getRuntime();
            long start=System.currentTimeMillis();
            System.out.println(new File(webroot).getAbsolutePath());
            //执行ffmpeg.exe,前面是ffmpeg.exe的地址，中间是需要转换的文件地址，后面是转换后的文件地址。-i是转换方式，意思是可编码解码，mp3编码方式采用的是libmp3lame
            Process p=run.exec(new File(webroot).getAbsolutePath()+"/ffmpeg -i "+sourcePath+" -acodec libmp3lame "+targetPath);
            //释放进程
            p.getOutputStream().close();
            p.getInputStream().close();
            p.getErrorStream().close();
            p.waitFor();
            long end=System.currentTimeMillis();
            System.out.println(sourcePath+" convert success, costs:"+(end-start)+"ms");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //run调用lame解码器最后释放内存
            run.freeMemory();
        }
    }
}
