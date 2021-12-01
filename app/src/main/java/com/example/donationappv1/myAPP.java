package com.example.donationappv1;

import android.app.Application;

import com.example.donationappv1.database.DatabaseManager;

public class myAPP extends Application {

    private DatabaseManager databaseManager = new DatabaseManager();

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
