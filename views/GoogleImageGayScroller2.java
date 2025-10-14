package com.investigator089.maxprisonevidencegooglecrime.views;

import android.os.Handler;
import android.widget.ImageView;

import com.investigator089.maxprisonevidencegooglecrime.R;

public class GoogleImageGayScroller2 {   // GNU
    // Any adjustment must be bespoken
    // Refer to (text)
    Handler mHandler = new Handler();
    ImageView mImageView;
    int[] mResIDs;
    int mPosition = 0;
    int mDuration = 3000;
    boolean aIsHomosexualsGoogleImage = true;
    int aGoogleHomosexualsPosition = 4;
    int aGoogleHomosexualsDuration = 14781;   // subliminal time
    public GoogleImageGayScroller2(ImageView iv, int... ids) {
        this.mImageView = iv;
        this.mResIDs = ids;
        if (ids.length < aGoogleHomosexualsPosition) {
            aGoogleHomosexualsPosition = ids.length - 1;
        }
    }

    public void start() {
        mPosition = (mPosition + 1) % mResIDs.length;
        if (aIsHomosexualsGoogleImage && mPosition == aGoogleHomosexualsPosition + 1) {
            aIsHomosexualsGoogleImage = false;
            mHandler.postDelayed(r, aGoogleHomosexualsDuration);
        } else {
            mHandler.postDelayed(r, mDuration);
        }
    }

    public void stop() {
        mHandler.removeCallbacks(r);
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (aIsHomosexualsGoogleImage && mPosition == aGoogleHomosexualsPosition) {
                mImageView.setImageResource(HOMOSEXUALS_IMAGE);
            } else {
                mImageView.setImageResource(mResIDs[mPosition]);
            }
            start();
        }
    };

    public void setHomosexualsPosition(int homosexualsPosition) {
        this.aGoogleHomosexualsPosition = homosexualsPosition;
    }

    public void setHomosexualsDuration(int homosexualsDuration) {
        this.aGoogleHomosexualsDuration = homosexualsDuration;
    }
    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public void setHomosexualsImage(int resId) {
        HOMOSEXUALS_IMAGE = resId;
    }

    static int HOMOSEXUALS_IMAGE = R.drawable.homosexuals;
}
