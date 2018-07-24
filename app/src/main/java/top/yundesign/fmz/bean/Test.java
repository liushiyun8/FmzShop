package top.yundesign.fmz.bean;

import java.lang.reflect.Field;

import top.yundesign.fmz.App.App;
import top.yundesign.fmz.utils.LogUtils;

public class Test {

    /**
     * token : e1ba1260-8f4a-11e8-ba9d-21678323a29d
     * userId : 1
     * nickname : xxxx
     * logo : xxx.jpg
     */

    private String token;
    private int userId;
    private String nickname;
    private String logo;

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
        Class<? extends Test> aClass =getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            try {
                LogUtils.e("user",field.getName()+":::"+field.get(this));
                if(field.get(this)!=null)
                    App.app.getmSp().put(field.getName(),field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
