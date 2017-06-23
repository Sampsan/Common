package com.sag.library.adapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sag.library.presenter.BaseFragment;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class FragmentPagerBindAdapter extends FragmentPagerAdapter {

    private BaseFragment[] mFragments;

    public FragmentPagerBindAdapter(FragmentManager fm, BaseFragment[] fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.length : 0;
    }

}
