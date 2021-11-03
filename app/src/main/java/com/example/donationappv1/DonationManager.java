package com.example.donationappv1;

import java.util.ArrayList;

public class DonationManager { // App Model

    // collection of Donations
    ArrayList<Donation> listOfDonations = new ArrayList<>(0);

    public ArrayList<Donation> getListOfDonations() {
        return listOfDonations;
    }

    public void addNewDonation(Donation d){
        listOfDonations.add(d);
    }
    
}
