package session.hackevent.mygola.com.mygola.view.mainscreen.eatout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import session.hackevent.mygola.com.mygola.R;
import session.hackevent.mygola.com.mygola.uilibs.BaseFragment;
import session.hackevent.mygola.com.mygola.uilibs.FragmentGeneralCallback;

public class EatoutFrag extends BaseFragment {

	public static String TAG = "session.hackevent.mygola.com.mygola.view.mainscreen.eatout.EatoutFrag";
	public static String TITLE=  "Eat Out";
    FragmentGeneralCallback fragmentGeneralCallback;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentGeneralCallback = (FragmentGeneralCallback) activity;
        setRetainInstance(true);
    }


    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab2.xml
		View view = inflater.inflate(R.layout.fragmenttab2, container, false);
		return view;
	}


    @Override
    public void onResume() {
        super.onResume();
        fragmentGeneralCallback.onResumeSetTitle(getTitle());
        fragmentGeneralCallback.onFragmentActive();
    }


    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void setToolbarNavigationState() {

    }

}
