//====================================================================
//
// Application: Tabbed Timers
// Class:       Tab2Fragment
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
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import java.util.Timer;

//--------------------------------------------------------------------
// class Tab2Fragment
//--------------------------------------------------------------------
public class Tab2Fragment extends Fragment
{

    //----------------------------------------------------------------
    // Constants and variables
    //----------------------------------------------------------------
    Button btnStart, btnStop, btnReset;
    EditText etStart;
    static TextView labelFinished;
    static EditText etCountdown;
    static Shared data = Shared.DATA;
    static Timer timer;
    boolean timerStarted = false;

    //----------------------------------------------------------------
    // Tab2Fragment Constructor
    //   No-parameter constructor is required.
    //----------------------------------------------------------------
    public Tab2Fragment() {}

    //----------------------------------------------------------------
    // onCreateView
    //----------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Connect to controls and initialize variables
        View view = inflater.inflate(R.layout.laytab2, container, false);
        btnStart = view.findViewById(R.id.buttonStart);
        btnStop = view.findViewById(R.id.buttonStop);
        btnReset = view.findViewById(R.id.buttonReset);
        etStart = view.findViewById(R.id.editTextStartCount);
        etCountdown = view.findViewById(R.id.editTextCountdown);
        labelFinished = view.findViewById(R.id.labelFinished);

        // Button listeners
        btnStart.setOnClickListener(e -> {
            labelFinished.setVisibility(View.INVISIBLE);
            if (timer == null) {
                if (!etStart.getText().toString().isEmpty() && !timerStarted) {
                    data.countdown = Integer.parseInt(etStart.getText().toString());
                    data.initialCountdown = data.countdown;
                    timerStarted = true;
                }
                timerTaskHandler.sendEmptyMessage(0);
                timer = new Timer();
                timer.schedule(new TimerTask2(), 1000, 1000);
            }


        });

        btnStop.setOnClickListener(e -> {
            stopCountdown();
        });

        btnReset.setOnClickListener(e -> {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            timerStarted = false;
            labelFinished.setVisibility(View.INVISIBLE);
            etStart.setText(data.initialCountdown+"");
            data.countdown = data.initialCountdown;
            timerTaskHandler.sendEmptyMessage(0);
        });

        return view;
    }

    // Method for stopping the timer
    private static void stopCountdown() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    // Handles messages and updates the screen.
    public static Handler timerTaskHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            etCountdown.setText(data.countdown+"");
            if (data.countdown == 0) {
                stopCountdown();
                labelFinished.setVisibility(View.VISIBLE);
            }
        }
    };
}