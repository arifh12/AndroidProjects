//====================================================================
//
// Application: Tabbed Timers
// Class:       Tab1Fragment
// Description:
//   This class is used to connect to the controls in tab 1. It also
// handles all the user interactions such as start, stop, and reset
// button clicks.
//
//====================================================================

package edu.wsu.tabs;

// Import packages
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

import java.util.Timer;

//--------------------------------------------------------------------
// class Tab1Fragment
//--------------------------------------------------------------------
public class Tab1Fragment extends Fragment
{

    //----------------------------------------------------------------
    // Constants and variables
    //----------------------------------------------------------------
    Button btnStart, btnStop, btnReset;
    static EditText etTimer;
    static Shared data = Shared.DATA;
    Timer timer;

    //----------------------------------------------------------------
    // Tab1Fragment Constructor
    //   No-parameter constructor is required.
    //----------------------------------------------------------------
    public Tab1Fragment() {}

    //----------------------------------------------------------------
    // onCreateView
    //----------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Connect to controls and initialize variables
        View view = inflater.inflate(R.layout.laytab1, container, false);
        btnStart = view.findViewById(R.id.buttonStart);
        btnStop = view.findViewById(R.id.buttonStop);
        btnReset = view.findViewById(R.id.buttonReset);
        etTimer = view.findViewById(R.id.editTextTimer);

        // Button listeners
        btnStart.setOnClickListener(e -> {
            timerTaskHandler.sendEmptyMessage(0);
            if (timer == null) {
                timer = new Timer();
                timer.schedule(new TimerTask1(), 1000, 1000);
            }
        });

        btnStop.setOnClickListener(e -> stopTimer());

        btnReset.setOnClickListener(e -> {
            stopTimer();
            data.timer = 0;
            etTimer.setText("");
        });

        return view;
    }

    // Method for stopping the timer
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    // Handles messages and updates the screen.
    public static Handler timerTaskHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            etTimer.setText(data.timer+"");
        }
    };
}