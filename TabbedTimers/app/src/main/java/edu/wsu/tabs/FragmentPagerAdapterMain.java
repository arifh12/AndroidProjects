//====================================================================
//
// Application: Tabbed Timers
// Activity:    ActMain
// Description:
//   This Android class is the tab adapter.
//
//====================================================================
package edu.wsu.tabs;

// Import packages
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


//--------------------------------------------------------------------
// class FragmentPagerAdapterMain
//--------------------------------------------------------------------
class FragmentPagerAdapterMain extends FragmentPagerAdapter
{

    //----------------------------------------------------------------
    // Constants and variables
    //----------------------------------------------------------------
    private Context context;
    private int tabCount;

    //----------------------------------------------------------------
    // FragmentPagerAdapterMain Constructor
    //----------------------------------------------------------------
    public FragmentPagerAdapterMain(
        Context c, FragmentManager fm, int tabCount)
    {
        super(fm);
        context = c;
        this.tabCount = tabCount;
    }

    //----------------------------------------------------------------
    // getItem
    //----------------------------------------------------------------
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                Tab1Fragment tab1Fragment = new Tab1Fragment();
                return tab1Fragment;
            case 1:
                Tab2Fragment tab2Fragment = new Tab2Fragment();
                return tab2Fragment;
            default:
                return null;
        }

    }

    //----------------------------------------------------------------
    // getCount
    //----------------------------------------------------------------
    @Override
    public int getCount()
    {
        return tabCount;
    }

}