package me.iacn.mbestyle.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import me.iacn.mbestyle.R;
import me.iacn.mbestyle.bean.AppBean;
import me.iacn.mbestyle.presenter.ApplyPresenter;
import me.iacn.mbestyle.ui.adapter.AppAdapter;
import me.iacn.mbestyle.ui.callback.OnItemClickListener;

/**
 * Created by iAcn on 2017/2/18
 * Emali iAcn0301@foxmail.com
 */

public class ApplyFragment extends ILazyFragment implements OnItemClickListener {

    private RecyclerView rvApp;
    private ApplyPresenter mPresenter;
    private List<AppBean> mApps;

    @Override
    protected int getContentView() {
        return R.layout.fragment_apply;
    }

    @Override
    protected void findView() {
        rvApp = (RecyclerView) findViewById(R.id.rv_app);
    }

    @Override
    protected void setListener() {
        rvApp.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        mPresenter = new ApplyPresenter(this);
        mPresenter.loadInstallApp();
    }

    @Override
    public void onItemClick(View itemView, int position) {

    }

    public void onLoadData(List<AppBean> list) {
        super.onLoadData();

        mApps = list;
        AppAdapter adapter = new AppAdapter(mApps);
        adapter.setOnItemClickListener(this);
        rvApp.setAdapter(adapter);
    }
}