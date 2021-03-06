package com.markzhai.library.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.markzhai.library.R;

/**
 * UI相关工具类 Created by marktlzhai on 2014/12/24.
 */
public class UIUtils {

    public static final float getHeightInPx(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final float getWidthInPx(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final int getHeightInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        int heightInDp = px2dp(context, height);
        return heightInDp;
    }

    public static final int getWidthInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        int widthInDp = px2dp(context, height);
        return widthInDp;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int px2sp(float pxValue, Context context) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp的单位 转成为 px(像素)
     */
    public static int sp2px(float spValue, Context context) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static ImageView createImageView(Context context, int imageRes) {
        ImageView iv = new ImageView(context);
        iv.setImageDrawable(context.getResources().getDrawable(imageRes));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }

    /**
     * 销毁一个imageview
     */
    public static void destroyImageView(ImageView imageView) {
        Drawable d = imageView.getDrawable();
        d.setCallback(null);
        if (d instanceof BitmapDrawable) {
            ((BitmapDrawable) d).getBitmap().recycle();
        }
    }

    /**
     * 设置typeface
     */
    public static void setTypeFace(ViewGroup group, Typeface tf) {
        if (group == null || tf == null) {
            return;
        }

        int count = group.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = group.getChildAt(i);
            if (v instanceof TextView) {
                setTypeFace((TextView) v, tf);
            }

            if (v instanceof ViewGroup) {
                setTypeFace((ViewGroup) v, tf);
            }
        }
    }

    /**
     * 设置typeface
     */
    public static void setTypeFace(TextView tv, Typeface tf) {
        if (tv == null) {
            return;
        }
        tv.setTypeface(tf);
    }

    public static Button getTestButton(Context context, String buttonTXT) {
        Button btn = new Button(context);
        btn.setBackgroundResource(R.drawable.selector_default_button);
        btn.setTextColor(context.getResources().getColor(R.color.color_default_text));
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        btn.setPadding(context.getResources().getDimensionPixelOffset(R.dimen.margin_normal), context.getResources().getDimensionPixelOffset(R.dimen.margin_small_more), context.getResources().getDimensionPixelOffset(R.dimen.margin_normal), context.getResources().getDimensionPixelOffset(R.dimen.margin_small_more));
        btn.setText(buttonTXT);

        return btn;
    }

    public static TextView getTestView(Context context, String message) {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.RED);
        textView.setText(message);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(30);

        return textView;
    }
}
