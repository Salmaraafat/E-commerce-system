
public class Customer {
    private final String name;      //// Customer's name
    private double balance;         //// Customer's current balance

    //// Constructor
    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    //// Getter methods for customer details
    public String getName() { return name; }
    public double getBalance() { return balance; }

    //// Withdraws money from the balance, returns true if successful
    public boolean withdraw(double amount) {
        if (balance < amount) {
            return false;  //// Not enough balance
        }
        balance -= amount;
        return true;
    }
}
