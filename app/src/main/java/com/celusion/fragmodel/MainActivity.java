package com.celusion.fragmodel;

import android.os.Bundle;

import com.celusion.fragmodel.fragment.Login;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login loginFragment = new Login();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentFrame,loginFragment,Login.TAG).commit();

    }
}
