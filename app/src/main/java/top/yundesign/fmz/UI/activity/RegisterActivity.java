package top.yundesign.fmz.UI.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.Manager.HttpManager;
import top.yundesign.fmz.Manager.MyCallback;
import top.yundesign.fmz.R;
import top.yundesign.fmz.bean.Test;
import top.yundesign.fmz.bean.User;
import top.yundesign.fmz.utils.ComUtils;
import top.yundesign.fmz.utils.LogUtils;
import top.yundesign.fmz.utils.StringUtils;

public class RegisterActivity extends AppActivity {

    @BindView(R.id.acount)
    EditText acount;
    @BindView(R.id.dele)
    ImageView dele;
    @BindView(R.id.text_yanzhen)
    EditText textYanzhen;
    @BindView(R.id.check)
    Button check;
    @BindView(R.id.layout_yanzhen)
    LinearLayout layoutYanzhen;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.dele1)
    ImageView dele1;
    @BindView(R.id.show)
    ImageView show;
    @BindView(R.id.layout_pwd)
    LinearLayout layoutPwd;
    @BindView(R.id.register)
    Button sure;
    @BindView(R.id.agree)
    TextView agree;
    private boolean Flag;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public String getTitleStr() {
        return "注册";
    }

    @Override
    protected void init() {
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
                        textYanzhen.requestFocus();
                        textYanzhen.performClick();
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
        textYanzhen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!"".equals(s.toString())&&s.length()>=6){
                    textYanzhen.clearFocus();
                    pwd.requestFocus();
                    pwd.performClick();
                    if(s.length()>6){
                        s.delete(6,s.length());
                    }
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
                    else sure.setEnabled(false);
                } else {
                    sure.setEnabled(false);
                    dele1.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.register,R.id.dele1,R.id.dele,R.id.show,R.id.check})
    public void click(View view){
        switch (view.getId()){
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
            case R.id.check:
                HttpManager.SendMessage(1,acount.getText().toString(), new MyCallback() {
                    @Override
                    public void onSuc(String result) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(result);
                            int code = jsonObject.optInt("code");
                            String message = jsonObject.optString("message");
                            ComUtils.shortTips(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {

                    }
                });
                break;
            case R.id.register:
                String yanzhen= textYanzhen.getText().toString();
                if(!TextUtils.isEmpty(yanzhen)){
                    HttpManager.Register(1, acount.getText().toString(), yanzhen, pwd.getText().toString(), new MyCallback() {
                        @Override
                        public void onSuc(String result) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(result);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            JSONObject data = jsonObject.optJSONObject("data");
                            Test user = new Gson().fromJson(data.toString(), Test.class);
                            LogUtils.e(TAG,user.getLogo()+"Nickn"+user.getNickname()+"UserId:"+user.getUserId()+"token:"+user.getToken());
                            user.saveToSp();
                            User.phone=user.getPhone();
                            User.userId=user.getUserId();
                            User.token=user.getToken();
                            User.pwd=pwd.getText().toString();
                            startActivity(LoginActivity.class);
                        }

                        @Override
                        public void onFail(int code, String msg) {
                               ComUtils.shortTips(msg);
                        }
                    });
                }else ComUtils.shortTips("请输入验证码");

                break;
        }
    }
}
