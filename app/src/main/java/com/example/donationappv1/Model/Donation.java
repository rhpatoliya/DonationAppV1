package com.example.donationappv1.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Donation implements Parcelable { // App Model

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Double donatinAmout;
    public int paymentMethod; // 1 for payPal and 2 for credit card


    public Donation(){
        this.donatinAmout = -1.0;
        this.paymentMethod = 0;
    }

    public Donation(Double donatinAmout, int paymentMethod) {
        this.donatinAmout = donatinAmout;
        this.paymentMethod = paymentMethod;
    }

    public Double getDonatinAmout() {
        return donatinAmout;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setDonatinAmout(Double donatinAmout) {
        this.donatinAmout = donatinAmout;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    protected Donation(Parcel in) {
        if (in.readByte() == 0) {
            donatinAmout = null;
        } else {
            donatinAmout = in.readDouble();
        }
        paymentMethod = in.readInt();
    }

    public static final Creator<Donation> CREATOR = new Creator<Donation>() {
        @Override
        public Donation createFromParcel(Parcel in) {
            return new Donation(in);
        }

        @Override
        public Donation[] newArray(int size) {
            return new Donation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (donatinAmout == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(donatinAmout);
        }
        parcel.writeInt(paymentMethod);
    }
}
