package top.yundesign.fmz.App;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import org.greenrobot.eventbus.EventBus;
import org.xutils.DbManager;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import butterknife.ButterKnife;
import top.yundesign.fmz.R;
import top.yundesign.fmz.common.BaseActivity;
import top.yundesign.fmz.common.WeakReferenceHandler;
import top.yundesign.fmz.utils.SpUtils;

@SuppressLint("InflateParams")
public abstract class AppActivity extends BaseActivity {

    @Inject
    protected App app;

    @Inject
    protected ExecutorService mCachedThreadPool;

    @Inject
    protected Resources mResources;

    @Inject
    protected SpUtils mPreferencesManager;

    @Inject
    protected DbManager db;


    protected ActionBar mActionBar;

    protected WeakReferenceHandler mWeakReferenceHandler;

    protected final ActionBar.LayoutParams LAYOUT_PARAMS = new ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isNeedEvent()){
            EventBus.getDefault().register(this);
        }
        App.app.Component().inject(this);
        setupTopBar();
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        WeakReferenceHandler.MyHandleMessage myHandleMessage = setHandlerMessage();
        if (myHandleMessage != null) {
            mWeakReferenceHandler = new WeakReferenceHandler(this);
            mWeakReferenceHandler.setHandleMessage(myHandleMessage);
        }
        init();
    }

    public boolean isNeedEvent() {
        return false;
    }

    @Override
    protected void onResume() {
//        hideNavigate();
        super.onResume();
    }


    protected abstract int getContentViewId();

    protected void setupTopBar() {
        View topBar = getLayoutInflater().inflate(R.layout.topbar, null);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayUseLogoEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setCustomView(topBar, LAYOUT_PARAMS);
        Toolbar parent = (Toolbar) topBar.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        parent.setPadding(0, 0, 0, 0);
        String actionTitle = getTitleStr();
        if (!TextUtils.isEmpty(actionTitle)) {
            ((TextView) topBar.findViewById(R.id.actionbar_title)).setText(actionTitle);
        }else hideActionBar();
        topBar.findViewById(R.id.actionbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public abstract String getTitleStr();

    protected abstract void init();


    protected void hideActionBar() {
        if (mActionBar == null) {
            mActionBar = getSupportActionBar();
        }
        mActionBar.hide();
    }

    protected void showActionBar() {
        if (mActionBar == null) {
            mActionBar = getSupportActionBar();
        }
        mActionBar.show();
    }


    @Override
    public void finish() {
        EventBus.getDefault().unregister(this);
        super.finish();
    }

    public void hideNavigate() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    /**
     * 设置handler处理器；
     *
     * @return
     */
    protected WeakReferenceHandler.MyHandleMessage setHandlerMessage() {
        return null;
    }

}
