package com.example.donationappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    DonationManager donationManager;
// View controller
    // MVC = C
    // MVVM = V + C = Model - View - View Model
    // View Model
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Donation App","onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Donation App","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Donation App","onResume");
        donationManager = new DonationManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Donation App","onPause");
        // Another activity is taking focus
        // (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d("Donation App","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d("Donation App","onDestroy");

    }
}