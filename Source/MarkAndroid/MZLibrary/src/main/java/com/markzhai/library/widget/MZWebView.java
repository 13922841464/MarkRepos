package com.markzhai.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by marktlzhai on 2015/5/25.
 */
public class MZWebView extends WebView {
    public MZWebView(Context context) {
        super(context);
        init();
    }

    public MZWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MZWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }
}
