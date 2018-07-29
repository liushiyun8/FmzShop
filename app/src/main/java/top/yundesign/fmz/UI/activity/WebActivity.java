package top.yundesign.fmz.UI.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;

public class WebActivity extends AppActivity {


    @BindView(R.id.wv)
    WebView wv;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    public String getTitleStr() {
        return null;
    }

    @Override
    protected void init() {
        String url = getIntent().getStringExtra("url");
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl(url);
    }
}
