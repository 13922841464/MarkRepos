package com.markzhai.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.markzhai.library.R;
import com.markzhai.library.exception.AccountParseException;
import com.markzhai.library.framework.core.model.MZAccount;
import com.markzhai.library.utils.SPUtils;
import com.markzhai.library.utils.StringUtils;

/**
 * Created by marktlzhai on 2015/4/20.
 */
public class AccountLayout extends RelativeLayout {

    private static final String TYPE_QQ = "QQ";
    private static final String TYPE_WEI_CHAT = "WEI_CHAT";
    private static final String TYPE_WEIBO = "WEIBO";

    private static final String SP_ACCOUNT = "ACCOUNT";
    private static final String SP_ACCOUNT_TYPE = "ACCOUNT_TYPE";

    private View rootView;

    private boolean accountGoldenEnable = false;
    private int accountGoldImage = 0;

    private View noLoginView;
    private View accountInfoView;

    private MZAccount currentAccount;

    public AccountLayout(Context context) {
        super(context);
        init();
    }

    public AccountLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AccountLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        String type = SPUtils.getString(SP_ACCOUNT_TYPE, "");
        String account = SPUtils.getString(SP_ACCOUNT, "");
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_account_layout, null, false);

        noLoginView = rootView.findViewById(R.id.account_no_login_view);
        accountInfoView = rootView.findViewById(R.id.account_info_view);
        noLoginView.setVisibility(View.VISIBLE);
        accountInfoView.setVisibility(View.GONE);

        addView(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (!StringUtils.isEmpty(type) && !StringUtils.isEmpty(account)) {
            try {
                currentAccount = new MZAccount(account);
                SPUtils.putString(SP_ACCOUNT_TYPE, "");
                SPUtils.putString(SP_ACCOUNT, "");
            } catch (AccountParseException e) {
                initLoginView();
            }
            initAccountView();
        } else {
            initLoginView();
        }
    }

    private void initLoginView() {

    }

    private void initAccountView() {

    }

    public void setAccountGoldenEnable(boolean accountGoldenEnable, int accountGoldImage) {
        this.accountGoldenEnable = accountGoldenEnable;
        this.accountGoldImage = accountGoldImage;
    }
}
