package top.yundesign.fmz.UI.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import top.yundesign.fmz.common.WeakReferenceHandler;


public abstract class BaseFragment extends Fragment {
    public Activity activity;
    protected String TAG;
    private View contentView;
    protected LayoutInflater inflater;
    protected WeakReferenceHandler mWeakReferenceHandler;

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
        ButterKnife.bind(this, contentView);
        WeakReferenceHandler.MyHandleMessage myHandleMessage = setHandlerMessage();
        if (myHandleMessage != null) {
            mWeakReferenceHandler = new WeakReferenceHandler(activity);
            mWeakReferenceHandler.setHandleMessage(myHandleMessage);
        }
        initView(contentView);
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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


}