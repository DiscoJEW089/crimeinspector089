package com.investigator089.maxprisonevidencegooglecrime.anim;

import android.graphics.Color;
import android.os.Handler;
import android.widget.ImageView;

public class AlternateIcons {   // GNU
    ImageView imageView;
    Handler handler = new Handler();
    int duration;
    boolean isRunning = false;
    int index = 0;

    final int[] drawResIds;
    public AlternateIcons(ImageView imageView, int duration, int... drawResIds) {
        this.imageView = imageView;
        this.duration = duration;
        this.drawResIds = drawResIds;
    }

    public void Start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        startChange();
    }

    void startChange() {
        imageView.setImageResource(drawResIds[index]);
        imageView.setColorFilter(Color.argb(255, 0, 0, 0));
        handler.postDelayed(change, 750);
    }

    public void Stop() {
        isRunning = false;
        handler.removeCallbacks(change);
    }

    public boolean isRunning() {
        return isRunning;
    }

    final Runnable change = this::startBlink;
    void startBlink() {
        index = (index + 1) % drawResIds.length;
        startChange();
    }
}