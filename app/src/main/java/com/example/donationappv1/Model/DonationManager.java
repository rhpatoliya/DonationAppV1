package com.example.donationappv1.Model;

import com.example.donationappv1.DonationDataBase;
import com.example.donationappv1.DonationDataBaseClient;

import java.util.ArrayList;
import java.util.List;

public class DonationManager { // App Model

    //DonationDataBaseClient dataBaseClient = DonationDataBaseClient.;
    // collection of Donations
    ArrayList<Donation> listOfDonations = new ArrayList<>(0);
    Double total = 0.0;

    public ArrayList<Donation> getListOfDonations() {

       //DonationDataBaseClient.ge
        return listOfDonations;
    }

    public void addNewDonation(Donation d){
        listOfDonations.add(d);// database locally // webservice  // cloud database
        DonationDataBaseClient.insertNewDonation(d);
    }

    public static Double getTotal(List<Donation> list){
        double total = 0.0;

        for (int i = 0 ; i < list.size(); i++)
        {
            total += list.get(i).donatinAmout;
        }
        return total;
    }
}
