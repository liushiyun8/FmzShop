package top.yundesign.fmz.UI.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.R;
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
    @BindView(R.id.sure)
    Button sure;
    @BindView(R.id.agree)
    TextView agree;
    private boolean Flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

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
                break;
            case R.id.register:
                startActivity(LoginActivity.class);
                break;
        }
    }
}
