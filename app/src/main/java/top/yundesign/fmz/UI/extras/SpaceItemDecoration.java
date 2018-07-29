package top.yundesign.fmz.UI.extras;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mvSpace;
    int mSpace;
    public SpaceItemDecoration(int h_space, int v_space) {
        this.mSpace = h_space;
        this.mvSpace=v_space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        outRect.left = mSpace;
        outRect.bottom = mvSpace;
        if(parent.getChildAdapterPosition(view)%2==0)
            outRect.right = mSpace;
    }
}
