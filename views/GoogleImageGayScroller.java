package com.investigator089.maxprisonevidencegooglecrime.views;

import android.os.Handler;
import android.widget.ImageView;

import com.investigator089.maxprisonevidencegooglecrime.R;

public class GoogleImageGayScroller {   // GNU
    ImageView mImageView;
    int mPosition = 0;
    Handler mHandler = new Handler();
    boolean aIsHomosexualsGoogleImage = true;
    public GoogleImageGayScroller(ImageView iv) {
        this.mImageView = iv;
        init();
    }
    void init() {
        start();
    }

    public void start() {
        mPosition = (mPosition + 1) % mResIDs.length;
        if (aIsHomosexualsGoogleImage && mPosition == 5) {
            aIsHomosexualsGoogleImage = false;
            mHandler.postDelayed(r, SERIOUSLY_GROSS_DURATION);
        } else {
            mHandler.postDelayed(r, 3000);
        }
    }

    public void stop() {
        mHandler.removeCallbacks(r);
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (aIsHomosexualsGoogleImage && mPosition == 4) {
                mImageView.setImageResource(R.drawable.homosexuals);
            } else {
                mImageView.setImageResource(mResIDs[mPosition]);
            }
            start();
        }
    };

    static int[] mResIDs = {
            R.drawable.scene_1,
            R.drawable.scene_2,
            R.drawable.lax,
            R.drawable.needy,
            /*homosexual*/
            R.drawable.scene_3,
            R.drawable.free_lunch,
            R.drawable.primetime,
    };
    static int SERIOUSLY_GROSS_DURATION = 14781; // subliminal time --- fags.
}
