package caje.bank;

import java.io.Serializable;

public class UserAccount extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public UserAccount(String accNum, String pin, double balance, String name) {
        super(accNum, pin, balance);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String showInfo() {
        return "Acc#: " + getAccNum() + " | Name: " + name + " | Balance: ₱" + getBalance();
    }
}
