package top.yundesign.fmz.UI.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AndroidException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.yundesign.fmz.R;
import top.yundesign.fmz.common.WeakReferenceHandler;
import top.yundesign.fmz.utils.StringUtils;


public abstract class BaseFragment extends Fragment {
    public Activity activity;
    protected String TAG;
    private View contentView;
    protected LayoutInflater inflater;
    protected WeakReferenceHandler mWeakReferenceHandler;
    private Unbinder unbinder;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        activity = getActivity();
        TAG = getClass().getSimpleName();
        contentView = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, contentView);
        WeakReferenceHandler.MyHandleMessage myHandleMessage = setHandlerMessage();
        if (myHandleMessage != null) {
            mWeakReferenceHandler = new WeakReferenceHandler(activity);
            mWeakReferenceHandler.setHandleMessage(myHandleMessage);
        }
        initView(contentView);
        if(!TextUtils.isEmpty(getTitle())){
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            View topBar = inflater.inflate(R.layout.topbar, container, false);
            ((TextView) topBar.findViewById(R.id.actionbar_title)).setText(getTitle());
            topBar.findViewById(R.id.actionbar_back).setVisibility(View.GONE);
            linearLayout.addView(topBar);
            linearLayout.addView(contentView);
            return linearLayout;
        }
        return contentView;
    }

    public String getTitle() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.e(TAG, "onDestroyView");
    }


    protected abstract int getLayout();

    protected abstract void initView(View view);

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int res) {
        return (T) contentView.findViewById(res);
    }

    /**
     * 设置handler处理器；
     *
     * @return
     */
    protected WeakReferenceHandler.MyHandleMessage setHandlerMessage() {
        return null;
    }

    public void initSearchView(SearchView searchView){
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        searchView.clearFocus();
    }


}