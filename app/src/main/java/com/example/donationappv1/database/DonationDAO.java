package com.example.donationappv1.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.donationappv1.Model.Donation;

import java.util.List;

// insert / update / delete/ quere

@Dao
public interface DonationDAO {
    @Insert
    void insertNewDonation(Donation donation);

    @Delete
    void deleteDonation(Donation todelete);

//    @Update
//    void updateDonation(Donation toUpdate);

    @Query("SELECT * FROM Donation")
    List<Donation> getAll();


    @Query("SELECT * FROM Donation WHERE donatinAmout >= :amout ")
    List<Donation> getAllDonationsBiggerThan(Double amout);

//
//    @Query("SELECT * FROM Donation WHERE paymentMethod = :method")
//    List<Donation> getAllDonationsWithPaymentMethod(int method);


}
