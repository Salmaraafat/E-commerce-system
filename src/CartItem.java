//// item in the shopping cart, linking a product to a quantity
public class CartItem {
    private final Product product;  /// The product being purchased
    private int units;              /// Number of units of this product

    /// Constructor to initialize a cart item
    public CartItem(Product product, int units) {
        this.product = product;
        this.units = units;
    }

    // Getter methods
    public Product getProduct() { return product; }
    public int getUnits() { return units; }

    /// Increases the number of units for this item
    public void addUnits(int extra) {
        units += extra;
    }

    /// Calculates the total cost of this item (price per unit * units)
    public double totalPrice() {
        return product.getPrice() * units;
    }
}
