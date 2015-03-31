package com.markzhai.library.utils;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.markzhai.library.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 图片工具类 Created by marktlzhai on 2014/12/17.
 */
public class ImageUtils {

    private static DisplayImageOptions displayImageOptions;

    private static Context context;

    /**
     * 初始化Image加载组件
     */
    public static void init(Context context) {

        ImageUtils.context = context;

        if (displayImageOptions == null) {
            displayImageOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.load_error).showImageOnFail(R.drawable.load_error).cacheInMemory(true).cacheOnDisk(true)
                    .considerExifParams(true).displayer(new FadeInBitmapDisplayer(20)).build();
        }

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024).diskCacheFileCount(Integer.MAX_VALUE).tasksProcessingOrder(QueueProcessingType.FIFO).writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 加载图片
     */
    public static void displayImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView, displayImageOptions);
    }

    /**
     * 加载图片
     */
    public static void displayImage(String url, ImageView imageView, ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(url, imageView, displayImageOptions, listener);
    }

    /**
     * 销毁
     */
    public static void destroy() {
        ImageLoader.getInstance().destroy();
    }

    /**
     * 获取可用于TextView的图片
     */
    public static Drawable getTextviewDrawable(Context context, int drawableResId, int width, int height) {
        Drawable d = context.getResources().getDrawable(drawableResId);
        d.setBounds(0, 0, width, height);
        return d;
    }

    /**
     * 获取可用于TextView的图片
     */
    public static Drawable getTextviewDrawable(Context context, int drawableResId, int size) {
        Drawable d = context.getResources().getDrawable(drawableResId);
        d.setBounds(0, 0, size, size);
        return d;
    }

    /**
     * bitmap转换byte数组
     *
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * byte数组转换bitmap
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * drawable转换bitmap
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable) d).getBitmap();
    }

    /**
     * bitmap转换drawable
     */
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(context.getResources(), b);
    }

    /**
     * drawable转换byte数组
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * byte数组转换drawable
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * 缩放图片
     *
     * @param org       原始图片
     * @param newWidth  宽度
     * @param newHeight 高度
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * 缩放图片
     *
     * @param org         原始图片
     * @param scaleWidth  宽度Scale
     * @param scaleHeight 高度Scale
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

}
