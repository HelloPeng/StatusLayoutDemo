package com.lvzp.statuslayoutdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        BlankFragment blankFragment1 = new BlankFragment();
        BlankFragment blankFragment2 = new BlankFragment();
        BlankFragment blankFragment3 = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("argument", 1);
        blankFragment1.setArguments(bundle);
        mFragmentList.add(blankFragment1);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("argument", 2);
        blankFragment2.setArguments(bundle2);
        mFragmentList.add(blankFragment2);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("argument", 3);
        blankFragment3.setArguments(bundle3);
        mFragmentList.add(blankFragment3);
        mTitleList.add("页面1");
        mTitleList.add("页面2");
        mTitleList.add("页面3");
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private FragmentPagerAdapter mFragmentAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    };

}
