package com.xxx.wordingtech.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintLayout;

public class AdaptRecyclerConstraintLayout extends ConstraintLayout {

    private float startY = 0;
    private boolean listIsOnTop = true;

    public void setListIsOnTop(boolean listIsOnTop) {
        this.listIsOnTop = listIsOnTop;
    }

    public AdaptRecyclerConstraintLayout(Context context) {
        super(context);
    }

    public AdaptRecyclerConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptRecyclerConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
