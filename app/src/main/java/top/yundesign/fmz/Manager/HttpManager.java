package top.yundesign.fmz.Manager;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.logging.XMLFormatter;

import top.yundesign.fmz.App.App;
import top.yundesign.fmz.utils.LogUtils;

public class HttpManager {
    public static final String HOST="http://i.fengmaozhai.com";
    public static final String  LOGINURL=HOST+"/api/user/login",
                                REGISTERURL=HOST+"/api/user/register",
                                SENDEMSG=HOST+"/api/user/sendMessage",
                                UPDATE_PWD=HOST+"/api/user/updatePassword",
                                GET_USERINFO =HOST+"/api/user/getUserInfo",
                                UPDATE_USERINFO=HOST+"/api/user/updateUserInfo";

    /**
     *
     * @param type 登陆类型 1：密码登陆、2：验证码登陆、3：微信登陆
     * @param phone 登录账户
     * @param pwd   密码、type = 1 时传入;短信验证码、type = 2 时传入;微信ID、type = 3 时传入
     * @param myCallback  结果回调
     */
    public static void Login(int type,String phone,String pwd,MyCallback myCallback){
        RequestParams params= getPostRequestParams(LOGINURL);
        params.addParameter("username",phone);
        params.addParameter("type",type);
        params.addBodyParameter("password",pwd);
        x.http().post(params,myCallback);
    }


    /**
     *
     * @param type 注册类型 1：手机验证码注册、2：微信注册
     * @param phone 注册账户
     * @param code 短信验证码
     * @param pwd   密码、type = 1 时传入  微信ID、type = 2 时传入
     * @param myCallback  结果回调
     */

    public static void Register(int type,String phone,String code,String pwd,MyCallback myCallback){
        RequestParams params = getPostRequestParams(REGISTERURL);
        params.addParameter("phone",phone);
        params.addParameter("type",type);
        params.addParameter("code",code);
        params.addBodyParameter("password",pwd);
        x.http().post(params,myCallback);
    }

    public static void SendMessage(String phone,MyCallback myCallback){
        RequestParams params = getPostRequestParams(SENDEMSG);
        params.addParameter("phone",phone);
        x.http().post(params,myCallback);
    }

    @NonNull
    private static RequestParams getPostRequestParams(String url) {
        RequestParams params = new RequestParams(url);
        params.setMethod(HttpMethod.POST);
        params.setAsJsonContent(true);
        return params;
    }


}
