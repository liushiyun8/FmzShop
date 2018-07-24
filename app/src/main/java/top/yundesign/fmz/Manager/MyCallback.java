package top.yundesign.fmz.Manager;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

import top.yundesign.fmz.App.App;
import top.yundesign.fmz.UI.activity.MainActivity;
import top.yundesign.fmz.utils.ComUtils;
import top.yundesign.fmz.utils.LogUtils;

/**
 * Created by liuyun on 2017/5/24.
 */
public abstract class MyCallback implements Callback.CommonCallback<String> {
    private String TAG="HTTPManager";

    public abstract void onSuc(String result);

    public abstract void onFail(int code,String msg);

    @Override
    public void onSuccess(String result) {
        LogUtils.e(TAG,result);
        onSuc(result);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ex.printStackTrace();
        if(ex instanceof HttpException){
            HttpException httpException= (HttpException) ex;
            String result = httpException.getResult();
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject error = jsonObject.optJSONObject("error");
                int code = error.optInt("code");
                String msg = error.optString("msg");
                if(code==4031021){
                    ComUtils.shortTips("Token过期，请重新登录");
                    App.app.startActivity(new Intent(App.app, MainActivity.class));
                }
                onFail(code,"".equals(getMsg(code))?msg:getMsg(code));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else onFail(999,"未知的错误");

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
    
    public String getMsg(int errCode){
        String errInfo="";
        switch (errCode) {

        }
        return errInfo;
    }
}