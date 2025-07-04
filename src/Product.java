
public class Product {
    private final String name;
    private final double price;
    private int quantity;
    private final boolean perishable;
    private final boolean shippable;


    // Constructor
    public Product(String name, double price, int quantity, boolean perishable, boolean shippable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.perishable = perishable;
        this.shippable = shippable;
    }

    // Getter methods
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isPerishable() { return perishable; }
    public boolean isShippable() { return shippable; }

    // Reduces the stock quantity by a specified amount, with validation
    public void reduceQuantity(int amount) {

        if (amount > quantity) {

            throw new IllegalArgumentException("Cannot reduce quantity below zero");

        }
        quantity -= amount;
    }
}
