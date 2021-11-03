package com.example.donationappv1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    DonationManager donationManager;
    Donation donationObject;
// View controller
    // MVC = C
    // MVVM = V + C = Model - View - View Model
    // View Model

// create reference of XML views into my activity == Java Objects
    //
Button donate_btu; // same as IBoutlet
EditText amout_text;
RadioButton payPal_btn;
RadioButton credit_card_btn;
    AlertDialog.Builder builder;
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
        donationManager = new DonationManager();
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
            builder.create();

            String payment = (donationObject.paymentMethod == 1) ?  "PayPal" : "Credit Card";
//            if (donationObject.paymentMethod == 2) // payPay
//            {
//                payment = "Credit Card";
//            }

            builder.setMessage("Your Donation is " + donationObject.donatinAmout +
                    "$ which completed using " + payment );
            builder.setTitle(R.string.thankyou_mes);
           // builder.setCancelable(true);
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

            donationObject = new Donation();

        }else {
            Toast.makeText(this,"Please enter all values ", Toast.LENGTH_LONG).show();

        }
        clearUI();


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