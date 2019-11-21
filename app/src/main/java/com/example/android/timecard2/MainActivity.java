package com.example.android.timecard2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setUpViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setUpViewPager (ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Patricia");
        adapter.addFragment(new Tab2Fragment(), "Nicole");
        adapter.addFragment(new Tab3Fragment(), "Ashlie");
        adapter.addFragment(new Tab4Fragment(), "Krista");
        adapter.addFragment(new Tab5Fragment(), "Rebecca");
        adapter.addFragment(new Tab6Fragment(), "Amber");
        adapter.addFragment(new Tab6Fragment(), "Aditya");
        adapter.addFragment(new Tab6Fragment(), "Bhavna");
        viewPager.setAdapter(adapter);

    }
}