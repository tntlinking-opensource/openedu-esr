package com.ckfinder.connector.handlers.command;

import java.io.File;  
import java.util.ArrayList;  
import java.util.Calendar;  
import java.util.List;  
  

public class FfmpegTest {
     private final static String PATH = "E:\\ffmpeg\\c.mp4";  
      
        public static void main(String[] args) {  
            if (!checkfile(PATH)) {   //判断路径是不是一个文件
                System.out.println(PATH + " is not file");  
                return;  
            }  
            if (process()) {        //执行转码任务
                System.out.println("ok");  
            }  
        }  
      
        private static boolean checkfile(String path) {  
            File file = new File(path);  
            if (!file.isFile()) {  
                return false;  
            }  
            return true;  
        }  
        
        private static boolean process() { 
            // 判断视频的类型
            int type = checkContentType();  
            boolean status = false;  
            //如果是ffmpeg可以转换的类型直接转码，否则先用mencoder转码成AVI
            if (type == 0) {  
                System.out.println("直接将文件转为flv文件");  
                status = processFLV(PATH);// 直接将文件转为flv文件  
            } else if (type == 1) {  
                String avifilepath = processAVI(type);  
                if (avifilepath == null)  
                    return false;// avi文件没有得到  
                status = processFLV(avifilepath);// 将avi转为flv  
            }  
            return status;  
        }  
      
        private static int checkContentType() {  
            String type = PATH.substring(PATH.lastIndexOf(".") + 1, PATH.length())  
                    .toLowerCase();  
            // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
            if (type.equals("avi")) {  
                return 0;  
            } else if (type.equals("mpg")) {  
                return 0;  
            } else if (type.equals("wmv")) {  
                return 0;  
            } else if (type.equals("3gp")) {  
                return 0;  
            } else if (type.equals("mov")) {  
                return 0;  
            } else if (type.equals("mp4")) {  
                return 0;  
            } else if (type.equals("asf")) {  
                return 0;  
            } else if (type.equals("asx")) {  
                return 0;  
            } else if (type.equals("flv")) {  
                return 0;  
            }  
            // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),  
            // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.  
            else if (type.equals("wmv9")) {  
                return 1;  
            } else if (type.equals("rm")) {  
                return 1;  
            } else if (type.equals("rmvb")) {  
                return 1;  
            }  
            return 9;  
        }  
           
      
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.  
        private static String processAVI(int type) {  
            List<String> commend = new ArrayList<String>();  
            commend.add("E:\\ffmpeg\\mencoder");  
            commend.add(PATH);  
            commend.add("-oac");  
            commend.add("lavc");  
            commend.add("-lavcopts");  
            commend.add("acodec=mp3:abitrate=64");  
            commend.add("-ovc");  
            commend.add("xvid");  
            commend.add("-xvidencopts");  
            commend.add("bitrate=600");  
            commend.add("-of");  
            commend.add("avi");  
            commend.add("-o");  
            commend.add("【存放转码后视频的路径，记住一定是.avi后缀的文件名】");  
            try {  
                //调用线程命令启动转码
                ProcessBuilder builder = new ProcessBuilder();  
                builder.command(commend);  
                builder.start();  
                return "【存放转码后视频的路径，记住一定是.avi后缀的文件名】";  
            } catch (Exception e) {  
                e.printStackTrace();  
                return null;  
            }  
        }  
      
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
        private static boolean processFLV(String oldfilepath) {  
      
            if (!checkfile(PATH)) {  
                System.out.println(oldfilepath + " is not file");  
                return false;  
            }  
              
            // 文件命名  
            Calendar c = Calendar.getInstance();  
            String savename = String.valueOf(c.getTimeInMillis())+ Math.round(Math.random() * 100000);  
            List<String> commend = new ArrayList<String>();  
            commend.add("E:\\ffmpeg\\ffmpeg");  
            commend.add("-i");  
            commend.add(oldfilepath);  
            commend.add("-ab");  
            commend.add("56");  
            commend.add("-ar");  
            commend.add("22050");  
            commend.add("-qscale");  
            commend.add("8");  
            commend.add("-r");  
            commend.add("15");  
            commend.add("-s");  
            commend.add("600x500");  
            commend.add("【存放转码后视频的路径，记住一定是.flv后缀的文件名】");  
      
            try {  
                Runtime runtime = Runtime.getRuntime();  
                Process proce = null; 
                //视频截图命令，封面图。  8是代表第8秒的时候截图
                String cmd = "";  
                String cut = "     E:\\ffmpeg\\ffmpeg.exe   -i   "  
                        + oldfilepath  
                        + "   -y   -f   image2   -ss   8   -t   0.001   -s   600x500   E:\\ffmpeg\\"  
                        + "a.jpg";  
                String cutCmd = cmd + cut;  
                proce = runtime.exec(cutCmd);  
                //调用线程命令进行转码
                ProcessBuilder builder = new ProcessBuilder(commend);                 
                 builder.command(commend);  
                builder.start();  
      
                return true;  
            } catch (Exception e) {  
                e.printStackTrace();  
                return false;  
            }  
        }  
}