package top.yundesign.fmz.UI.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.Manager.HttpManager;
import top.yundesign.fmz.Manager.MyCallback;
import top.yundesign.fmz.R;
import top.yundesign.fmz.config.UserConfig;
import top.yundesign.fmz.utils.ComUtils;
import top.yundesign.fmz.utils.LogUtils;
import top.yundesign.fmz.utils.StringUtils;

public class LoginActivity extends AppActivity {

    @BindView(R.id.acount)
    EditText acount;
    @BindView(R.id.dele)
    ImageView dele;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.dele1)
    ImageView dele1;
    @BindView(R.id.show)
    ImageView show;
    @BindView(R.id.sure)
    Button sure;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.weichat)
    ImageView weichat;
    @BindView(R.id.login_account)
    TextView loginAccount;
    @BindView(R.id.login_phone)
    TextView loginPhone;
    @BindView(R.id.account_layout)
    LinearLayout accountLayout;
    @BindView(R.id.text_yanzhen)
    EditText textYanzhen;
    @BindView(R.id.check)
    Button check;
    @BindView(R.id.iv_account)
    ImageView ivAccount;
    @BindView(R.id.layout_pwd)
    LinearLayout layoutPwd;
    @BindView(R.id.layout_yanzhen)
    LinearLayout layoutYanzhen;
    @BindView(R.id.agree)
    TextView agree;
    @BindView(R.id.layout_weichat)
    LinearLayout layoutWeichat;
    private Boolean isAccount = true;
    private boolean Flag;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public String getTitleStr() {
        return "登录";
    }

    @Override
    protected void init() {
        loginAccount.setSelected(true);
        loginPhone.setSelected(false);
        acount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!"".equals(s.toString())) {
                    dele.setVisibility(View.VISIBLE);
                    if (!"".equals(pwd.getText().toString()) && pwd.getText().length() >= 6) {
                        sure.setEnabled(true);
                    }
                    if (StringUtils.isPhoneNum(s.toString())) {
                        LogUtils.e(TAG, "被调用了");
                        acount.clearFocus();
                        pwd.requestFocus();
                        pwd.performClick();
                        check.setEnabled(true);
                    }else check.setEnabled(false);
                    if (s.length() > 11) {
                        s.delete(11, s.length());
                    }
                } else {
                    sure.setEnabled(false);
                    dele.setVisibility(View.GONE);
                }
            }
        });
        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!"".equals(s.toString())) {
                    dele1.setVisibility(View.VISIBLE);
                    if (s.length() >= 6)
                        sure.setEnabled(true);
                } else {
                    sure.setEnabled(false);
                    dele1.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.register, R.id.forget, R.id.weichat, R.id.sure, R.id.login_account, R.id.login_phone,
            R.id.dele,R.id.dele1,R.id.show})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.register:
                startActivity(RegisterActivity.class);
                break;
            case R.id.forget:
                startActivity(ForgetActivity.class);
                break;
            case R.id.weichat:
                break;
            case R.id.sure:
                login();
                break;
            case R.id.login_account:
                isAccount=true;
                switchLay(isAccount);
                break;
            case R.id.login_phone:
                isAccount=false;
                switchLay(isAccount);
                break;
            case R.id.dele:
                acount.setText("");
                view.setVisibility(View.GONE);
                break;
            case R.id.dele1:
                pwd.setText("");
                view.setVisibility(View.GONE);
                break;
            case R.id.show:
                if(Flag){
                    pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    show.setImageResource(R.mipmap.login_btn_invis_nor);
                }else {
                    pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    show.setImageResource(R.mipmap.login_btn_visible_nor);
                }
                Flag=!Flag;
                break;
        }
    }

    private void login() {
        String name = acount.getText().toString();
        String pwdStr = pwd.getText().toString();
        HttpManager.Login(1,name, pwdStr, new MyCallback() {
            @Override
            public void onSuc(String result) {
                LogUtils.e(TAG,result);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(jsonObject!=null){
                    int code = jsonObject.optInt("code");
                    if(code==0){
                        JSONObject datao= jsonObject.optJSONObject("data");
                        String token = datao.optString("token");
                        int userId = datao.optInt("userId");
                        ComUtils.shortTips("登录成功");
                        mSp.put("token",token);
                        mSp.put("userId",userId);
                        UserConfig.token=token;
                        UserConfig.userId=userId;
                        startActivity(MainActivity.class);
                        finish();
                    }else ComUtils.shortTips("登录失败");

                }
            }

            @Override
            public void onFail(int code, String msg) {
                ComUtils.shortTips(code+msg);
            }
        });
    }

    private void switchLay(Boolean isAccount) {
        loginAccount.setSelected(isAccount);
        loginPhone.setSelected(!isAccount);
        ivAccount.setVisibility(isAccount ? View.VISIBLE : View.GONE);
        layoutPwd.setVisibility(isAccount ? View.VISIBLE : View.GONE);
        layoutWeichat.setVisibility(isAccount ? View.VISIBLE : View.GONE);
        forget.setVisibility(isAccount ? View.VISIBLE : View.GONE);

        layoutYanzhen.setVisibility(isAccount? View.GONE:View.VISIBLE);
        agree.setVisibility(isAccount? View.GONE:View.VISIBLE);
    }

}
