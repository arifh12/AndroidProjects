//====================================================================
//
// Application: Tabbed Timers
// Activity:    ActMain
// Course:      CSC 4330
// Homework:    05
// Author:      Arif Hasan
// Date:        04/28/2021
// Description:
//   This application uses the tabbed layout to allow the user to
// navigate between a timer or a countdown timer. The user can
// start, stop, or reset on either of these tabs at any time. Also, a
// label is shown when the countdown timer reaches zero.
//
//====================================================================


package edu.wsu.tabs;

// Import packages
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

//--------------------------------------------------------------------
// class ActMain
//--------------------------------------------------------------------
public class ActMain extends AppCompatActivity
{

    //----------------------------------------------------------------
    // Constants and variables
    //----------------------------------------------------------------
    public static final String APP_NAME = "Tabbed Timers";
    public static final String APP_VERSION = "1.0";
    private TabLayout tlMain;
    private ViewPager vpMain;

    //----------------------------------------------------------------
    // onCreate
    //----------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laymain);

        // Connect to controls
        tlMain = findViewById(R.id.tlMain);
        vpMain = findViewById(R.id.vpMain);

        // Create tabs
        tlMain.addTab(tlMain.newTab().setText("Timer"));
        tlMain.addTab(tlMain.newTab().setText("Countdown"));

        // Create adapter
        final FragmentPagerAdapterMain fpaMain =
            new FragmentPagerAdapterMain(
                this,
                getSupportFragmentManager(),
                tlMain.getTabCount());
        vpMain.setAdapter(fpaMain);

        // Define listeners
        tlMain.addOnTabSelectedListener(
            new TabLayout.OnTabSelectedListener()
            {
                @Override
                public void onTabSelected(TabLayout.Tab tab)
                {
                    vpMain.setCurrentItem(tab.getPosition());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {}
                @Override
                public void onTabReselected(TabLayout.Tab tab) {}
            });
        vpMain.addOnPageChangeListener(
            new TabLayout.TabLayoutOnPageChangeListener(tlMain));

    }
}
