package com.example.donationappv1.Model;

import java.util.ArrayList;

public class DonationManager { // App Model

    // collection of Donations
    ArrayList<Donation> listOfDonations = new ArrayList<>(0);
    Double total = 0.0;

    public ArrayList<Donation> getListOfDonations() {
        return listOfDonations;
    }

    public void addNewDonation(Donation d){
        listOfDonations.add(d);// database locally // webservice  // cloud database
    }

    public Double getTotal(){
        for (int i = 0 ; i < listOfDonations.size(); i++)
        {
            total += listOfDonations.get(i).donatinAmout;
        }
        return total;
    }
}
