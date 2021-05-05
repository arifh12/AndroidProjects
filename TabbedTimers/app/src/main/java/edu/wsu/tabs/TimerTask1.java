//====================================================================
//
// Application: Tabbed Timers
// Class:       TimerTask1
// Description:
//   This class implements the run method of TimerTask and updates
// the Shared singleton data. It is triggered by a timer in
// Tab1Fragment.
//
//====================================================================

package edu.wsu.tabs;

// Import packages
import java.util.TimerTask;

//----------------------------------------------------------------
// class Task
//----------------------------------------------------------------
public class TimerTask1 extends TimerTask {
    //----------------------------------------------------------------
    // run: updating data in Shared
    //----------------------------------------------------------------
    @Override
    public void run() {
        Shared.DATA.timer += 1;
        Tab1Fragment.timerTaskHandler.sendEmptyMessage(0);
    }
}
