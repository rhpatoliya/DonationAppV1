package com.example.donationappv1;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import com.example.donationappv1.Model.Donation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DonationDataBaseClient {

   static DonationDataBase dbClient;
   static Context db_context;

    public interface DatabaseActionListener {
        public void databaseReturnWithList(List<Donation> donationList);
    }

   static   DatabaseActionListener listener;

    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    static Handler  handler = new Handler(Looper.getMainLooper());
 // Thread
    DonationDataBaseClient(Context context){
        db_context = context;
        dbClient = Room.databaseBuilder(context,
                DonationDataBase.class, "database-donations").build();
    }

    public static DonationDataBase getDbClient(){
        if (dbClient == null){
            dbClient = new DonationDataBaseClient(db_context).dbClient;
        }
        return dbClient;
    }


    public static void insertNewDonation(Donation newDonation){
        // backgound thread
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbClient.getDonationDao().insert(newDonation);
            }
        });
    }

    public static void getAllDonations(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Donation> listFromDB = dbClient.getDonationDao().getAllDonations();
                // run some code in main thread
                // return from background thread
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // return something to main thread
                        listener.databaseReturnWithList(listFromDB);
                    }
                });
            }
        });
    }
}
