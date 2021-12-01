package com.example.donationappv1.database;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import com.example.donationappv1.Model.Donation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {

    static DonationDatabase db;
     ExecutorService databaseExecuter = Executors.newFixedThreadPool(4);
     Handler db_handler = new Handler(Looper.getMainLooper());

    public interface DatabaseListener {
        void databaseAllDonationListener(List<Donation> list);
    }

     public DatabaseListener listener;


    private static void BuildDBInstance (Context context) {
        db = Room.databaseBuilder(context,DonationDatabase.class,"donation_db").build();
    }
    public static DonationDatabase getDBInstance(Context context){
        if (db == null){
            BuildDBInstance(context);
        }
        return db;
    }

    public void insertNewDonation(Donation d){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                db.getDonationDAO().insertNewDonation(d);
            }
        });
    }

    public void getAllDonations(){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                List<Donation> list =  db.getDonationDAO().getAll();
                db_handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseAllDonationListener(list);
                    }
                });

            }
        });

    }







}