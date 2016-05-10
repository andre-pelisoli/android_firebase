package br.com.pelisoli.android_firebase.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.pelisoli.android_firebase.view.fragment.MealFragment;
import br.com.pelisoli.android_firebase.view.fragment.ShoppingListFragment;

/**
 * Created by pelisoli on 27/04/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numTabs;

    public ViewPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new ShoppingListFragment();
            case 1:
                return new MealFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
