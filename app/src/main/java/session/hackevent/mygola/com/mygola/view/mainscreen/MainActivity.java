package session.hackevent.mygola.com.mygola.view.mainscreen;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import session.hackevent.mygola.com.mygola.R;
import session.hackevent.mygola.com.mygola.uilibs.BaseActivity;
import session.hackevent.mygola.com.mygola.uilibs.FragmentGeneralCallback;


public class MainActivity extends BaseActivity implements FragmentDrawer.FragmentDrawerListener, FragmentGeneralCallback {


    private MenuItem mSearchAction;
    private boolean mSearchOpened;
    private SearchManager searchManager;
    private SearchView searchView;
    private SearchableInfo searchableInfo;
    private ExampleAdapter searchableAdapter;
    private MenuItem searchItem;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private FragmentDrawer mFragmentDrawer;
    private ActionBarDrawerToggle mActionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentDrawer = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mFragmentDrawer.setUp(R.id.fragment_navigation_drawer, getDrawerLayout(), getActionBarToolbar());
        mFragmentDrawer.setDrawerListener(this);

        setTitle("Pick one...");


        // Locate the viewpager in activity_main.xml
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // Set the ViewPagerAdapter into ViewPager
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final Menu fMenu = menu;
        setupSearchView(menu);

        return true;
    }


    @Override
    protected void loadData(String query) {
        super.loadData(query);

        // Cursor
//        String[] columns = new String[] { "_id", "text" };
//        Object[] temp = new Object[] { 0, "default" };
//
//        MatrixCursor cursor = new MatrixCursor(columns);
//        resultList = new ArrayList<String>();
//        if(wordList!=null && !wordList.isEmpty()) {
//            for (int i = 0; i < wordList.size(); i++) {
//
//                if(wordList.get(i).toLowerCase().contains(query.toLowerCase())) {
//                    temp[0] = i;
//                    temp[1] = wordList.get(i);//replaced s with i as s not used anywhere.
//                    resultList.add(wordList.get(i));
//                    cursor.addRow(temp);
//                }
//            }
//            searchableAdapter = new ExampleAdapter(this, cursor, resultList);
//            searchView.setSuggestionsAdapter(searchableAdapter);
//        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(item.getItemId()){
            case R.id.action_search:

                return true;
            case android.R.id.home:
                toggleDrawer();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
//        displayView(position);
    }

    @Override
    public void onResumeSetTitle(String title) {

    }

    @Override
    public void onFragmentActive() {

    }

    @Override
    public void onItemSelected() {

    }
}
