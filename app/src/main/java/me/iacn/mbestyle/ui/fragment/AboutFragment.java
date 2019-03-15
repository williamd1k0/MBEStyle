package me.iacn.mbestyle.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import me.iacn.mbestyle.BuildConfig;
import me.iacn.mbestyle.R;
import me.iacn.mbestyle.ui.activity.LicenseActivity;
import me.iacn.mbestyle.ui.widget.AboutItem;
import me.iacn.mbestyle.util.GlideUtils;

/**
 * Created by iAcn on 2017/2/18
 * Email i@iacn.me
 */

public class AboutFragment extends BaseFragment implements View.OnClickListener {

    private ImageView ivLogo;
    private AboutItem aiVersion;
    private AboutItem aiDesigner;
    private AboutItem aiDeveloper;
    private AboutItem aiOpenSource;

    @Override
    protected int getContentView() {
        return R.layout.fragment_about;
    }

    @Override
    protected void findView() {
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        aiVersion = (AboutItem) findViewById(R.id.ai_version);
        aiDesigner = (AboutItem) findViewById(R.id.ai_designer);
        aiDeveloper = (AboutItem) findViewById(R.id.ai_developer);
        aiOpenSource = (AboutItem) findViewById(R.id.ai_open_source);
    }

    @Override
    protected void setListener() {
        aiVersion.setOnClickListener(this);
        aiDesigner.setOnClickListener(this);
        aiDeveloper.setOnClickListener(this);
        aiOpenSource.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        aiVersion.setSummary(BuildConfig.VERSION_NAME);
        GlideUtils.with(this).showImage(R.drawable.bg_about_logo, ivLogo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ai_designer:
                openUrl("http://coolapk.com/u/433446");
                break;
            case R.id.ai_developer:
                openUrl("http://coolapk.com/u/532152");
                break;
            case R.id.ai_open_source:
                startActivity(new Intent(getActivity(), LicenseActivity.class));
                break;
        }
    }

    private void openUrl(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

}