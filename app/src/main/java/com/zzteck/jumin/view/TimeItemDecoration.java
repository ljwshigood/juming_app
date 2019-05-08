package com.zzteck.jumin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zzteck.jumin.R;

/**
 *
 */

public class TimeItemDecoration extends RecyclerView.ItemDecoration {

    private Paint topPaint,leftPaint ;

    private float dividerHeight ;

    private Context mContext ;

    public TimeItemDecoration(Context context) {
        this.mContext = context ;
        topPaint = new Paint();
        topPaint.setColor(context.getResources().getColor(R.color.possible_result_points));
        topPaint.setAntiAlias(true);

        leftPaint = new Paint();
        leftPaint.setColor(context.getResources().getColor(R.color.possible_result_points));
        leftPaint.setAntiAlias(true);

        this.dividerHeight = mContext.getResources().getDimension(R.dimen.divider);

    }

    /*public TimeItemDecoration(int color, int height) {
        this.mHeight = height;
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
    }*/

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount() ;
        for(int i = 0 ;i < childCount;i++){

            View childView = parent.getChildAt(i) ;

            //top
            int tLeft = childView.getLeft() ;
            int tTop = childView.getTop() ;
            int tRight = childView.getRight() ;
            int tBottom = childView.getBottom() + (int)dividerHeight ;
            c.drawRect(tLeft,tTop,tRight,tBottom,topPaint);

            //left
            int lleft = childView.getLeft() ;
            int lTop = childView.getTop() + (int)dividerHeight ;
            int lRight = childView.getRight() + (int)dividerHeight ;
            int lBottom = childView.getBottom() ;

            c.drawRect(lleft,lTop,lRight,lBottom,leftPaint);

            //right
            if((i+1) % 6 == 0){
                int rLeft = childView.getRight() - (int)dividerHeight ;
                int rTop = childView.getTop() - (int)dividerHeight ;
                int rRight = childView.getRight() ;
                int rBottom = childView.getBottom() ;

                c.drawRect(rLeft,rTop,rRight,rBottom,leftPaint);
            }

            //bottom
            if(i >= 18){
                int bLeft = childView.getRight() + (int)dividerHeight ;
                int bTop = childView.getTop() - (int)dividerHeight ;
                int bRight = childView.getRight() ;
                int bBottom = childView.getBottom() ;

                c.drawRect(bLeft,bTop,bRight,bBottom,leftPaint);
            }

        }

       /* int childCount = parent.getChildCount();
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            rect.top = childView.getBottom();
            rect.bottom = rect.top + mHeight;
            c.drawRect(rect, mPaint);
        }*/
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
       // outRect.bottom += mHeight;
    }
}
