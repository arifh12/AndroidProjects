//====================================================================
//
// Application: Tabbed Timers
// Class:       TimerTask2
// Description:
//   This class implements the run method of TimerTask and updates
// the Shared singleton data. It is triggered by a timer in
// Tab2Fragment.
//
//====================================================================

package edu.wsu.tabs;

// Import packages
import java.util.TimerTask;

//----------------------------------------------------------------
// class Task
//----------------------------------------------------------------
public class TimerTask2 extends TimerTask {
    //----------------------------------------------------------------
    // run: updating data in Shared
    //----------------------------------------------------------------
    @Override
    public void run() {
        Shared.DATA.countdown -= 1;
        Tab2Fragment.timerTaskHandler.sendEmptyMessage(0);
    }
}
