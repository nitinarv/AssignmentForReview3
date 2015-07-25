package session.hackevent.mygola.com.mygola.view.mainscreen.recommended;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import session.hackevent.mygola.com.mygola.R;
import session.hackevent.mygola.com.mygola.uilibs.BaseFragment;
import session.hackevent.mygola.com.mygola.uilibs.FragmentGeneralCallback;

public class RecommendedFrag extends BaseFragment {

	public static String TAG = "session.hackevent.mygola.com.mygola.view.mainscreen.recommended";
	public static String TITLE=  "Recommended";
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
		// Get the view from fragmenttab1.xml
		View view = inflater.inflate(R.layout.fragmenttab1, container, false);
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
