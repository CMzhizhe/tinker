package tinker.sample.android.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileSDCardUtil {
    public static FileSDCardUtil fileSDCardUtil;
    public final static String TinkerCacheDirs = "tinkerCacheDirs";//tinker保存的目录

    public static FileSDCardUtil getInstance() {
        if (fileSDCardUtil == null) {
            synchronized (FileSDCardUtil.class) {
                if (fileSDCardUtil == null) {
                    fileSDCardUtil = new FileSDCardUtil();
                }
            }
        }
        return fileSDCardUtil;
    }

 
    /**
     * @date 创建时间:2021/8/12 0012
     * @auther gaoxiaoxiong
     * @Descriptiion 获取沙盒目录下，tinker的缓存目录
     **/
    public String getSandboxPublickDiskTinkerCacheDir(Context context, String childDir){
        return getSandboxPublickDiskCacheDir(context.getApplicationContext(), TinkerCacheDirs + "/" + childDir);
    }



 
    /**
     * 作者：GaoXiaoXiong
     * 创建时间:2019/1/26
     * 注释描述:获取缓存目录
     * @fileName 获取沙盒存储目录下缓存的 fileName的文件夹路径
     */
    public String getSandboxPublickDiskCacheDir(Context context, String fileName) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {//此目录下的是外部存储下的私有的fileName目录
            cachePath = context.getExternalCacheDir().getPath() + "/" + fileName;  //SDCard/Android/data/你的应用包名/cache/fileName
        } else {
            cachePath = context.getCacheDir().getPath() + "/" + fileName;
        }
        File file = new File(cachePath);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath(); //SDCard/Android/data/你的应用包名/cache/fileName
    }

    /**
     * @date: 2019/8/2 0002
     * @author: gaoxiaoxiong
     * @description:获取沙盒存储目录下的 fileName的文件夹路径
     **/
    public String getSandboxPublickDiskFileDir(Context context, String fileName) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {//此目录下的是外部存储下的私有的fileName目录
            cachePath = context.getExternalFilesDir(fileName).getAbsolutePath();  //mnt/sdcard/Android/data/com.my.app/files/fileName
        } else {
            cachePath = context.getFilesDir().getPath() + "/" + fileName;        //data/data/com.my.app/files/fileName
        }
        File file = new File(cachePath);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();  //mnt/sdcard/Android/data/com.my.app/files/fileName
    }


    /**
     * @date :2020/3/17 0017
     * @author : gaoxiaoxiong
     * @description:获取公共目录，注意，只适合android9.0以下的
     **/
    public String getPublickDiskFileDirAndroid9(String fileDir) {
        String filePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            filePath = Environment.getExternalStoragePublicDirectory(fileDir).getPath();
        }
        File file = new File(filePath);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * @date 创建时间:2020/11/6 0006
     * @auther gaoxiaoxiong
     * @Descriptiion mnt/sdcard/ fileDir
     **/
    public String getPublickExternalStorageDirectory9(String fileDir){
        String filePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageDirectory())
                || !Environment.isExternalStorageRemovable()) {
            filePath = Environment.getExternalStorageDirectory().getPath();
        }
        File file = new File(filePath + fileDir);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


}
