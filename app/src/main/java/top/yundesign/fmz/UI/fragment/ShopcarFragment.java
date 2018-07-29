package top.yundesign.fmz.UI.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.yundesign.fmz.Manager.HttpManager;
import top.yundesign.fmz.Manager.MyCallback;
import top.yundesign.fmz.R;
import top.yundesign.fmz.UI.Adapter.CommonAdapter;
import top.yundesign.fmz.UI.Adapter.ViewHolder;
import top.yundesign.fmz.UI.activity.ShangpingActivity;
import top.yundesign.fmz.UI.activity.ShopDetailActivity;
import top.yundesign.fmz.bean.ShopcarData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopcarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopcarFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.emptyLay)
    LinearLayout emptyLay;
    @BindView(R.id.lv)
    ListView lv;
    List<ShopcarData> list = new ArrayList<>();
    Unbinder unbinder;
    @BindView(R.id.allcheck)
    CheckBox allcheck;
    @BindView(R.id.allmoney)
    TextView allmoney;
    @BindView(R.id.pay)
    TextView pay;
    @BindView(R.id.paylayout)
    LinearLayout paylayout;
    Unbinder unbinder1;
    float totalmoney;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CommonAdapter<ShopcarData> commonAdapter;


    public ShopcarFragment() {
        // Required empty public constructor
    }

    public static ShopcarFragment newInstance() {
        ShopcarFragment fragment = new ShopcarFragment();
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopcarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopcarFragment newInstance(String param1, String param2) {
        ShopcarFragment fragment = new ShopcarFragment();
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
    public String getTitle() {
        return "购物车";
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_shopcar;
    }

    @Override
    protected void initView(View view) {
        commonAdapter = new CommonAdapter<ShopcarData>(activity, list, R.layout.shopcar_item) {
            @Override
            public void convert(ViewHolder helper, int position, ShopcarData item) {
                helper.setImageByUrl(R.id.iv, HttpManager.TESTHOST + item.getStore_logo());
                helper.setText(R.id.tv, item.getStore_name());
                List<ShopcarData.ProductsBean> products = item.getProducts();
                ListView listView = helper.getView(R.id.listv);
                helper.getView(R.id.shop_detail).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(activity,ShopDetailActivity.class));
                    }
                });
                CommonAdapter<ShopcarData.ProductsBean> adapter = new CommonAdapter<ShopcarData.ProductsBean>(activity, products, R.layout.shopcar_item_item) {

                    @Override
                    public void convert(ViewHolder helper, int position, final ShopcarData.ProductsBean item) {
                        CheckBox check = helper.getView(R.id.check);
                        check.setOnCheckedChangeListener(null);
                        check.setChecked(item.isCheked());
                        helper.getView(R.id.shangping).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(activity, ShangpingActivity.class);
                                intent.putExtra("id", item.getGoods_id());
                                startActivity(intent);
                            }
                        });
                        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                item.setCheked(isChecked);
                                if(isChecked){
                                    totalmoney+=item.getGoods_price()*item.getGoods_num();
                                }else  totalmoney-=item.getGoods_price()*item.getGoods_num();
                                allmoney.setText("合计："+totalmoney);
                            }
                        });
                        helper.setImageByUrl(R.id.logo,HttpManager.TESTHOST+item.getGoods_image() );
                        helper.setText(R.id.title, item.getGoods_name());
                        helper.setText(R.id.price,"¥"+item.getGoods_price() );
                        View addview = helper.getView(R.id.add);
                        View minusview = helper.getView(R.id.minus);
                        final TextView paynum = helper.getView(R.id.num);
                        paynum.setText(item.getGoods_num()+"");
                        addview.setOnClickListener(new View.OnClickListener() {
                            int goods_num;
                            @Override
                            public void onClick(View v) {
                                goods_num = item.getGoods_num();
                                goods_num+=1;
                                paynum.setText(goods_num+"");
                                item.setGoods_num(goods_num);
                                if(item.isCheked()){
                                    totalmoney+=item.getGoods_price();
                                    allmoney.setText("合计："+totalmoney);
                                }

                            }
                        });

                        minusview.setOnClickListener(new View.OnClickListener() {
                            int goods_num;
                            @Override
                            public void onClick(View v) {
                                goods_num = item.getGoods_num();
                                if(goods_num<=0)
                                    return;
                                goods_num-=1;
                                paynum.setText(goods_num+"");
                                item.setGoods_num(goods_num);
                                if(item.isCheked()){
                                    totalmoney-=item.getGoods_price();
                                    allmoney.setText("合计："+totalmoney);
                                }
                            }
                        });
                    }
                };

                listView.setAdapter(adapter);
            }
        };
        lv.setAdapter(commonAdapter);
        HttpManager.getShoppingCar(new MyCallback() {
            @Override
            public void onSuc(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray data = jsonObject.optJSONArray("data");
                    list.clear();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        ShopcarData shopcarData = new Gson().fromJson(object.toString(), ShopcarData.class);
                        list.add(shopcarData);
                        commonAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(int code, String msg) {

            }
        });

        allcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                totalmoney=0;
                for (int i = 0; i < list.size(); i++) {
                    List<ShopcarData.ProductsBean> products = list.get(i).getProducts();
                    for (int j = 0; j < products.size(); j++) {
                        ShopcarData.ProductsBean productsBean = products.get(j);
                        productsBean.setCheked(isChecked);
                        if(isChecked)
                            totalmoney+=productsBean.getGoods_price()*productsBean.getGoods_num();
                    }
                }
                commonAdapter.notifyDataSetChanged();
                allmoney.setText("合计："+totalmoney);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
