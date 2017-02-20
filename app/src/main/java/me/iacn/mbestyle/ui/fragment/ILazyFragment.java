package me.iacn.mbestyle.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import me.iacn.mbestyle.R;

/**
 * Created by iAcn on 2017/2/20
 * Emali iAcn0301@foxmail.com
 */

public abstract class ILazyFragment extends Fragment {

    protected View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), getInitialView(), null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setContentView();
        findView();
        setListener();
        initData();
    }

    @LayoutRes
    protected int getInitialView() {
        return R.layout.inflate_loading;
    }

    protected final void setContentView() {
        mContentView = View.inflate(getActivity(), getContentView(), null);
    }

    @LayoutRes
    protected abstract int getContentView();

    protected abstract void findView();

    protected abstract void setListener();

    protected abstract void initData();

    protected final View findViewById(@IdRes int id) {
        return mContentView.findViewById(id);
    }

    protected void onLoadData() {
        FrameLayout layout = (FrameLayout) getView();

        if (layout != null) {
            ViewGroup group = (ViewGroup) layout.getParent();
            group.removeAllViews();
            group.addView(mContentView);
        }
    }
}