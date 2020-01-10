package com.celusion.fragmodel;

import android.os.Bundle;

import com.celusion.fragmodel.databinding.ActivityMainBinding;
import com.celusion.fragmodel.fragment.LogFragment;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogFragment logFragment = new LogFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentFrame,logFragment,LogFragment.FRAG).commit();


    }
}
