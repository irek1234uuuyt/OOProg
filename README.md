# CaJer eBank – Simple JavaFX Banking System

## Author: Casalla & Jerxel

CaJer eBank is a simple JavaFX-based banking application created for our Object-Oriented Programming project. It allows users to create an account, log in, view their balance, and transfer money to other users. The project demonstrates the use of classes, inheritance, file handling, and basic GUI development in Java.

## Features

* **User Registration**:  Create a new bank account with name, pin, starting balance, and account number
* **Login System**: Sign in using account number and PIN
* **Balance Display**: Shows available balance immediately after login
* **Money Transfer**: Send funds to another existing account
* **Search for Books**: Uses a **Linear Search algorithm** to find a specific book by its title.
* **Input Validation**
    * Prevents sending money to yourself
    * Shows Insufficient Funds
    * Rejects negative or non-numeric amounts
    * Detects Invalid Account Number
* **File Saving**: Accounts are stored and loaded using serialization
* **Simple and Clean GUI**: Built using JavaFX VBox/HBox layout components

## How to Run the Project

1. **Requirements**:
    * Java JDK 8 or higher
    * JavaFX SDK (if not using IntelliJ IDEA)

2. **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/cajer-ebank.git
    ```

3. **Open the project folder**:
    ```bash
    cd cajer-ebank
    ```

4. **Compile the Java Files**:
    ```bash
    javac --module-path "PATH_TO_FX/lib" --add-modules javafx.controls -d out src/caje/bank/*.java
    ```

5. **Run the Application**:
    ```bash
    java --module-path "PATH_TO_FX/lib" --add-modules javafx.controls -cp out caje.bank.CaJerBankFileApp
    ```