package top.yundesign.fmz.UI.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;
import top.yundesign.fmz.UI.fragment.ItemFragment;

public class OrderActivity extends AppActivity {

    @BindView(R.id.tabLay)
    TabLayout tabLay;
    @BindView(R.id.vp)
    ViewPager vp;

    String[] titles={"全部","待付款","待分享","待发货","待收货","待评价"};
    ItemFragment[] itemFragments=new ItemFragment[6];


    @Override
    protected int getContentViewId() {
        return R.layout.activity_order;
    }

    @Override
    public String getTitleStr() {
        return "我的订单";
    }


    @Override
    protected void init() {
        for (int i = 0; i < itemFragments.length; i++) {
            itemFragments[i]=ItemFragment.newInstance(1, i);
        }
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLay.setupWithViewPager(vp);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return itemFragments.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return itemFragments[position];
        }

    }
}
