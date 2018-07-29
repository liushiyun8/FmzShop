package top.yundesign.fmz.UI.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;

public class ShopDetailActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_shop_detail;
    }

    @Override
    public String getTitleStr() {
        return "店铺详情";
    }

    @Override
    protected void init() {

    }
}
