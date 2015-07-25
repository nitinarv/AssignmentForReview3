package session.hackevent.mygola.com.mygola.view.mainscreen.recommended;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
    SwipeRefreshLayout mSwipeRefreshLayout;



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
        trySetupSwipeRefresh(view);
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

    private void trySetupSwipeRefresh(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(
                    R.color.refresh_progress_1,
                    R.color.refresh_progress_2,
                    R.color.refresh_progress_3,
                    R.color.refresh_progress_4,
                    R.color.refresh_progress_5,
                    R.color.refresh_progress_6,
                    R.color.refresh_progress_7,
                    R.color.refresh_progress_8,
                    R.color.refresh_progress_9,
                    R.color.refresh_progress_10,
                    R.color.refresh_progress_11,
                    R.color.refresh_progress_12,
                    R.color.refresh_progress_13);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mSwipeRefreshLayout.setRefreshing(true);

                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSwipeRefreshLayout.setRefreshing(false);

                        }
                    },10000);


                    requestDataRefresh();
                }
            });

            int progressBarStartMargin = getResources().getDimensionPixelSize(
                    R.dimen.swipe_refresh_progress_bar_start_margin);
            int progressBarEndMargin =getResources().getDimensionPixelSize(
                    R.dimen.swipe_refresh_progress_bar_start_margin);
            mSwipeRefreshLayout.setProgressViewOffset(true, progressBarStartMargin, progressBarEndMargin);
        }
    }

    public void requestDataRefresh(){
       new AsyncTask<Void,Void,Void>(){
           @Override
           protected void onPreExecute() {
               super.onPreExecute();
               mSwipeRefreshLayout.setRefreshing(true);

           }

           @Override
           protected Void doInBackground(Void... params) {
               try{
                   Thread.sleep(10000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               return null;
           }

           @Override
           protected void onPostExecute(Void aVoid) {
               super.onPostExecute(aVoid);
               mSwipeRefreshLayout.setRefreshing(false);
           }
       }.execute();
    }
}
