package com.project.khopt.fitassistant.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.project.khopt.fitassistant.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private Fragment mInitialFragment;
    private FragmentManager mFragmentManager;
    //private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.welcome, R.string.register );
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNavigationView = findViewById(R.id.navigation);
        setupNavigationMenu(mNavigationView);

        mFragmentManager = getSupportFragmentManager();
//        mInitialFragment = new ExerciseFragment();
//        FragmentTransaction mFragmentTransaction  = mFragmentManager.beginTransaction();
//        mFragmentTransaction.add(R.id.fragment_container, mInitialFragment);
//        mFragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationMenu(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                setupNavigationItem(item);
                return true;
            }
        });
    }

    private void setupNavigationItem(MenuItem item){

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = null ;
        switch (item.getItemId()){
            case R.id.profile_item:
               //  fragment =  new ProfileFragment();
                Toast.makeText(this, "Profile", Toast.LENGTH_LONG).show();
                break;
            case R.id.blog_item:
                fragment = new BlogFragment();
                Toast.makeText(this, "Blog", Toast.LENGTH_LONG).show();
                break;
            case R.id.nutrition_item:
                fragment = new NutritionFragment();
                break;
            case R.id.exercises_item:
                fragment = new ExerciseFragment();
                break;

        }

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
        mDrawerLayout.closeDrawers();
    }
}

