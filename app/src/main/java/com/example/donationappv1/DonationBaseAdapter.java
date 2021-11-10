package com.example.donationappv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.donationappv1.Model.Donation;

import java.util.ArrayList;

public class DonationBaseAdapter extends BaseAdapter {
    ArrayList<Donation> listOfDonations;
    Context list_activity_Context;

    DonationBaseAdapter(ArrayList<Donation> list, Context activity_context){
        listOfDonations = list;
        list_activity_Context = activity_context;
    }

    @Override
    public int getCount() {
        return listOfDonations.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // in each row I have one donation item form the array list;
    // 10 donations
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(list_activity_Context).inflate(R.layout.donation_item,null);
        TextView amount = view.findViewById(R.id.donation_amout);
        TextView method = view.findViewById(R.id.payment_method);
        amount.setText(listOfDonations.get(i).getDonatinAmout()+"");
        if (listOfDonations.get(i).getPaymentMethod() == 1) // Credit Card
        {
            method.setText("Credit Card");
        }
        else method.setText("PayPal");
        return view;
    }
}
