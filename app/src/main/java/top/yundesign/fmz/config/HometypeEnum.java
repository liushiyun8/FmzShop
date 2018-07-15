package top.yundesign.fmz.config;

import android.support.v4.app.Fragment;

import top.yundesign.fmz.UI.fragment.CategoryFragment;
import top.yundesign.fmz.UI.fragment.FindFragment;
import top.yundesign.fmz.UI.fragment.HomeFragment;
import top.yundesign.fmz.UI.fragment.MineFragment;
import top.yundesign.fmz.UI.fragment.ShopcarFragment;

public enum  HometypeEnum {
    HOME(0, "首页", HomeFragment.newInstance()),FIND(1,"发现", FindFragment.newInstance()),FENLEI(2,"分类", CategoryFragment.newInstance()),
    SHOPCAR(3,"购物车", ShopcarFragment.newInstance()),MINE(4,"我的", MineFragment.newInstance());
    ;
    private int index;
    private String name;
    private Fragment fm;


    HometypeEnum(int index, String name, Fragment fm) {
        this.index = index;
        this.name = name;
        this.fm = fm;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fragment getFm() {
        return fm;
    }

    public void setFm(Fragment fm) {
        this.fm = fm;
    }

    public static HometypeEnum getbyIndex(int index){
        for (HometypeEnum type :HometypeEnum.values()) {
            if(type.getIndex()==index)
                return type;
        }
      return null;
    }


}
