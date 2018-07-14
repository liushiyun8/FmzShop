package top.yundesign.fmz.UI.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;

public class MainActivity extends AppActivity {

    @BindView(R.id.sample_text)
    TextView sampleText;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public String getTitleStr() {
        return "登录";
    }

    @Override
    protected void init() {

    }
}
