package com.markzhai.diablo3.core.view.fragment;

import android.view.View;
import android.widget.Button;

import com.markzhai.diablo3.R;
import com.markzhai.diablo3.core.DiabloApp;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;
import com.markzhai.library.utils.SPUtils;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/5/19.
 */
public class FragmentSplash extends BaseFragment implements View.OnClickListener {

    public enum Lang {
        EN("en"), ZH("cn"), ZH_TW("tw");

        private String type;

        private Lang(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    @InjectView(R.id.enter_english)
    private Button enterEnButton;
    @InjectView(R.id.enter_zh)
    private Button enterZhButton;
    @InjectView(R.id.enter_zh_tw)
    private Button enterZhTwButton;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void init() {
        enterEnButton.setTag(Lang.EN);
        enterEnButton.setOnClickListener(this);

        enterZhButton.setTag(Lang.ZH);
        enterZhButton.setOnClickListener(this);

        enterZhTwButton.setTag(Lang.ZH_TW);
        enterZhTwButton.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        Lang lang = (Lang) view.getTag();
        SPUtils.putString(DiabloApp.SP.LANG, lang.getType());

        enterHomePage();
    }

    private void enterHomePage() {
        FragmentRequest homeRequest = new FragmentRequest(FragmentType.HOME, FragmentHome.class, false, false, null);
        startFragment(homeRequest);
    }
}
