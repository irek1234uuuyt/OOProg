package caje.bank;

import java.io.*;
import java.util.*;

public class BankManager {
    private List<UserAccount> users = new ArrayList<>();
    private final String file = "../data/accounts.dat";

    public void loadAccounts() {
        File f = new File(file);
        if (!f.exists()) return;
        try (ObjectInputStream o = new ObjectInputStream(new FileInputStream(f))) {
            users = (List<UserAccount>) o.readObject();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void saveAccounts() {
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file))) {
            o.writeObject(users);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<UserAccount> getUsers() {
        return users;
    }

    public void addUser(UserAccount u) {
        users.add(u);
        saveAccounts();
    }

    public UserAccount login(String accNum, String pin) {
        for (int i = 0; i < users.size(); i++) {
            UserAccount u = users.get(i);
            if (u.getAccNum().equals(accNum) && u.getPin().equals(pin))
                return u;
        }
        return null;
    }

    public UserAccount findUser(String accNum) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getAccNum().equals(accNum))
                return users.get(i);
        }
        return null;
    }
}
