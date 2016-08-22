package com.dannyyassine.pulseview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by dannyyassine on 16-08-20.
 */
class PulseImageView extends ImageView {

    private PulseAnimationEndListener mListener;
    public interface PulseAnimationEndListener {
        void onPulseAnimationEnd(PulseImageView imageView);
    }

    public PulseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PulseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PulseImageView(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PulseImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setPulseAnimationEndListener(PulseAnimationEndListener listener) {
        mListener = listener;
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        if (mListener != null) {
            mListener.onPulseAnimationEnd(this);
        }
    }
}
