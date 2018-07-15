package top.yundesign.fmz.UI.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;
import top.yundesign.fmz.UI.fragment.HomeFragment;
import top.yundesign.fmz.config.HometypeEnum;

public class MainActivity extends AppActivity {

    @BindView(R.id.hgp)
    RadioGroup hgp;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private int currentIndex;
    Fragment currentF;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public String getTitleStr() {
        return null;
    }

    @Override
    protected void init() {
        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = HomeFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.content,homeFragment).commit();
        currentF=homeFragment;
        hgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
               switchFragment(i);
           }
       });
    }

    private void switchFragment(int id) {
        switch (id){
            case R.id.homebtn:
                currentIndex=0;
                break;
            case R.id.find:
                currentIndex=1;
                break;
            case R.id.fenlei:
                currentIndex=2;
                break;
            case R.id.shopcar:
                currentIndex=3;
                break;
            case R.id.mine:
                currentIndex=4;
                break;
        }
        HometypeEnum hometypeEnum = HometypeEnum.getbyIndex(currentIndex);
        Fragment fm = hometypeEnum.getFm();
        transaction=fragmentManager.beginTransaction();
        if(fm.isAdded())
            transaction.show(fm);
        else transaction.add(R.id.content,fm);
        transaction.hide(currentF);
        transaction.commit();
        currentF=fm;
    }

}
