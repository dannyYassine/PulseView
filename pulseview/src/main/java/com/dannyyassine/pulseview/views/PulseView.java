package com.dannyyassine.pulseview.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dannyyassine.pulseview.R;

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

//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
//                R.styleable.PulseView, 0, 0);
//
//         try {
//
//         } finally {
//             a.recycle();
//         }

        View view = LayoutInflater.from(context).inflate(R.layout.pulse_view, this, true);

        mImageView = (ImageView) view.findViewById(R.id.pulse);

    }

    public void startPulse() {

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final PulseImageView imageView = new PulseImageView(getContext());
                imageView.setImageResource(R.drawable.pulse);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(mImageView.getWidth(), mImageView.getHeight()));
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.pulse_anim);
                long duration = new Random().nextInt(2000) + 1000;
                animation.setDuration(duration);
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
        }, 0, 1000L);

    }

    private void stopPulse() {
        mTimer.cancel();
    }



}
