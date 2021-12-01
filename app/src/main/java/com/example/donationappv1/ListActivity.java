package com.example.donationappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.donationappv1.Model.Donation;
import com.example.donationappv1.Model.DonationManager;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity
        implements DonationDataBaseClient.DatabaseActionListener{

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
        number_text.setText("The number of donations is " + listFromMA.size());
        DonationDataBaseClient.listener = this;
        DonationDataBaseClient.getAllDonations();
    }

    @Override
    public void databaseReturnWithList(List<Donation> donationList) {

        ArrayList<Donation> alDonation = new  ArrayList<Donation>(donationList);
        DonationBaseAdapter listAdapter = new DonationBaseAdapter(alDonation,this);
        listOfDonations.setAdapter(listAdapter);
        total_text.setText("The total amout: " + DonationManager.getTotal(donationList));
        number_text.setText("The number of donations is " + donationList.size());

    }
}