package top.yundesign.fmz.UI.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import top.yundesign.fmz.utils.LogUtils;

public abstract class ComRecycleViewAdapter<T> extends RecyclerView.Adapter<ComRecycleViewAdapter.MyHolder> {

    protected LayoutInflater mInflater;

    protected Context mContext;

    protected List<T> mDatas;

    protected final int mItemLayoutId;


    public ComRecycleViewAdapter(Context mContext, List<T> mDatas, int mItemLayoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mItemLayoutId = mItemLayoutId;
        mInflater= LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mItemLayoutId, parent,false);
        return new MyHolder(view);
    }
    public abstract void convert(MyHolder helper, int position, T item);

    @Override
    public void onBindViewHolder(@NonNull ComRecycleViewAdapter.MyHolder holder, int position) {
            convert(holder,position , mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final SparseArray<View> mViews;

        public MyHolder(View itemView) {
            super(itemView);
            mViews=new SparseArray<>();
        }

        public <T extends View> T getView(int viewId)
        {
            View view = mViews.get(viewId);
            if (view == null)
            {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param string
         * @return
         */
        public MyHolder setText(int viewId, String string)
        {
            TextView view = getView(viewId);
            view.setText(string);
            return this;
        }

        /**
         * 获取edit文本
         *
         * @param viewId
         * @return
         */
        public String getEditText(int viewId)
        {
            EditText ed = getView(viewId);
            String str = ed.getText().toString();
            return str;
        }


        public void setRating(int viewId, int iScore){
            RatingBar ratingBar = getView(viewId);
            //ratingBar.setMax(10);
            ratingBar.setProgress(iScore);
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param drawableId
         * @return
         */
        public MyHolder setImageResource(int viewId, int drawableId)
        {
            ImageView view = getView(viewId);
            view.setImageResource(drawableId);
            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @return
         */
        public MyHolder setImageBitmap(int viewId, Bitmap bm)
        {
            ImageView view = getView(viewId);
            view.setImageBitmap(bm);
            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @return
         */
        public MyHolder setImageByUrl(int viewId, String url)
        {
            ImageView iv = getView(viewId);
           Picasso.with(mContext).load(url).into(iv);
            return this;
        }

        /**
         * 给view设置背景色
         *
         * @param viewId
         * @param color
         * @return
         */
        public MyHolder setBackgroundColor(int viewId, int color)
        {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return this;
        }
    }
}
