//====================================================================
//
// Application: Tabbed Timers
// Class:       Shared
// Description:
//   This class stores the data shared between the tab fragments and
//   their respective timer task classes.
//
//====================================================================

package edu.wsu.tabs;

//====================================================================
// enum Shared
//====================================================================
public enum Shared {
    // Enum value declarations
    DATA;
    int timer = 0, countdown = 60, initialCountdown = 60;
}
