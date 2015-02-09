package com.markzhai.library.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.markzhai.library.R;
import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.utils.UIUtils;

/**
 * Created by marktlzhai on 2015/2/5.
 */
public abstract class BaseDialog extends Dialog {

    public abstract View onCreateView();

    public void onDialogClosed() {

    }

    public BaseDialog(Context context) {
        super(context, R.style.BaseDialog);
    }

    public BaseDialog(Context context, int theme) {
        super(context, R.style.BaseDialog);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private Typeface tf;

    private TextView titleView;
    private ImageButton closeButton;
    private RelativeLayout dialogButtonLayout;
    private Button leftButton;
    private Button rightButton;
    private RelativeLayout dialogContainer;

    private RelativeLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.dialog_base, null, false);
        setContentView(rootView);

        titleView = (TextView) findViewById(R.id.dialog_title);
        closeButton = (ImageButton) findViewById(R.id.dialog_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onDialogClosed();
            }
        });
        dialogButtonLayout = (RelativeLayout) findViewById(R.id.dialog_button_layout);
        leftButton = (Button) findViewById(R.id.dialog_button_left);
        rightButton = (Button) findViewById(R.id.dialog_button_right);
        dialogContainer = (RelativeLayout) findViewById(R.id.dialog_container);

        View view = onCreateView();
        if (view != null) {
            dialogContainer.addView(view, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    public void setTitle(int resID) {
        titleView.setText(resID);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void configButtons(int leftTxtResID, View.OnClickListener leftAction, int rightTxtResID, View.OnClickListener rightAction) {
        dialogButtonLayout.setVisibility(View.VISIBLE);
        leftButton.setText(leftTxtResID);
        leftButton.setOnClickListener(leftAction);

        rightButton.setText(rightTxtResID);
        rightButton.setOnClickListener(rightAction);
    }

    public void hideButtons() {
        dialogButtonLayout.setVisibility(View.GONE);
    }

    public void setTypeFace(Typeface typeFace) {
        UIUtils.setTypeFace(rootView, typeFace);
    }
}
