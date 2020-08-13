package com.project.khopt.fitassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.project.khopt.fitassistant.adapters.TabAdapter;


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
        mAdapter.addFragment(new TabFragment(), "Title 1");
        mAdapter.addFragment(new TabFragment(), "Title 2");
        mAdapter.addFragment(new TabFragment(), "Title 3");

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
