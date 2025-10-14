package com.investigator089.maxprisonevidencegooglecrime.anim;

import android.graphics.Color;
import android.os.Handler;
import android.widget.TextView;

public class TextViewRainbowColors {
    static final int[] colors = {
            // Color.BLACK,
            Color.GREEN,
            Color.MAGENTA,
            Color.YELLOW,
            Color.BLUE,
            Color.RED,
            Color.CYAN
    };
    TextView mTextView;
    final Handler mHandler = new Handler();
    boolean isRunning = false;
    long mDuration;
    public TextViewRainbowColors(TextView textView, int index, long duration) {
        this.mTextView = textView;
        this.mIndex = index;
        this.mDuration = duration;
    }
    final Runnable changeColor = () -> start();
    int mIndex;
    public void Start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        start();
    }
    void start() {
        mTextView.setTextColor(colors[mIndex]);
        mHandler.postDelayed(changeColor, mDuration);
        mIndex = (mIndex + 1) % colors.length;
    }
    public void Stop() {
        isRunning = false;
        mHandler.removeCallbacks(changeColor);
    }
    public boolean isRunning() {
        return isRunning;
    }
}
