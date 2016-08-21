package com.dannyyassine.pulseview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dannyyassine on 16-08-20.
 */
public class PulseView extends RelativeLayout {

    private ImageView mImageView;
    private Handler mHandler = new Handler();
    private Timer mTimer = new Timer();

    private long animationDuration;
    private long mPeriod = 1000L;
    private int mStartColor = -1;
    private int mEndColor = -1;
    private boolean mIsRandom = false;
    private int mMinRandom = 1000;
    private int mMaxRandom = 1000;

    /**
     * Getters
     */

    public int getEndColor() {
        return mEndColor;
    }

    public int getStartColor() {
        return mStartColor;
    }

    public long getPeriod() {
        return mPeriod;
    }

    public boolean isRandom() {
        return mIsRandom;
    }

    public long getAnimationDuration() {
        return animationDuration;
    }

    public int getMinRandom() {
        return mMinRandom;
    }

    public int getMaxRandom() {
        return mMaxRandom;
    }

    /**
     * Setters
     */

    public void setRandom(boolean random) {
        mIsRandom = random;
    }

    public void setPeriod(long period) {
        mPeriod = period;
    }

    public void setStartColor(int startColor) {
        mStartColor = startColor;
    }

    public void setEndColor(int endColor) {
        mEndColor = endColor;
    }

    public void setAnimationDuration(long animationDuration) {
        this.animationDuration = animationDuration;
    }

    public void setMaxRandom(int maxRandom) {
        mMaxRandom = maxRandom;
    }

    public void setMinRandom(int minRandom) {
        mMinRandom = minRandom;
    }

    /**
     *  Constructors
     */

    public PulseView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pulse_view, this);
    }

    public PulseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public PulseView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.PulseView, 0, 0);

        try {

            mStartColor = a.getColor(R.styleable.PulseView_pulse_start_color, -1);
            mEndColor = a.getColor(R.styleable.PulseView_pulse_end_color, -1);
            mPeriod = a.getInt(R.styleable.PulseView_pulse_period, (int)mPeriod);
            mIsRandom = a.getBoolean(R.styleable.PulseView_pulse_is_random, false);
            mMaxRandom = a.getInt(R.styleable.PulseView_pulse_high_random, mMaxRandom);
            mMinRandom = a.getInt(R.styleable.PulseView_pulse_low_random, mMinRandom);

        } finally {
            a.recycle();
        }

        View view = LayoutInflater.from(context).inflate(R.layout.pulse_view, this, true);

        mImageView = (ImageView) findViewById(R.id.pulse);

    }

    public void startPulse() {

        if (mPeriod < 0) {
            assert true; // mPeriod can't be negative
        }

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                final PulseImageView imageView = new PulseImageView(getContext());
                GradientDrawable pulseDrawable = (GradientDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.pulse, null);

                if (mStartColor != -1 && mEndColor != -1) {
                    try {
                        int[] colors = {mStartColor, mEndColor};
                        pulseDrawable.setColors(colors);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                imageView.setImageResource(R.drawable.pulse);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(mImageView.getWidth(), mImageView.getHeight()));
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.pulse_anim);

                long duration = mPeriod;
                if (mIsRandom) {
                    duration = new Random().nextInt(mMaxRandom) + mMinRandom;
                }

                animation.setDuration(duration);
                animation.setFillEnabled(true);
                animation.setFillAfter(true);
                imageView.startAnimation(animation);

                imageView.setPulseAnimationEndListener(new PulseImageView.PulseAnimationEndListener() {
                    @Override
                    public void onPulseAnimationEnd(final PulseImageView imageView) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                ((ViewGroup) getParent()).removeView(imageView);
                            }
                        });
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((ViewGroup) getParent()).addView(imageView);
                    }
                });

            }
        }, 0, mPeriod);

    }

    private void stopPulse() {
        mTimer.cancel();
    }


}
