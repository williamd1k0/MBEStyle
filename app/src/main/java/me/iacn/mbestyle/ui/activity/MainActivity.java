package me.iacn.mbestyle.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import me.iacn.mbestyle.R;
import me.iacn.mbestyle.ui.fragment.BaseFragment;
import me.iacn.mbestyle.ui.fragment.AboutFragment;
// import me.iacn.mbestyle.ui.fragment.ApplyFragment;
import me.iacn.mbestyle.ui.fragment.IconFragment;
import me.iacn.mbestyle.util.ScreenUtils;
import me.iacn.mbestyle.util.StatusBarUtils;

/**
 * Created by iAcn on 2017/2/18
 * Email i@iacn.me
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;

    private int mCurrentIndex = -1;
    private List<BaseFragment> mFragments;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        StatusBarUtils.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary));

        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottom_bar);
        bottomView.setOnNavigationItemSelectedListener(this);
        setBottomIconOriColor(bottomView);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFragments = Arrays.asList(
                new IconFragment(),
                // new ApplyFragment(),
                new AboutFragment());

        mFragmentManager = getFragmentManager();

        switchFragment(0);
        handleToolbarElevation(0);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int order = item.getOrder();

        if (order >= 0 && order <= 3) {
            handleToolbarElevation(order);
            switchFragment(order);
            return true;
        }

        return false;
    }

    private void setBottomIconOriColor(BottomNavigationView bottomView) {
        try {
            Field mMenuViewField = BottomNavigationView.class.getDeclaredField("mMenuView");
            mMenuViewField.setAccessible(true);
            BottomNavigationMenuView mMenuView = (BottomNavigationMenuView) mMenuViewField.get(bottomView);

            Field mButtonsField = BottomNavigationMenuView.class.getDeclaredField("mButtons");
            mButtonsField.setAccessible(true);
            BottomNavigationItemView[] mButtons = (BottomNavigationItemView[]) mButtonsField.get(mMenuView);

            Field mIconField = BottomNavigationItemView.class.getDeclaredField("mIcon");
            mIconField.setAccessible(true);

            for (BottomNavigationItemView item : mButtons) {
                ImageView mIcon = (ImageView) mIconField.get(item);
                mIcon.setImageTintList(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

    private void handleToolbarElevation(int index) {
        // 只在图标 Fragment 隐藏阴影
        ViewCompat.setElevation(mToolbar, index == 0 ? 0 : ScreenUtils.dip2px(this, 2));
    }

    private void switchFragment(int index) {
        if (index == mCurrentIndex) return;

        Fragment fragment = mFragments.get(index);
        FragmentTransaction transaction =
                mFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.fragment_in, R.animator.fragment_out);

        String indexString = String.valueOf(index);
        Fragment targetFragment = mFragmentManager.findFragmentByTag(indexString);

        if (mCurrentIndex != -1) {
            // 不是首次启动
            transaction.hide(mFragments.get(mCurrentIndex));
        }

        if (targetFragment == null) {
            // 之前没有添加过
            transaction.add(R.id.fl_content, fragment, indexString);
        } else {
            transaction.show(targetFragment);
        }

        transaction.commit();
        mCurrentIndex = index;
    }
}