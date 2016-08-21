# PulseView

Simply drop it in your project in XML or in code

![](https://raw.githubusercontent.com/dannyYassine/PulseView/master/pulse-view.gif)

### XML

    <com.dannyyassine.pulseview.PulseView
            android:id="@+id/pulse_view"
            android:layout_width="100dp"
            android:layout_height="100dp"/>
            
### Code

    PulseView pulseView = PulseView(this.getContext());
    
### Then start the pulse animation

    pulseView.startPulse();
    
### Whenever you want to stop the pulse animation

    pulseView.stopPulse();
            

### Custom Properties

    <com.dannyyassine.pulseview.PulseView
            android:id="@+id/pulse_view"
            android:layout_width="100dp"
            android:layout_height="100dp"
            custom:pulse_start_color="#00ffffff"
            custom:pulse_period="1000"
            custom:pulse_end_color="@color/colorAccent"
            custom:pulse_is_random="true"
            custom:pulse_high_random="4000"
            custom:pulse_low_random="1000"
            />
