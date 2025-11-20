package caje.bank;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accNum;
    private String pin;
    private double balance;

    public Account(String accNum, String pin, double balance) {
        this.accNum = accNum;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccNum() {
        return accNum;
    }
    public String getPin() {
        return pin;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract String showInfo();
}
