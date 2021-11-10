package com.example.donationappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.donationappv1.Model.Donation;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Donation> listFromMA;
    ListView listOfDonations;
    TextView total_text;
    TextView number_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        total_text =  findViewById(R.id.total_text);
        number_text = findViewById(R.id.num_of_donation);
        listOfDonations = findViewById(R.id.list_of_donations);

        listFromMA = getIntent().getParcelableArrayListExtra("listOfDonations");
        Double t = getIntent().getExtras().getDouble("total");
        total_text.setText("The total amout: " + t);
        number_text.setText("The number of donations is " + listFromMA.size());

       DonationBaseAdapter listAdapter = new DonationBaseAdapter(listFromMA,this);



       listOfDonations.setAdapter(listAdapter);
    }
}