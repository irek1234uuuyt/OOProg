package caje.bank;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class CaJerBankFileApp extends Application {

    private BankManager manager = new BankManager();
    private UserAccount currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        manager.loadAccounts();

        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new Insets(20));

        TextField accField = new TextField();
        accField.setPromptText("Account Number");

        PasswordField pinField = new PasswordField();
        pinField.setPromptText("PIN");

        Button loginBtn = new Button("Login");
        Button signUpBtn = new Button("Sign Up");
        Label msg = new Label();

        loginBtn.setOnAction(e -> {
            String acc = accField.getText().trim();
            String pin = pinField.getText().trim();
            UserAccount u = manager.login(acc, pin);
            if (u != null) {
                currentUser = u;
                stage.setScene(new Scene(mainMenu(stage), 400, 150));
                stage.setTitle("CaJer eBank");
            } else {
                msg.setText("Wrong acc or PIN\n");
            }
        });

        signUpBtn.setOnAction(e -> openSignUpForm(stage));

        loginLayout.getChildren().addAll(
                new Label("CaJer eBank Login"),
                accField,
                pinField,
                loginBtn,
                signUpBtn,
                msg
        );

        Scene scene = new Scene(loginLayout, 400, 210);
        stage.setScene(scene);
        stage.setTitle("CaJer eBank");
        stage.show();
    }

    private VBox mainMenu(Stage stage) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(20));

        Label welcomeLabel = new Label("Welcome, " + currentUser.getName());
        Label balanceLabel = new Label("Balance: ₱" + currentUser.getBalance());

        Button transferBtn = new Button("Transfer Funds");
        Button logoutBtn = new Button("Logout");

        transferBtn.setOnAction(e -> transfer(stage));
        logoutBtn.setOnAction(e -> {
            manager.saveAccounts();
            currentUser = null;
            start(stage);
        });

        box.getChildren().addAll(welcomeLabel, balanceLabel, transferBtn, logoutBtn);
        return box;
    }

    private void transfer(Stage stage) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(20));

        TextField recipientAccField = new TextField();
        recipientAccField.setPromptText("Recipient Acc#");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        Button sendBtn = new Button("Send");
        Button backBtn = new Button("Back");

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(sendBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        Label msg = new Label();

        sendBtn.setOnAction(e -> {
            String rAcc = recipientAccField.getText().trim();
            double money;
            try {
                money = Double.parseDouble(amountField.getText().trim());
            } catch (Exception ex) {
                msg.setText("Invalid Input\n");
                return;
            }

            UserAccount recipient = null;
            for (UserAccount u : manager.getUsers()) {
                if (u.getAccNum().equals(rAcc)) {
                    recipient = u;
                    break;
                }
            }

            if (recipient == null) {
                msg.setText("Recipient not found\n");
                return;
            }

            if (money <= 0 || currentUser.getBalance() < money) {
                msg.setText("Not enough money\n");
                return;
            }

            currentUser.setBalance(currentUser.getBalance() - money);
            recipient.setBalance(recipient.getBalance() + money);
            manager.saveAccounts();
            msg.setText("Sent!\n");
        });

        backBtn.setOnAction(e -> {
            stage.setScene(new Scene(mainMenu(stage), 400, 150));
            stage.setTitle("CaJer eBank");
        });

        box.getChildren().addAll(
                new Label("Transfer Funds"),
                recipientAccField,
                amountField,
                msg,
                buttonBox
        );

        Scene scene = new Scene(box, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Transfer Funds");
    }

    private void openSignUpForm(Stage stage) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(20));

        TextField accField = new TextField();
        accField.setPromptText("Account Number");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        PasswordField pinField = new PasswordField();
        pinField.setPromptText("PIN");
        TextField balanceField = new TextField();
        balanceField.setPromptText("Initial Balance");

        Button createBtn = new Button("Create Account");
        Button backBtn = new Button("Back");
        Label msg = new Label();

        createBtn.setOnAction(e -> {
            String accNum = accField.getText().trim();
            String pin = pinField.getText().trim();
            String name = nameField.getText().trim();
            double balance;

            try {
                balance = Double.parseDouble(balanceField.getText().trim());
            } catch (Exception ex) {
                msg.setText("Invalid input\n");
                return;
            }

            for (UserAccount u : manager.getUsers()) {
                if (u.getAccNum().equals(accNum)) {
                    msg.setText("Account already exists!\n");
                    return;
                }
            }

            UserAccount newUser = new UserAccount(accNum, pin, balance, name);
            manager.addUser(newUser);

            start(stage); // Return to login
        });

        backBtn.setOnAction(e -> start(stage));

        box.getChildren().addAll(
                new Label("Sign Up"),
                accField,
                nameField,
                pinField,
                balanceField,
                createBtn,
                backBtn,
                msg
        );

        Scene scene = new Scene(box, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
    }
}
