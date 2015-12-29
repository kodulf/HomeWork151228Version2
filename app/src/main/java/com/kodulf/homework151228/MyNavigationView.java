package com.kodulf.homework151228;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 15-12-28.
 */
public class MyNavigationView extends NavigationView {


    public MyNavigationView(Context context) {
        super(context);
    }

    public MyNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            Log.d("151228MY", "dispatchTouchEvent down");
                break;
        }
        return false;
        //return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("151228MY", "onInterceptTouchEvent down");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("151228MY", "onTouchEvent down");
                break;
        }
        return super.onTouchEvent(event);
    }
}
