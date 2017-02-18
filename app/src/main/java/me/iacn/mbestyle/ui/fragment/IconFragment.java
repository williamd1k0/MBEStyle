package me.iacn.mbestyle.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import me.iacn.mbestyle.R;

/**
 * Created by iAcn on 2017/2/18
 * Emali iAcn0301@foxmail.com
 */

public class IconFragment extends BaseFragment {

    private TabLayout mTab;
    private ViewPager mViewPager;

    @Override
    protected int getInflateView() {
        return R.layout.fragment_icon;
    }

    @Override
    protected void findView() {
        mTab = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        mTab.setupWithViewPager(mViewPager);
        mViewPager.setAdapter();
    }
}