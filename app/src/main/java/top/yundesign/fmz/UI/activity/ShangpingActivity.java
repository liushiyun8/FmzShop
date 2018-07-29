package top.yundesign.fmz.UI.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;

public class ShangpingActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangping);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_shangping;
    }

    @Override
    public String getTitleStr() {
        return null;
    }

    @Override
    protected void init() {

    }
}
