package com.example.donationappv1;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.donationappv1.Model.Donation;

import java.util.List;

@Dao
public interface DonationDao {

    @Insert
    void insert(Donation donation);

    @Delete
    void delete(Donation donation);

    @Query("Select * from Donation")
    List<Donation> getAllDonations();

    @Query("Select * from Donation where donatinAmout > :amount")
    List<Donation> getDonationByAmout(Double amount);

    @Update
    void updateDonation(Donation updatedDonation);


}
