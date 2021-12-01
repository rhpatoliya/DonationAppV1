package com.example.donationappv1.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.donationappv1.Model.Donation;

@Database(version = 1,entities = {Donation.class})
public abstract class DonationDatabase extends RoomDatabase{

    abstract public DonationDAO getDonationDAO();

}
