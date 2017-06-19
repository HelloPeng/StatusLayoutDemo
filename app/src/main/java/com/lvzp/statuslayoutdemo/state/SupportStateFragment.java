package com.lvzp.statuslayoutdemo.state;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lvzp.statuslayoutdemo.R;

public class SupportStateFragment extends Fragment {

    private FrameLayout mFlLayoutParent;

    private View mProgressLayout;
    private View mEmptyLayout;
    private View mErrorLayout;
    private View mNetErrorLayout;
    private ProgressBar mProgressBar;
    private TextView mTvProgressHint;

    private boolean isShowProgress;
    private boolean isShowEmpty;
    private boolean isShowError;
    private boolean isShowNetError;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflateView = inflater.inflate(R.layout.fragment_state, container, false);
        mFlLayoutParent = (FrameLayout) inflateView.findViewById(R.id.fl_layout_parent);
        mProgressLayout = inflateView.findViewById(R.id.progress_content);
        mEmptyLayout = inflateView.findViewById(R.id.empty_content);
        mErrorLayout = inflateView.findViewById(R.id.error_content);
        mNetErrorLayout = inflateView.findViewById(R.id.net_error_content);
        mProgressBar = (ProgressBar) inflateView.findViewById(R.id.progress_bar);
        mTvProgressHint = (TextView) inflateView.findViewById(R.id.tv_progress_hint);
        initPageState();
        return inflateView;
    }

    public void setupActivityTitleHeight(int titleHeight) {
        if (mFlLayoutParent.getPaddingTop() != titleHeight)
            mFlLayoutParent.setPadding(0, titleHeight, 0, 0);
    }


    public void showProgress() {
        isShowProgress = true;
        isShowError = false;
        isShowEmpty = false;
        isShowNetError = false;
        initPageState();
    }

    public void showEmpty() {
        isShowProgress = true;
        isShowError = false;
        isShowNetError = false;
        isShowEmpty = true;
        initPageState();
    }

    public void showError() {
        isShowProgress = true;
        isShowEmpty = false;
        isShowNetError = false;
        isShowError = true;
        initPageState();
    }

    public void showNetError() {
        isShowProgress = true;
        isShowError = false;
        isShowEmpty = false;
        isShowNetError = true;
        initPageState();
    }

    private void initPageState() {
        if (mProgressLayout != null && mEmptyLayout != null && mErrorLayout != null && mNetErrorLayout != null) {
            mProgressLayout.setVisibility(isShowProgress ? View.VISIBLE : View.GONE);
            mEmptyLayout.setVisibility(isShowEmpty ? View.VISIBLE : View.GONE);
            mErrorLayout.setVisibility(isShowError ? View.VISIBLE : View.GONE);
            mNetErrorLayout.setVisibility(isShowNetError ? View.VISIBLE : View.GONE);
        }
    }


    /**
     * 在页面中点击重试的回调方法
     * 特别注意：由于这个回调方法是Fragment与Activity之间通信用的，为了不打破他们之间的生命周期，建议在使用的时候之间让Activity实现这个接口
     */
    public interface OnClickRetryListener {

        void onClickRetry();
    }


}
