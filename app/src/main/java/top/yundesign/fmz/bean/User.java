package top.yundesign.fmz.bean;

import java.lang.reflect.Field;

import top.yundesign.fmz.App.App;
import top.yundesign.fmz.utils.SpUtils;

public class User {
    public static String phone;
    public static String token= (String) App.app.getmSp().get("token","");
    public static int userId= (int) App.app.getmSp().get("token",0);
    public static int type;
    public static String pwd;
    public static String openId;
    public static String nickname;
    public static String logo;
    static {
        SpUtils spUtils = App.app.getmSp();
        phone= (String) spUtils.get("phone","");
        token= (String) spUtils.get("token","");
    }

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void saveToSp() {
        Class<? extends User> aClass = getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            try {
                App.app.getmSp().put(field.getName(),field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
