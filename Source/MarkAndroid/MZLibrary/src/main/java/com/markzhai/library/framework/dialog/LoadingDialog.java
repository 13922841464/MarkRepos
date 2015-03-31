package com.markzhai.library.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.widget.TextView;

import com.markzhai.library.R;

/**
 * Created by marktlzhai on 2015/3/31.
 */
public class LoadingDialog {
    public static Dialog showLoadingDialog(Context context, String message, boolean cancelable) {
        final Dialog dialog = new Dialog(context, R.style.BaseDialog);

        dialog.setContentView(R.layout.dialog_loading);
        TextView messageView = (TextView) dialog.findViewById(R.id.tip_message);
        messageView.setText(message);
        dialog.setCancelable(cancelable);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dilg, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                return false;
            }
        });
        dialog.show();

        return dialog;
    }
}
