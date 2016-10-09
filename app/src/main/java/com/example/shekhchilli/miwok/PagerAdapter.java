package com.example.shekhchilli.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by shekh chilli on 9/21/2016.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_NUM  = 4;

    public PagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:{
                return new NumbersFragment();
            }
            case 1:{
                return new ColorsFragment();
            }
            case 2:{
                return new FamilyFragment();
            }
            case 3:{
                return new PhrasesFragment();
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch(position){
            case 0:{
                return "Numbers";
            }
            case 1:{
                return "Colors";
            }
            case 2:{
                return "Family Members";
            }
            case 3:{
                return "Phrases";
            }
            default:{
                return null;
            }
        }
}
}
