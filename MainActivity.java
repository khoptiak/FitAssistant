package com.project.khopt.fitassistant;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.project.khopt.fitassistant.adapters.TabAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity {

private TabAdapter mAdapter;
private TabLayout mTabLayout;
private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mAdapter = new TabAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new SportTabFragment(), "Sport");
        mAdapter.addFragment(new TabFragment(), "Title 2");
        mAdapter.addFragment(new TabFragment(), "Title 3");

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.baseline_local_dining_black_24).getPosition();
    }


}
