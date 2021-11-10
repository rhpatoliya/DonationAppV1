package com.example.donationappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {
TextView msg_text;
TextView num_text;
    int num_of_donations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


      //  String msg = getIntent().getExtras().getString("report_msg");

        if (getIntent().hasExtra("numberOfDonations")){
            num_of_donations = getIntent().getExtras().getInt("numberOfDonations");
        }


        msg_text = findViewById(R.id.report_text);
        num_text = findViewById(R.id.num_of_donations_text);
       // getIntent().getParcelableExtra("mynewObject");
        Donation myNewDonation = getIntent().getExtras().getParcelable("mynewObject");
        msg_text.setText("The last Donation was with "+ String.valueOf(myNewDonation.getDonatinAmout())+" CAN");
        num_text.setText("Number Of Donations \n" +String.valueOf(num_of_donations));

    }
}