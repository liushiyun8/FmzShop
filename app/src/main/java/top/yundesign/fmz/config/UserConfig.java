package top.yundesign.fmz.config;

import top.yundesign.fmz.App.App;

public class UserConfig {
    public static String token= (String) App.app.getmSp().get("token","");
    public static int userId= (int) App.app.getmSp().get("token",0);
}
