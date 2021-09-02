package tinker.sample.android.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MOVIES;
import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.DIRECTORY_PICTURES;

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
     * @date: 2019/5/22 0022
     * @author: gaoxiaoxiong
     * @description:获取沙盒目录下图片位置
     **/
    public String getSandboxPublickDiskImagePicDir(Context context) {
        return getSandboxPublickDiskFileDir(context.getApplicationContext(), DIRECTORY_PICTURES);
    }


    /**
     * @date: 2019/5/22 0022
     * @author: gaoxiaoxiong
     * @description: 获取沙盒目录下电影位置
     **/
    public String getSandboxPublickDiskMoviesDir(Context context) {
        return getSandboxPublickDiskFileDir(context.getApplicationContext(), DIRECTORY_MOVIES);
    }

    /**
     * @date :2019/12/16 0016
     * @author : gaoxiaoxiong
     * @description:获取沙盒目录下音乐位置
     **/
    public String getSandboxPublickDiskMusicDir(Context context) {
        return getSandboxPublickDiskFileDir(context.getApplicationContext(), DIRECTORY_MUSIC);
    }


    /**
     * 作者：GaoXiaoXiong
     * 创建时间:2019/7/21
     * 注释描述:沙盒目录下文件保存位置
     */
    public String getSandboxPublickDiskDownLoadsDir(Context context) {
        return getSandboxPublickDiskFileDir(context.getApplicationContext(), DIRECTORY_DOWNLOADS);
    }



    /**
     * @date 创建时间:2018/12/20
     * @author GaoXiaoXiong
     * @Description: 获取沙盒目录下的图片
     */
    public String getSandboxPublickDiskImagePicCacheDir(Context context) {
        return getSandboxPublickDiskCacheDir(context.getApplicationContext(), DIRECTORY_PICTURES);
    }


    /**
     * @date: 2019/6/21 0021
     * @author: gaoxiaoxiong
     * @description:获取沙盒目录下的音乐  缓存目录
     **/
    public String getSandboxPublickDiskMusicCacheDir(Context context) {
        return getSandboxPublickDiskCacheDir(context.getApplicationContext(), DIRECTORY_MUSIC);
    }

    /**
     * @date: 创建时间:2019/12/22
     * @author: gaoxiaoxiong
     * @descripion:获取沙盒目录下的电影  缓存目录
     **/
    public String getSandboxPublickDiskMoviesCacheDir(Context context) {
        return getSandboxPublickDiskCacheDir(context.getApplicationContext(), DIRECTORY_MOVIES);
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
