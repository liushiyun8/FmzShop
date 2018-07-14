package top.yundesign.fmz.UI.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;

public class LoginActivity extends AppActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public String getTitleStr() {
        return "登录";
    }

    @Override
    protected void init() {

    }
}
