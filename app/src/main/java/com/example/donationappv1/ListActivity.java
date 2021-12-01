package com.example.donationappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.donationappv1.Model.Donation;
import com.example.donationappv1.Model.DonationManager;
import com.example.donationappv1.database.DatabaseManager;
import com.example.donationappv1.DonationListAdapter;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity
        implements  DatabaseManager.DatabaseListener {

    ArrayList<Donation> listFromDB;
    ListView listOfDonations;
    TextView total_text;
    TextView number_text;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        total_text = findViewById(R.id.total_text);
        number_text = findViewById(R.id.num_of_donation);
        listOfDonations = findViewById(R.id.list_of_donations);
        dbManager = ((myAPP) getApplication()).getDatabaseManager();
        dbManager.listener = this;
        //listFromMA = getIntent().getParcelableArrayListExtra("listOfDonations");
        dbManager.getAllDonations();

    }


    @Override
    public void databaseAllDonationListener(List<Donation> list) {
        listFromDB = new ArrayList<>(list);
        DonationListAdapter adapter = new DonationListAdapter(listFromDB, this);
        listOfDonations.setAdapter(adapter);
        number_text.setText("The number of donations is " + listFromDB.size());

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {// toronto
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Search for cities");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);//
               // if ((Double.parseDouble(query)))
                dbManager.getAllDonationsBiggerThan(Double.parseDouble(query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }



}