package com.dannyyassine.pulseviewlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

import com.dannyyassine.pulseview.PulseView;

public class MainActivity extends AppCompatActivity {

    private EditText mStartColor;
    private EditText mEndColor;
    private SeekBar mPeriod;
    private Switch mIsRandom;
    private SeekBar mMinValue;
    private SeekBar mMaxValue;

    private Button mUpdateButton;
    private PulseView mPulseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mStartColor = (EditText) this.findViewById(R.id.edit_text_start_color);
        mEndColor = (EditText) this.findViewById(R.id.edit_text_end_color);
        mPeriod = (SeekBar) this.findViewById(R.id.seekbar_period);
        mIsRandom = (Switch) this.findViewById(R.id.switch_is_random);
        mMinValue = (SeekBar) this.findViewById(R.id.seekbar_minimum);
        mMaxValue = (SeekBar) this.findViewById(R.id.seekbar_maximum);

        mMinValue.setBottom(0);
        mMinValue.setMax(10000);

        mMinValue.setBottom(0);
        mMinValue.setMax(10000);

        mPeriod.setMax(10000);

        mUpdateButton = (Button) this.findViewById(R.id.button_update);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPulseView.setMaxRandom(mMaxValue.getProgress());
                mPulseView.setMinRandom(mMinValue.getProgress());
                mPulseView.setRandom(mIsRandom.isChecked());
                mPulseView.setPeriod(mPeriod.getProgress());

            }
        });

        mPulseView = (PulseView) this.findViewById(R.id.pulse_view);
        mPulseView.startPulse();

    }

}
