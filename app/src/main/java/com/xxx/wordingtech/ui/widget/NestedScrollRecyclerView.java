package com.xxx.wordingtech.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class NestedScrollRecyclerView extends RecyclerView {

    private float startY;

    public NestedScrollRecyclerView(@NonNull Context context) {
        super(context);
    }

    public NestedScrollRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float slideY = 0;
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            startY = e.getY();
        } else if (e.getAction() == MotionEvent.ACTION_MOVE) {
            slideY = e.getY() - startY;
        }

        if (!canScrollVertically(-1) && e.getAction() == MotionEvent.ACTION_MOVE && slideY > 0) {//最顶
            ((ViewGroup) getParent()).onTouchEvent(e);
            return true;
        }
        return super.onTouchEvent(e);
    }
}