package com.example.donationappv1;

public class Donation { // App Model

    Double donatinAmout;
    int paymentMethod; // 1 for payPal and 2 for credit card

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
}
