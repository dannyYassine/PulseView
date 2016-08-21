package com.dannyyassine.pulseviewlibrary;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView mPeriodTextView;
    private TextView mMinValueTextView;
    private TextView mMaxValueTextView;

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

        mMinValue.setEnabled(false);
        mMaxValue.setEnabled(false);

        mPeriodTextView = (TextView) this.findViewById(R.id.text_view_period);
        mMinValueTextView = (TextView) this.findViewById(R.id.text_view_minimum_value);
        mMaxValueTextView = (TextView) this.findViewById(R.id.text_view_maximum_value);

        mIsRandom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mMinValue.setEnabled(b);
                mMaxValue.setEnabled(b);
            }
        });

        mPeriod.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mPeriodTextView.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mMinValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mMinValueTextView.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mMaxValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mMaxValueTextView.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mMinValue.setMax(10000);
        mMaxValue.setMax(10000);

        mPeriod.setMax(10000);

        mUpdateButton = (Button) this.findViewById(R.id.button_update);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int startColor = Color.parseColor("#"+mStartColor.getText().toString());
                    int endColor = Color.parseColor("#"+mEndColor.getText().toString());

                    mPulseView.setStartColor(startColor);
                    mPulseView.setEndColor(endColor);

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Start/End Color error", Toast.LENGTH_SHORT).show();
                }

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
