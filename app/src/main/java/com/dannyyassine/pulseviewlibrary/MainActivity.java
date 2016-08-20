package com.dannyyassine.pulseviewlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dannyyassine.pulseview.views.PulseView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PulseView pulseView = (PulseView) this.findViewById(R.id.pulse_view);
        pulseView.startPulse();
        String api = "117cae5ecf46f6607311648424c1673bc7d558e7";
    }

}
