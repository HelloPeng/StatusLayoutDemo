package com.lvzp.statuslayoutdemo.state;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by 860617003 on 2017/6/19.
 */

public class StateHelper {

    private static final int PAGE_LAYOUT_DEFAULT_ID = 0x22131231;
    private static final String TAG = "StateFragment";
    private StateFragment mStateFragment;
    private SupportStateFragment mSupportStatusFragment;
    private FragmentManager mFragmentManager;
    private android.support.v4.app.FragmentManager mSupportFragmentManager;
    private int mTitleHeight;

    public StateHelper(@NonNull Activity activity) {
        mFragmentManager = activity.getFragmentManager();
        mStateFragment = getStateFragment(activity, android.R.id.content);
    }

    public StateHelper(@NonNull Fragment fragment) throws Exception {
        mFragmentManager = fragment.getFragmentManager();
        int containerViewId;
        if (fragment.getView() != null) {
            if (fragment.getView().getId() == -1)
                fragment.getView().setId(PAGE_LAYOUT_DEFAULT_ID);
            containerViewId = PAGE_LAYOUT_DEFAULT_ID;
        } else {
            throw new NullPointerException("如果您当前处于Fragment中，请在onActivityCreate()回调中创建该对象");
        }
        mStateFragment = getStateFragment(fragment.getActivity(), containerViewId);
    }

    public StateHelper(@NonNull android.support.v4.app.Fragment fragment) {
        mSupportFragmentManager = fragment.getChildFragmentManager();
        int containerViewId;
        if (fragment.getView() != null) {
            if (fragment.getView().getId() == -1)
                fragment.getView().setId(PAGE_LAYOUT_DEFAULT_ID);
            containerViewId = PAGE_LAYOUT_DEFAULT_ID;

        } else {
            throw new NullPointerException("如果您当前处于Fragment中，请在onActivityCreate()回调中创建该对象");
        }
        mSupportStatusFragment = getSupportStateFragment(fragment.getActivity(), containerViewId);
    }

    private StateFragment getStateFragment(Activity activity, int containerViewId) {
        StateFragment stateFragment = findStateFragment(activity);
        boolean isNewInstance = stateFragment == null;
        if (isNewInstance) {
            stateFragment = new StateFragment();
            FragmentManager fragmentManager = mFragmentManager;
            fragmentManager
                    .beginTransaction()
                    .add(containerViewId, stateFragment, TAG)
                    .hide(stateFragment)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return stateFragment;
    }

    private SupportStateFragment getSupportStateFragment(FragmentActivity activity, int containerViewId) {
        SupportStateFragment stateFragment = findSupportStateFragment(activity);
        boolean isNewInstance = stateFragment == null;
        if (isNewInstance) {
            stateFragment = new SupportStateFragment();
            android.support.v4.app.FragmentManager fragmentManager = mSupportFragmentManager;
            fragmentManager
                    .beginTransaction()
                    .add(containerViewId, stateFragment, TAG)
                    .hide(stateFragment)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return stateFragment;
    }


    private StateFragment findStateFragment(Activity activity) {
        return (StateFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    private SupportStateFragment findSupportStateFragment(FragmentActivity activity) {
        return (SupportStateFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
    }

    public void bindTitleView(final View view) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mTitleHeight = view.getHeight();
                mStateFragment.setupActivityTitleHeight(mTitleHeight);
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
    }

    public void showProgressView() {
        if (mFragmentManager != null) {
            checkFragmentIsVisible();
            mStateFragment.showProgress();
        } else {
            checkSupportFragmentIsVisible();
            mSupportStatusFragment.showProgress();
        }
    }

    private void checkFragmentIsVisible() {
        if (!mStateFragment.isVisible()) {
            FragmentManager fragmentManager = mFragmentManager;
            fragmentManager
                    .beginTransaction()
                    .show(mStateFragment)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    private void checkSupportFragmentIsVisible() {
        if (!mSupportStatusFragment.isVisible()) {
            android.support.v4.app.FragmentManager fragmentManager = mSupportFragmentManager;
            fragmentManager
                    .beginTransaction()
                    .show(mSupportStatusFragment)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    public void showEmptyView() {
        if (mFragmentManager != null) {
            checkFragmentIsVisible();
            mStateFragment.showEmpty();
        } else {
            checkSupportFragmentIsVisible();
            mSupportStatusFragment.showEmpty();
        }
    }

    public void showErrorView() {
        if (mFragmentManager != null) {
            checkFragmentIsVisible();
            mStateFragment.showError();
        } else {
            checkSupportFragmentIsVisible();
            mSupportStatusFragment.showError();
        }
    }

    public void showNetErrorView() {
        if (mFragmentManager != null) {
            checkFragmentIsVisible();
            mStateFragment.showNetError();
        } else {
            checkSupportFragmentIsVisible();
            mSupportStatusFragment.showNetError();
        }
    }

    public void hideStateLayout() {
        if (mFragmentManager != null) {
            hideStateFragment();
        } else {
            hideSupportStateFragment();
        }
    }

    private void hideStateFragment() {
        FragmentManager fragmentManager = mFragmentManager;
        fragmentManager
                .beginTransaction()
                .hide(mStateFragment)
                .commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    private void hideSupportStateFragment() {
        android.support.v4.app.FragmentManager fragmentManager = mSupportFragmentManager;
        fragmentManager
                .beginTransaction()
                .hide(mSupportStatusFragment)
                .commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }
}
