package com.example.donationappv1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.donationappv1.Model.Donation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FireStoreManager {

    interface firebaseCallBack{
        void firebaseAddingDocumentListener();
        void firebaseFailAddingDocumentListener();
        void firebaseGettingDocumentsListener(ArrayList<Donation> list);
    }

    firebaseCallBack listener;
    ExecutorService databaseExecuter = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public  void addNewDonationToFireStore(Donation newDonation){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> data = new HashMap<>();
                data.put("amount", newDonation.donatinAmout);
                data.put("method", newDonation.paymentMethod);
                data.put("date",new Date());

                db.collection("DonationCollection")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("firebase", "DocumentSnapshot written with ID: " + documentReference.getId());
                                db_handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.firebaseAddingDocumentListener();
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("firebase", "Error adding document", e);
                                db_handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.firebaseFailAddingDocumentListener();
                                    }
                                });
                            }
                        });


            }
        });

        // Add a new document with a generated id.

    }



    public void getAllDonations(){
        ArrayList<Donation> listFromFireStore = new ArrayList<>(0);
        db.collection("DonationCollection")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("firebase", document.getId() + " => " + document.getData());
                                Number amount = (Number) document.get("amount");
                                Number method = (Number) document.get("method");

                                Donation d = new Donation(amount.doubleValue(), method.intValue());
                                listFromFireStore.add(d);
                            }
                            listener.firebaseGettingDocumentsListener(listFromFireStore);
                        } else {
                            Log.d("firebase", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
