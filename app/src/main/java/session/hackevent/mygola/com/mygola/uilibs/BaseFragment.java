package session.hackevent.mygola.com.mygola.uilibs;

import android.support.v4.app.Fragment;

/**
 * Created by nitinraj.arvind on 7/25/2015.
 */
public abstract class BaseFragment extends Fragment {



    /**Will have to be implemented by every fragment*/
    public abstract String getTitle();

    public abstract void setToolbarNavigationState();



}
