import java.util.Scanner;

// Bank Account Class
class BankAccount {

    private double balance;

    // Constructor
    BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Deposit Method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit Successful!");
        } else {
            System.out.println("Invalid Deposit Amount!");
        }
    }

    // Withdraw Method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid Amount!");
        } 
        else if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } 
        else {
            balance -= amount;
            System.out.println("Withdrawal Successful!");
        }
    }

    // Check Balance
    public double checkBalance() {
        return balance;
    }
}

// ATM Class
class ATM {

    private BankAccount account;

    ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: ₹" + account.checkBalance());
                    break;

                case 2:
                    System.out.print("Enter Deposit Amount: ₹");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter Withdrawal Amount: ₹");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Thank you for using ATM!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);
    }
}

// Main Class
public class ATMInterface {

    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(10000); // Initial Balance
        ATM atm = new ATM(userAccount);

        atm.showMenu();
    }
}
