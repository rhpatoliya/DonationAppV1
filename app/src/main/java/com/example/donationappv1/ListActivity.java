package com.example.donationappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.donationappv1.Model.Donation;
import com.example.donationappv1.Model.DonationManager;
import com.example.donationappv1.database.DatabaseManager;
import com.example.donationappv1.DonationListAdapter;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity
        implements  DatabaseManager.DatabaseListener
{

    ArrayList<Donation> listFromDB;
    ListView listOfDonations;
    TextView total_text;
    TextView number_text;
    DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        total_text =  findViewById(R.id.total_text);
        number_text = findViewById(R.id.num_of_donation);
        listOfDonations = findViewById(R.id.list_of_donations);
        dbManager = ((myAPP)getApplication()).getDatabaseManager();
        dbManager.listener = this;
        //listFromMA = getIntent().getParcelableArrayListExtra("listOfDonations");
        dbManager.getAllDonations();

    }


    @Override
    public void databaseAllDonationListener(List<Donation> list) {
        listFromDB = new ArrayList<>(list);
        DonationListAdapter adapter = new DonationListAdapter(listFromDB,this);
        listOfDonations.setAdapter(adapter);
        number_text.setText("The number of donations is " + listFromDB.size());

    }
}