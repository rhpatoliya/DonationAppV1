package com.example.donationappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.donationappv1.Model.Donation;
import com.example.donationappv1.Model.DonationManager;
import com.example.donationappv1.database.DatabaseManager;
import com.example.donationappv1.database.DonationDatabase;

public class MainActivity extends AppCompatActivity  {
  static DonationManager donationManager = new DonationManager();
    Donation donationObject;

Button donate_btu; // same as IBoutlet
EditText amout_text;
RadioButton payPal_btn;
RadioButton credit_card_btn;
    AlertDialog.Builder builder;
    DatabaseManager dbManager;
    DonationDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("Donation App","onCreate");
        donate_btu = (Button) findViewById(R.id.donate_btu);
        amout_text = (EditText) findViewById(R.id.amout_text);
        payPal_btn = (RadioButton) findViewById(R.id.paypal_radio_btu);
        credit_card_btn = (RadioButton) findViewById(R.id.credit_card_radio_btu);
        donationObject = new Donation();
        builder = new AlertDialog.Builder(this);

        db = DatabaseManager.getDBInstance(this);
        dbManager = ((myAPP)getApplication()).getDatabaseManager();


        // first option of creating click listner
        payPal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Donation App","PayPal Button clicked");
                donationObject.setPaymentMethod(1);
            }
        });

        credit_card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Donation App","Credit Card Button clicked");
                donationObject.setPaymentMethod(2);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Donation App","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Donation App","onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Donation App","onPause");
        // Another activity is taking focus
        // (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d("Donation App","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d("Donation App","onDestroy");

    }

// second option which is create a public function
    public void donate_now_clicked(View view) {
        Log.d("Donation App","Donate Button clicked");
        if (checkInputs()){
            donationObject.setDonatinAmout(Double.parseDouble(amout_text.getText().toString()));
            donationManager.addNewDonation(donationObject);

            // insert donation object to DB is not correct here
            // is should run in backgound thread
           // db.getDonationDAO().insertNewDonation(donationObject);
            dbManager.insertNewDonation(donationObject);

            showAnAlert();
            donationObject = new Donation();
        }else {
            Toast.makeText(this,"Please enter all values ", Toast.LENGTH_LONG).show();
        }
        clearUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.donation_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId()){
             case R.id.report_menu_item: {
                 openReportActivity();
                 break;
             }
             case R.id.exit:{
                 break;
             }
             case R.id.list_activity:{
                 openListActivity();
                 break;
             }
         }
         return true;
    }

    private void openListActivity(){
        Intent toListActivity = new Intent(this,ListActivity.class);

        toListActivity.putParcelableArrayListExtra("listOfDonations",donationManager.getListOfDonations());
        toListActivity.putExtra("total",DonationManager.getTotal(donationManager.getListOfDonations()));
        startActivity(toListActivity);
    }

    private void openReportActivity(){

        ///Intent 1- deteirmine the class (Activity)
        // Intent 2- carry data

        Donation d= donationManager.getListOfDonations().get(donationManager.getListOfDonations().size() - 1);
        Intent report_intent = new Intent(this,ReportActivity.class);
        report_intent.putExtra("mynewObject",d);

        // report_intent.putExtra("report_msg",thanksMsg);
        report_intent.putExtra("numberOfDonations",donationManager.getListOfDonations().size());
        startActivity(report_intent);
    }
    private void showAnAlert(){
        builder.create();

        String payment = (donationObject.getPaymentMethod() == 1) ?  "PayPal" : "Credit Card";

        builder.setMessage("Your Donation is " + donationObject.getDonatinAmout() +
                "$ which completed using " + payment );
        builder.setTitle(R.string.thankyou_mes);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Donation App","in dialog ok button");
            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Donation App","in dialog cancel button");
            }
        });
        builder.show();
    }

    Boolean checkInputs(){
        if (!amout_text.getText().toString().isEmpty()
                &&
                (Integer.parseInt(amout_text.getText().toString()) > 0)
                &&
                ((payPal_btn.isChecked() || (credit_card_btn.isChecked()))))
        {
            return  true;
        }
        return  false;
    }

    void clearUI(){
        amout_text.setText("");
        payPal_btn.setChecked(false);
        credit_card_btn.setChecked(false);
    }

}