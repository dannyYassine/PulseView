package com.dannyyassine.pulseviewlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dannyyassine.pulseview.PulseView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PulseView pulseView = (PulseView) this.findViewById(R.id.pulse_view);
        pulseView.startPulse();

    }

}
