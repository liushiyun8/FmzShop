package top.yundesign.fmz.Manager;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

import top.yundesign.fmz.App.App;
import top.yundesign.fmz.UI.activity.MainActivity;
import top.yundesign.fmz.utils.ComUtils;

/**
 * Created by liuyun on 2017/5/24.
 */
public abstract class MyCallback implements Callback.CommonCallback<String> {

    public abstract void onSuc(String result);

    public abstract void onFail(int code,String msg);

    @Override
    public void onSuccess(String result) {
        onSuc(result);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ex.printStackTrace();
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