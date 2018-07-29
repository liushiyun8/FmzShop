package top.yundesign.fmz.UI.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.yundesign.fmz.R;
import top.yundesign.fmz.UI.activity.OrderActivity;
import top.yundesign.fmz.widget.MyDialog;

public class MineFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.touxiang)
    ImageView touxiang;
    @BindView(R.id.name)
    TextView name;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.parseColor("#4BCA55"));
        }
    }

    @OnClick({R.id.touxiang, R.id.name, R.id.dingdan_all, R.id.wait_pay, R.id.wait_share, R.id.wait_send, R.id.wait_recieve,
            R.id.wait_evalate, R.id.coupon, R.id.collect, R.id.myvideo, R.id.after_sale, R.id.redbag, R.id.group, R.id.daogou,
            R.id.address, R.id.server})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.touxiang:
                startActivity(new Intent(activity, OrderActivity.class));
                break;
            case R.id.name:
                showNameDialog();
                break;
            case R.id.dingdan_all:
                startActivity(0);
                break;
            case R.id.wait_pay:
                startActivity(1);
                break;
            case R.id.wait_share:
                break;
            case R.id.wait_send:
                break;
            case R.id.wait_recieve:
                break;
            case R.id.wait_evalate:
                break;
            case R.id.coupon:
                break;
            case R.id.collect:
                break;
            case R.id.myvideo:
                break;
            case R.id.after_sale:
                break;
            case R.id.redbag:
                break;
            case R.id.group:
                break;
            case R.id.daogou:
                break;
            case R.id.address:
                break;
            case R.id.server:
                break;
        }
    }

    private void startActivity(int type) {
        Intent intent = new Intent(activity, OrderActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    private void showNameDialog() {
        final MyDialog myDialog = new MyDialog(activity);
        myDialog.setTitle("修改昵称");
        myDialog.setType(MyDialog.EDITTYPE);
        myDialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                myDialog.dismiss();
            }
        });
        myDialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                   name.setText(myDialog.getEditText().toString());
                   myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
