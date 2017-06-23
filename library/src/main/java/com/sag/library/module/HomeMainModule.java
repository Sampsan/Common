package com.sag.library.module;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * 应用主Activity导航模块
 * <p>
 * Created by SAG on 2017/6/15
 */

public class HomeMainModule implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private int index;
    private ViewPager mPager;
    private ViewGroup mNavigation;

    public void setNavigation(ViewPager pager, ViewGroup group) {
        mPager = pager;
        mPager.setOnPageChangeListener(this);

        mNavigation = group;
        final int total = group.getChildCount();
        for (int i = 0; i < total; i++) {
            group.getChildAt(i).setOnClickListener(this);
        }
        select(group.getChildAt(index), true);

        mPager.setOffscreenPageLimit(mPager.getAdapter().getCount());
    }

    @Override
    public void onClick(View view) {
        final ViewGroup parent = (ViewGroup) view.getParent();
        final int previous = mPager.getCurrentItem();
        final int index = parent.indexOfChild(view);
        if (previous != index) {
            mPager.setCurrentItem(index, false);
        }
    }

    private void select(View view, boolean isSelected) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            final int total = group.getChildCount();
            for (int i = 0; i < total; i++) {
                group.getChildAt(i).setSelected(isSelected);
            }
        } else {
            view.setSelected(isSelected);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        select(mNavigation.getChildAt(index), false);
        select(mNavigation.getChildAt(index = position), true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
