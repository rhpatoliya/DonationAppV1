package com.example.donationappv1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.donationappv1.Model.Donation;


@Database(entities = {Donation.class},version = 1)
public abstract class DonationDataBase extends RoomDatabase {
    public abstract DonationDao getDonationDao();
}
