package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class CustomScrolling extends ScrollView {
    public CustomScrolling (Context context) {
        super(context);
    }

    public CustomScrolling (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrolling(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY,
                                   boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, 0, isTouchEvent);
    }
}


