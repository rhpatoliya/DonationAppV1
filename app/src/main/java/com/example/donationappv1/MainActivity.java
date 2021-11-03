package com.example.donationappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity  {
    DonationManager donationManager;
// View controller
    // MVC = C
    // MVVM = V + C = Model - View - View Model
    // View Model

// create reference of XML views into my activity == Java Objects
    //
Button donate_btu; // same as IBoutlet
EditText amout_text;
RadioButton payPal_btn;
RadioButton credit_card_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Donation App","onCreate");
        donate_btu = (Button) findViewById(R.id.donate_btu);
        amout_text = (EditText) findViewById(R.id.amout_text);
        payPal_btn = (RadioButton) findViewById(R.id.paypal_radio_btu);
        credit_card_btn = (RadioButton) findViewById(R.id.credit_card_radio_btu);

        // first option of creating click listner
        payPal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Donation App","PayPal Button clicked");

            }
        });

        credit_card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Donation App","Credit Card Button clicked");

            }
        });
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

// second option which is create a public function
    public void donate_now_clicked(View view) {
        Log.d("Donation App","Donate Button clicked");

    }
}