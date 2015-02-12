package com.markzhai.library.framework.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.markzhai.library.R;
import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.NLog;

/**
 * 管理Fragment Created by marktlzhai on 2015/1/4.
 */
public class DefaultFragmentManager {
    private static final String TAG = DefaultFragmentManager.class.getSimpleName();

    private BaseActivity baseActivity;

    private FragmentManager fragmentManager;

    private BaseFragment currentFragment;

    public DefaultFragmentManager(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        this.fragmentManager = this.baseActivity.getSupportFragmentManager();
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    /**
     * 根据class获取一个fragment实例
     *
     * @param request
     * @return
     */
    public BaseFragment getFragmentInstance(FragmentRequest request) {
        BaseFragment fragment = (BaseFragment) Fragment.instantiate(baseActivity, request.getFragmentClass().getName(), request.getData());
        return fragment;
    }

    /**
     * 增加一个Fragment
     */
    public boolean insertFragmentToActivity(int layoutID, FragmentRequest request) {
        BaseFragment fragment = getFragmentInstance(request);

        if (fragment != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();

            if (request.addBackStack()) {
                addToBackStack(fragment, ft, fragment.getFragmentTag());
                ft.setCustomAnimations(request.getFragmentType().getEnterAnim(), request.getFragmentType().getExitAnim(), request.getFragmentType().getPopEnterAnim(), request.getFragmentType().getPopExitAnim());
            } else {
                ft.setCustomAnimations(request.getFragmentType().getEnterAnim(), request.getFragmentType().getExitAnim());
            }

            ft.replace(layoutID, fragment, fragment.getFragmentTag());

            if(layoutID == R.id.fragment_container) {
                currentFragment = fragment;
            }

            ft.commitAllowingStateLoss();
            return true;
        } else {
            return false;
        }
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    /**
     * 清除回退栈
     */
    public void clearBackstack() {
        fragmentManager.popBackStackImmediate(fragmentManager.getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        NLog.d(TAG, "[clearBackstack] stack cleared  - stack entry count :" + fragmentManager.getBackStackEntryCount() + " list size : " + fragmentManager.getBackStackEntryCount());
    }

    public boolean isFragmentAlreadyInStack(Fragment fragment) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            FragmentManager.BackStackEntry stackedEntry = fragmentManager.getBackStackEntryAt(i);
            Fragment stackedFragment = fragmentManager.findFragmentByTag(stackedEntry.getName());
            if (stackedFragment != null) {
                NLog.d(TAG,
                        "[isFragmentAlreadyInStack] is fragment from backstack: " + stackedFragment.getClass().getSimpleName() + "(" + stackedFragment.getId() + ") the same type of " + fragment.getClass().getSimpleName() + "(" + fragment.getId()
                                + ")");
                if (stackedFragment.getClass().isAssignableFrom(fragment.getClass())) {
                    NLog.d(TAG, "[isFragmentAlreadyInStack] " + fragment.getClass().getSimpleName() + " already stacked");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFragmentAlreadyInStackByHashCode(Fragment fragment) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            FragmentManager.BackStackEntry stackedEntry = fragmentManager.getBackStackEntryAt(i);
            Fragment stackedFragment = fragmentManager.findFragmentByTag(stackedEntry.getName());
            if (stackedFragment != null) {
                NLog.d(TAG,
                        "[isFragmentAlreadyInStack] has fragment from backstack: " + stackedFragment.getClass().getSimpleName() + "(" + stackedFragment.getId() + ") the same hashcode " + fragment.getClass().getSimpleName() + "(" + fragment.getId()
                                + ")");
                if (stackedFragment.hashCode() == fragment.hashCode()) {
                    NLog.d(TAG, "[isFragmentAlreadyInStack] " + fragment.getClass().getSimpleName() + " already stacked");
                    return true;
                }
            }
        }
        return false;
    }

    public int getFragmentPositionInStack(Fragment fragment, final boolean fromEnd) {
        int i;
        int n;
        if (fromEnd) {
            i = fragmentManager.getBackStackEntryCount() - 1;
            n = 0;
        } else {
            i = 0;
            n = fragmentManager.getBackStackEntryCount();
        }
        for (; (fromEnd) ? i >= n : i < n; ) {

            FragmentManager.BackStackEntry stackedEntry = fragmentManager.getBackStackEntryAt(i);
            Fragment stackedFragment = fragmentManager.findFragmentByTag(stackedEntry.getName());

            if (stackedFragment.getClass().isAssignableFrom(fragment.getClass())) {
                NLog.d(TAG, "[getFragmentPositionInStack] Fragment" + i + "/" + fragmentManager.getBackStackEntryCount() + ": " + stackedFragment.getClass().getSimpleName());

                return (fromEnd) ? fragmentManager.getBackStackEntryCount() - i : i;
            }

            if (fromEnd) {
                i--;
            } else {
                i++;
            }

        }
        return -1;
    }

    public void addToBackStack(Fragment fragmentToAdd, FragmentTransaction ft, String stackName) {
        if (fragmentToAdd != null) {
            ft.addToBackStack(stackName);
            NLog.d(TAG,
                    "[addToBackStack] add fragment to backstack " + (stackName != null ? "(" + stackName + ")" : "") + ": " + fragmentToAdd.getClass().getSimpleName() + " id :" + fragmentToAdd.getId() + " stack entry count :"
                            + fragmentManager.getBackStackEntryCount());
        }
    }

    public void popBackStack() {
        Fragment lastStackedFragment = getLastStackedFragment();
        if (lastStackedFragment != null) {
            fragmentManager.popBackStackImmediate();
            NLog.d(TAG, "[popBackStack] popping fragment from backstack: " + lastStackedFragment.getClass().getSimpleName() + " id :" + lastStackedFragment.getId() + " stack entry count :" + fragmentManager.getBackStackEntryCount());

        }
    }

    public boolean popBackStack(String tillName, boolean inclusive) {
        boolean hasPoppedSomething = fragmentManager.popBackStackImmediate(tillName, (inclusive) ? FragmentManager.POP_BACK_STACK_INCLUSIVE : 0);
        return hasPoppedSomething;
    }

    public Fragment getLastStackedFragment() {

        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry stackedEntry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
            Fragment lastStackedFragment = fragmentManager.findFragmentByTag(stackedEntry.getName());
            NLog.d(TAG, "[getLastStackedFragment] lastStackedFragment is : " + lastStackedFragment.getClass().getSimpleName() + " id :" + lastStackedFragment.getId());
            return lastStackedFragment;
        }

        return null;
    }

    ;

    public int getBackStackEntryCount() {
        return fragmentManager.getBackStackEntryCount();
    }
}
