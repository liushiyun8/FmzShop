package top.yundesign.fmz.UI.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;

public class ForgetActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_forget;
    }

    @Override
    public String getTitleStr() {
        return "找回密码";
    }

    @Override
    protected void init() {

    }
}
