package com.example.donationappv1.database;


import android.content.Context;

import androidx.room.Room;

public class DatabaseManager {

    static DonationDatabase db;

    private static void BuildDBInstance (Context context) {
        db = Room.databaseBuilder(context,DonationDatabase.class,"donation_db").build();
    }
    public static DonationDatabase getDBInstance(Context context){
        if (db == null){
            BuildDBInstance(context);
        }
        return db;
    }
}