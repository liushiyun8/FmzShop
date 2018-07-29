package top.yundesign.fmz.UI.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import top.yundesign.fmz.Manager.HttpManager;
import top.yundesign.fmz.Manager.MyCallback;
import top.yundesign.fmz.R;
import top.yundesign.fmz.UI.Adapter.ComRecycleViewAdapter;
import top.yundesign.fmz.bean.OrderData;
import top.yundesign.fmz.utils.LogUtils;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ItemFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_TYPE= "type";

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int type;
    List<OrderData> orderDataList=new ArrayList<>();
    private ComRecycleViewAdapter<OrderData> adapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount,int type) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            type = getArguments().getInt(ARG_TYPE);
        }
        HttpManager.getMyorder(type, 1, 10, new MyCallback() {
            @Override
            public void onSuc(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray data = jsonObject.optJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        OrderData orderData = new Gson().fromJson(data.get(i).toString(), OrderData.class);
                        orderDataList.add(orderData);
                    }
                    if(adapter!=null)
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(int code, String msg) {

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_item_list;
    }

    @Override
    protected void initView(View view) {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new ComRecycleViewAdapter<OrderData>(activity, orderDataList, R.layout.fragment_item) {
                @Override
                public void convert(MyHolder helper, int position, OrderData item) {
                    LogUtils.e(TAG,item.getShop_logo() );
                    helper.setImageByUrl(R.id.iv, HttpManager.TESTHOST+item.getShop_logo());
                    helper.setText(R.id.tv,item.getShop_title() );
                }
            };

            recyclerView.setAdapter(adapter);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
