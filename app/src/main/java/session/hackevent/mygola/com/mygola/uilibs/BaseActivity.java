package session.hackevent.mygola.com.mygola.uilibs;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import session.hackevent.mygola.com.mygola.view.mainscreen.ExampleAdapter;
import session.hackevent.mygola.com.mygola.view.mainscreen.FragmentDrawer;
import session.hackevent.mygola.com.mygola.R;

/**
 * Created by nitinraj.arvind on 7/25/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {


    // Primary toolbar and drawer toggle
    private Toolbar mActionBarToolbar;
    private DrawerLayout mDrawerLayout;
    private FragmentDrawer mFragmentDrawer;
    private ActionBarDrawerToggle mActionBarDrawerToggle;


    private int mThemedStatusBarColor;
    private int mNormalStatusBarColor;
    private int mProgressBarTopWhenActionBarShown;

    private MenuItem mSearchAction;
    private boolean mSearchOpened;
    private SearchManager searchManager;
    private SearchView searchView;
    private SearchableInfo searchableInfo;
    private ExampleAdapter searchableAdapter;
    private MenuItem searchItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mThemedStatusBarColor = getResources().getColor(R.color.colorPrimaryDark);
        mNormalStatusBarColor = mThemedStatusBarColor;
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
                clearBaseTitle(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }

    private void clearBaseTitle(final Toolbar toolbar){
        setBaseTitle(toolbar, getResources().getString(R.string.empty_string));
    }

    private void setBaseTitle(final Toolbar toolbar, final String title){
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(title);
            }
        });
    }

    /**Pick from label in activity tag*/
    protected void setTitle(Context context, Class gClass){
        setTitle(context, gClass, null);
    }

    /**Pick title given from a fragment*/
    protected void setTitle(String title){
        setTitle(null, null, title);
    }

    protected int backStackCount(){
        List<Fragment> allFrags = getSupportFragmentManager().getFragments();
        int fragIndex = (allFrags==null)?(0):(getSupportFragmentManager().getBackStackEntryCount());
        return fragIndex;
    }


    /**
     * We have to include the toolbar and then we have to
     * set the title usign the pcakage manager, for being used by fragments
     * */
    protected void setTitle(Context context, Class gClass, String mTitle){
        if(getActionBarToolbar()!=null){
            String value = "-NA-";
            if(mTitle==null) {
                if(context!=null && gClass!=null) {
                    PackageManager pm = getPackageManager();
                    ComponentName componentName = new ComponentName(context, gClass);

                    try {
                        if (pm.getActivityInfo(componentName, 0).labelRes != 0) {
                            value = context.getResources().getString(pm.getActivityInfo(componentName, 0).labelRes);
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                value = mTitle;
            }

            setBaseTitle(getActionBarToolbar(), value);
        }
    }



    protected DrawerLayout getDrawerLayout(){
        return (DrawerLayout) findViewById(R.id.drawerlayout);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setupNavDrawer();
    }

    /**
     * Sets up the navigation drawer as appropriate. Note that the nav drawer will be
     * different depending on whether the attendee indicated that they are attending the
     * event on-site vs. attending remotely.
     */
    private void setupNavDrawer() {
        mDrawerLayout = getDrawerLayout();
        if (mDrawerLayout == null) {
            return;
        }
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        if (mActionBarToolbar != null) {
            mActionBarToolbar.setNavigationIcon(R.drawable.ic_drawer_two_dark);
            mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            });
        }

        /*mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }
        });*/


        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, getDrawerLayout(), getActionBarToolbar(), R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        };


        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.LEFT);

    }


    protected void setupSearchView(Menu menu){
        searchItem = menu.findItem(R.id.action_search);
        searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }

        searchableInfo = searchManager.getSearchableInfo(this.getComponentName());
        if (searchView != null) {
            searchView.setSearchableInfo(searchableInfo);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                loadData(s);
                return true;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int i) {

                return false;
            }

            @Override
            public boolean onSuggestionClick(int i) {
//                if(resultList!=null && wordMap!=null)
//                    Toast.makeText(MainActivity.this, "Count for [" + resultList.get(i) + "] is (" + wordMap.get(resultList.get(i).toString()) + ")", Toast.LENGTH_LONG).show();

                searchView.setIconified(true);
                searchItem.collapseActionView();
                return false;
            }
        });
    }


    protected void setDrawerToBackButton(){
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                getActionBarToolbar().setNavigationIcon(R.drawable.ic_keyboard_back_dark);
            }
        });
    }

    protected void setDrawerToMenuButton(){
        mDrawerLayout.post(new Runnable() {
                @Override
                public void run() {
                    mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
//                    getActionBarToolbar().setNavigationIcon(R.drawable.ic_keyboard_back_dark);
                }
        });
    }


    protected void loadData(String query){

    }

    protected void toggleDrawer(){
        if(mDrawerLayout!=null) {
            if (!mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            } else {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        }
    }
}
