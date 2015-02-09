package com.markzhai.adultol.views;

import android.os.AsyncTask;

import com.markzhai.adultol.R;
import com.markzhai.adultol.model.Redirect;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.utils.AppUtils;

/**
 * Created by marktlzhai on 2015/2/4.
 */
public class SplashFragment extends BaseFragment {

    private RedirectAction redirectAction;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void init() {
        redirectAction = new RedirectAction();
        redirectAction.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (redirectAction != null) {
            redirectAction.cancel(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (redirectAction != null) {
            redirectAction.cancel(true);
        }
    }

    class RedirectAction extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (isCancelled()) {
                return;
            }

            startFragment(Redirect.LOGIN);
            redirectAction = null;
        }
    }
}
