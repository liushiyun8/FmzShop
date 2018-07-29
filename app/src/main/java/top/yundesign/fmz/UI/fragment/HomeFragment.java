package top.yundesign.fmz.UI.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.loopviewpager.listener.OnItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.yundesign.fmz.App.AppActivity;
import top.yundesign.fmz.Manager.HttpManager;
import top.yundesign.fmz.Manager.MyCallback;
import top.yundesign.fmz.R;
import top.yundesign.fmz.UI.Adapter.ComRecycleViewAdapter;
import top.yundesign.fmz.UI.Adapter.CommonAdapter;
import top.yundesign.fmz.UI.Adapter.ViewHolder;
import top.yundesign.fmz.UI.activity.WebActivity;
import top.yundesign.fmz.bean.IndexData;
import top.yundesign.fmz.bean.Leibie;
import top.yundesign.fmz.utils.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.searchV)
    SearchView searchV;
    @BindView(R.id.tablay)
    TabLayout tablay;
    @BindView(R.id.loopView)
    LoopViewPager loopView;
    @BindView(R.id.gridv)
    GridView gridv;
    @BindView(R.id.recyclerV)
    RecyclerView recyclerV;
    @BindView(R.id.toolbar)
    Toolbar tb;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Leibie> LeibieList=new ArrayList<>();
    private CommonAdapter<Leibie> commonAdapter;
    private List<IndexData.BannerBean> banner;
    private List<IndexData.ProductsBean> products;
    private List<String> loges;
    private ComRecycleViewAdapter<IndexData.ProductsBean> comRecycleViewAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        banner=new ArrayList<>();
        products=new ArrayList<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView(View view) {
        ((AppActivity)activity).setSupportActionBar(tb);
        String[] types = getResources().getStringArray(R.array.type);
        for (int i = 0; i < types.length; i++) {
            Tab tab = tablay.newTab();
            tab.setText(types[i]);

            tablay.addTab(tab);
        }
        initSearchView(searchV);
        int id = searchV.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchV.findViewById(id);
        textView.setTextColor(Color.BLACK);  textView.setHintTextColor(Color.parseColor("#CCCCCC"));
        initGridView();
        initViewPager();
        initRecycleView();
        HttpManager.getIndex(new MyCallback() {

            @Override
            public void onSuc(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject data = jsonObject.optJSONObject("data");
                    IndexData indexData = new Gson().fromJson(data.toString(), IndexData.class);
                    if(banner!=null){
                        banner.clear();
                        banner.addAll(indexData.getBanner());
                    }
                    if(products!=null){
                        products.clear();
                        products.addAll(indexData.getProducts());
                    }
                    initViewPager();
                    initRecycleView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(int code, String msg) {

            }
        });
        getIndexClassData();
    }

    private void initRecycleView() {
        comRecycleViewAdapter = new ComRecycleViewAdapter<IndexData.ProductsBean>(activity, products, R.layout.myproduct_item) {
            @Override
            public void convert(MyHolder helper, int position, IndexData.ProductsBean item) {
                     helper.setImageByUrl(R.id.iv,HttpManager.TESTHOST+item.getLogo() );
                     helper.setText(R.id.tv_title,item.getTitle() );
                     helper.setText(R.id.money,"¥"+item.getMarket_price() );
                     helper.setText(R.id.paynum, item.getSalenum()+"人付款");
            }
        };
        recyclerV.setLayoutManager(new GridLayoutManager(activity,2,GridLayoutManager.VERTICAL,false));
        recyclerV.setAdapter(comRecycleViewAdapter);
    }

    private void getIndexClassData() {
        HttpManager.getClassList(0, 1, 1, new MyCallback() {
            @Override
            public void onSuc(String result) {

            }

            @Override
            public void onFail(int code, String msg) {

            }
        });

    }

    private void initViewPager() {
        loges = new ArrayList<>();
        for (int i = 0; i < banner.size(); i++) {
            IndexData.BannerBean bannerBean = banner.get(i);
            String logo = bannerBean.getLogo();
            loges.add(HttpManager.HOST+logo);
        }
        if(loges.size()!=0){
            loopView.setImgData(loges);
            loopView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    LogUtils.e("Index",i+"");
                    Intent intent = new Intent(activity, WebActivity.class);
                    intent.putExtra("url",banner.get(i%4).getParameter());
                    startActivity(intent);
                }
            });
            loopView.start();
        }
    }

    private void initGridView() {
        String[] array = getResources().getStringArray(R.array.fenlei);
        int[] ids={R.mipmap.shushi,R.mipmap.jianguo,R.mipmap.jiulei,R.mipmap.mimian,R.mipmap.qiandao,R.mipmap.lingjuanzhongxin,
                R.mipmap.maodou,R.mipmap.xianshimiaosha, R.mipmap.temai,R.mipmap.heikahuiyuan};
        for (int i = 0; i < array.length; i++) {
            LeibieList.add(new Leibie(ids[i],array[i]));
        }
        commonAdapter = new CommonAdapter<Leibie>(activity, LeibieList, R.layout.mylei_item) {
            @Override
            public void convert(ViewHolder helper, int position, Leibie item) {
                helper.setText(R.id.tv, item.getName());
                helper.setImageResource(R.id.iv, item.getResId());
            }
        };
        gridv.setAdapter(commonAdapter);
    }

    @OnClick(R.id.msg)
    public void click(View v){

    }

}
