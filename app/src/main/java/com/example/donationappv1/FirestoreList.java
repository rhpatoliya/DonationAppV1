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
import com.example.donationappv1.database.DatabaseManager;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FirestoreList extends AppCompatActivity implements FireStoreManager.firebaseCallBack {
    ArrayList<Donation> listFromFirestore;
    ListView listOfDonations;
    TextView total_text;
    TextView number_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore_list);
        total_text = findViewById(R.id.total_text);
        number_text = findViewById(R.id.num_of_donation);
        listOfDonations = findViewById(R.id.list_of_donations);

        FireStoreManager fireStoreManager = new FireStoreManager();
        fireStoreManager.listener = this;


        fireStoreManager.getAllDonations();


//        listFromDB = new ArrayList<>(list);
//        DonationListAdapter adapter = new DonationListAdapter(listFromDB, this);
//        listOfDonations.setAdapter(adapter);
//        number_text.setText("The number of donations is " + listFromDB.size());



    }

    @Override
    public void firebaseAddingDocumentListener() {

    }

    @Override
    public void firebaseFailAddingDocumentListener() {

    }

    @Override
    public void firebaseGettingDocumentsListener(ArrayList<Donation> list) {
      DonationListAdapter adapter = new DonationListAdapter(list, this);
        listOfDonations.setAdapter(adapter);
        number_text.setText("The number of donations is " + list.size());


    }


//
//
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.list_menu, menu);
//
//        MenuItem searchViewMenuItem = menu.findItem(R.id.search);
//
//        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
//        String searchFor = searchView.getQuery().toString();
//        if (!searchFor.isEmpty()) {// toronto
//            searchView.setIconified(false);
//            searchView.setQuery(searchFor, false);
//        }
//
//        searchView.setQueryHint("Search for cities");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d("query", query);//
//                // if ((Double.parseDouble(query)))
//                dbManager.getAllDonationsBiggerThan(Double.parseDouble(query));
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        return true;
//    }

}