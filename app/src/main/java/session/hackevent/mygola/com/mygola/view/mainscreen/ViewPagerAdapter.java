package session.hackevent.mygola.com.mygola.view.mainscreen;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import session.hackevent.mygola.com.mygola.view.mainscreen.artnculture.ArtsnCultureFrag;
import session.hackevent.mygola.com.mygola.view.mainscreen.eatout.EatoutFrag;
import session.hackevent.mygola.com.mygola.view.mainscreen.recommended.RecommendedFrag;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	final int PAGE_COUNT = 3;
	// Tab Titles
	private String tabtitles[] = new String[] { RecommendedFrag.TITLE, EatoutFrag.TITLE, ArtsnCultureFrag.TITLE};
	Context context;

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {

			// Open RecommendedFrag.java
		case 0:
			RecommendedFrag fragmenttab1 = new RecommendedFrag();
			return fragmenttab1;

			// Open EatoutFrag.java
		case 1:
			EatoutFrag fragmenttab2 = new EatoutFrag();
			return fragmenttab2;

			// Open ArtsnCultureFrag.java
		case 2:
			ArtsnCultureFrag fragmenttab3 = new ArtsnCultureFrag();
			return fragmenttab3;
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabtitles[position];
	}
}
