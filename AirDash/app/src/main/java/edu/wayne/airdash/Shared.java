//====================================================================
//
// Application: Air Dash
// Class:       Shared
// Description:
//   This class stores the data shared between ActMain and Task.
//
//====================================================================

package edu.wayne.airdash;

//====================================================================
// enum Shared
//====================================================================
public enum Shared {
    // Enum value definition
    DATA;

    // Enum field declarations
    public int clock, ordersDlv, ordersQd, dronesCrashed,
            orderRate, dronesFlying, dlvTime = 0;
    public int dronesAvl = 100;
    public final int DELAY_SECONDS = 1;
    public final int PERIOD_SECONDS = 1;
    public final String SHARED_PREFS = "MY_PREFS";
}
