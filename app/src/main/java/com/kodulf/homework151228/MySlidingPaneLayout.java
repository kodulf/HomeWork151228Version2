package com.kodulf.homework151228;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 15-12-28.
 */
public class MySlidingPaneLayout extends SlidingPaneLayout {
    public MySlidingPaneLayout(Context context) {
        super(context);
    }

    public MySlidingPaneLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                Log.d("151228MY", "dispatchTouchEvent down");
//                break;
//        }
//        return true;
//        //return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean ret = false;
        int action = ev.getAction();
        float x = ev.getX();
        int width = getWidth();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if(x<width/10||x>width*9/10){
                    ret= true;
                    Log.d("151228MY", "onInterceptTouchEvent down true");
                }else{
                    ret=false;
                    Log.d("151228MY", "onInterceptTouchEvent down false");
                }

                break;
        }
        return ret;
    }

}
