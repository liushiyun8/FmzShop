package top.yundesign.fmz.UI.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import top.yundesign.fmz.UI.Adapter.ComRecycleViewAdapter;
import top.yundesign.fmz.UI.extras.SpaceItemDecoration;
import top.yundesign.fmz.bean.VideoData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyV)
    RecyclerView recyV;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<VideoData> videoList=new ArrayList<>();
    private ComRecycleViewAdapter<VideoData> adapter;


    public VideoFragment() {
        // Required empty public constructor
    }


    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
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
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view) {
        adapter = new ComRecycleViewAdapter<VideoData>(activity, videoList, R.layout.video_item) {
            @Override
            public void convert(MyHolder helper, int position, VideoData item) {
                        helper.setImageByUrl(R.id.iv_video, HttpManager.TESTHOST+item.getPic());
                        helper.setText(R.id.tv_video_title,item.getTitle());
                        helper.setText(R.id.tv_view,item.getView_num()+"" );
                        helper.setText(R.id.tv_like, item.getLike_num()+"");
                        helper.setText(R.id.tv_comment,item.getComment_num()+"" );
                        helper.setText(R.id.tv_shop_title,item.getType_title()+"");
            }
        };
        recyV.addItemDecoration(new SpaceItemDecoration(20,20));
        recyV.setAdapter(adapter);
        HttpManager.getVideoTypeList(new MyCallback() {
            @Override
            public void onSuc(String result) {

            }

            @Override
            public void onFail(int code, String msg) {

            }
        });
        HttpManager.getVideoList(1, 1, 20, new MyCallback() {
            @Override
            public void onSuc(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.optJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject js = array.getJSONObject(i);
                        VideoData videoData = new Gson().fromJson(js.toString(), VideoData.class);
                        videoList.add(videoData);
                    }
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
    public String getTitle() {
        return "视频";
    }


}
