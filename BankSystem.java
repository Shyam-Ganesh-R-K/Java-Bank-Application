import java.util.ArrayList;
import java.util.Scanner;

// Base class for accounts
class Account {
    int cust_id;
    double bal;

    public Account() {
        this.cust_id = 0;
        this.bal = 0;
    }
}

class SBAccount extends Account {
    public SBAccount(int cust_id, double bal) {
        super();
        this.bal = bal;
        this.cust_id = cust_id;
        System.out.println("\nNew Savings Bank Account created Successfully!");
        System.out.println("Customer ID     : " + cust_id);
        System.out.printf("Initial Balance : Rs. %.2f\n", bal);
    }

    public void deposit(double dep_amt) {
        if (dep_amt > 0) {
            System.out.printf("\nDeposit of Rs. %.2f\n", dep_amt);
            System.out.printf("   Before Deposit : Rs. %.2f\n", this.bal);
            this.bal += dep_amt;
            System.out.printf("   After Deposit  : Rs. %.2f\n", this.bal);
        } else {
            System.out.println("Invalid Amount!!!");
        }
    }

    public void withdraw(double with_amt) {
        if (this.bal > with_amt + 1000) {
            System.out.printf("\nWithdrawal of Rs. %.2f\n", with_amt);
            System.out.printf("   Before Withdrawal : Rs. %.2f\n", this.bal);
            this.bal -= with_amt;
            System.out.printf("   After Withdrawal  : Rs. %.2f\n", this.bal);
        } else {
            System.out.println("Insufficient Balance!!!");
        }
    }

    public void calc_interest() {
        System.out.printf("\nCalculating Interest on Balance: Rs. %.2f\n", this.bal);
        double interest = 0.04 * this.bal / 12;
        System.out.println("   Interest Rate: 4% annually");
        System.out.printf("   Monthly Interest: Rs. %.2f\n", interest);
        this.bal += interest;
        System.out.printf("   New Balance: Rs. %.2f\n", this.bal);
    }
}

class FDAccount extends Account {
    int period;

    public FDAccount(int period, int cust_id, double bal) {
        this.period = period;
        this.cust_id = cust_id;
        this.bal = bal;
        super.cust_id = cust_id;
        super.bal = bal;
        System.out.println("\nFixed Deposit Account Created!");
        System.out.println("Customer ID   : " + cust_id);
        System.out.printf("Deposit Amount: Rs. %.2f\n", bal);
        System.out.println("Period        : " + period + " months");
    }

    public double calc_interest() {
        return 0.0825 * this.bal * this.period / 12;
    }

    public void close() {
        System.out.println("\nClosing Fixed Deposit");
        System.out.printf("   Balance Before Interest: Rs. %.2f\n", this.bal);
        double interest = calc_interest();
        System.out.printf("   Interest Earned        : Rs. %.2f\n", interest);
        this.bal += interest;
        System.out.printf("   Balance After Interest : Rs. %.2f\n", this.bal);
    }
}

class Customer {
    int cust_id;
    String name;
    String address;
    double bal;
    int period;
    SBAccount sb;
    FDAccount fd;

    public Customer(int cust_id, String name, String address) {
        this.cust_id = cust_id;
        this.name = name;
        this.address = address;
        this.bal = 0;
        this.period = 0;
        this.sb = null;
        this.fd = null;
    }

    public void createAccount(int val, Scanner sc) {
        if (val == 1) {
            System.out.print("Enter Initial Balance: Rs. ");
            this.bal = sc.nextDouble();
            this.sb = new SBAccount(this.cust_id, this.bal);
        } else if (val == 2) {
            System.out.print("Enter Initial Balance: Rs. ");
            this.bal = sc.nextDouble();
            System.out.print("Enter Period (months): ");
            this.period = sc.nextInt();
            this.fd = new FDAccount(this.period, this.cust_id, this.bal);
        } else {
            System.out.println("Invalid choice");
        }
    }

    public void transaction(int val, Scanner sc) {
        if (val == 1) {
            System.out.print("Enter Amount to Deposit: Rs. ");
            double amt = sc.nextDouble();
            sb.deposit(amt);
        } else if (val == 2) {
            System.out.print("Enter Amount to Withdraw: Rs. ");
            double amt = sc.nextDouble();
            sb.withdraw(amt);
        } else if (val == 3) {
            sb.calc_interest();
        } else if (val == 4) {
            fd.close();
            System.out.println("Fixed Deposit Account Closed Successfully!");
        } else {
            System.out.println("Invalid choice");
        }
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> c = new ArrayList<>();
        int j = 0;
        int cust_id = 1000000;

        System.out.println("\n============================================================");
        System.out.printf("%30s%n", "Welcome to ABC Bank");
        System.out.println("============================================================");

        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. Savings Account");
            System.out.println("2. Fixed Deposit Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int ch1 = sc.nextInt();

            if (ch1 == 1) {
                int ch2 = 0;
                while (ch2 != 5) {
                    System.out.println("\nSavings Account Menu");
                    System.out.println("1. Open New SB Account");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Calculate Interest");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    ch2 = sc.nextInt();

                    if (ch2 == 1) {
                        sc.nextLine(); // consume newline
                        System.out.print("Enter your name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter your address: ");
                        String address = sc.nextLine();
                        c.add(new Customer(cust_id + j, name, address));
                        c.get(j).createAccount(1, sc);
                        j++;
                    } else if (ch2 == 2) {
                        System.out.print("Enter Customer ID: ");
                        int cus_id = sc.nextInt();
                        c.get(cus_id - cust_id).transaction(1, sc);
                    } else if (ch2 == 3) {
                        System.out.print("Enter Customer ID: ");
                        int cus_id = sc.nextInt();
                        c.get(cus_id - cust_id).transaction(2, sc);
                    } else if (ch2 == 4) {
                        System.out.print("Enter Customer ID: ");
                        int cus_id = sc.nextInt();
                        c.get(cus_id - cust_id).transaction(3, sc);
                    } else if (ch2 == 5) {
                        System.out.println("Returning to Main Menu...");
                    } else {
                        System.out.println("Invalid choice");
                    }
                }
            } else if (ch1 == 2) {
                int ch3 = 0;
                while (ch3 != 3) {
                    System.out.println("\nFixed Deposit Menu");
                    System.out.println("1. Open New FD Account");
                    System.out.println("2. Close FD Account");
                    System.out.println("3. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    ch3 = sc.nextInt();

                    if (ch3 == 1) {
                        sc.nextLine(); // consume newline
                        System.out.print("Enter your name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter your address: ");
                        String address = sc.nextLine();
                        c.add(new Customer(cust_id + j, name, address));
                        c.get(j).createAccount(2, sc);
                        j++;
                    } else if (ch3 == 2) {
                        System.out.print("Enter Customer ID: ");
                        int cus_id = sc.nextInt();
                        c.get(cus_id - cust_id).transaction(4, sc);
                    } else if (ch3 == 3) {
                        System.out.println("Returning to Main Menu...");
                    } else {
                        System.out.println("Invalid choice");
                    }
                }
            } else if (ch1 == 3) {
                System.out.println("\nThank you for banking with us! Have a great day!");
                System.out.println("============================================================");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}
