
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    // Adds a product to the cart with a specified quantity
    public void addProduct(Product product, int quantity) {
        if (quantity <= 0 || quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Invalid quantity or not enough stock");
        }

        // Check if the product is already in the cart; if so, update its quantity
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                if (item.getUnits() + quantity > product.getQuantity()) {
                    throw new IllegalArgumentException("Not enough stock available");
                }
                item.addUnits(quantity);
                return;
            }
        }
        // If not found, add a new cart item
        items.add(new CartItem(product, quantity));
    }

    // Checks if the cart has no items
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Calculates the subtotal of all items in the cart
    public double subTotal() {
        return items.stream().mapToDouble(CartItem::totalPrice).sum();
    }

    // Determines the shipping fee: $30 if there are shippable items, $0 otherwise
    public double shippingFee() {
        return getShippingItems().isEmpty() ? 0 : 30;
    }

    // Calculates the total amount due, including shipping
    public double totalDue() {
        return subTotal() + shippingFee();
    }

    // Retrieves all shippable items in the cart for shipping purposes
    public List<Shippable> getShippingItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            Product p = item.getProduct();
            if (p.isShippable() && p instanceof Shippable) {
                for (int i = 0; i < item.getUnits(); i++) {
                    shippableItems.add((Shippable) p);
                }
            }
        }
        return shippableItems;
    }

    // Processes the checkout: validates, charges the customer, updates stock, and ships
    public void checkout(Customer customer) {
        if (isEmpty()) {
            throw new RuntimeException("Cannot checkout an empty cart");
        }

        // Verify stock availability for all items
        for (CartItem item : items) {
            Product p = item.getProduct();
            if (p.getQuantity() < item.getUnits()) {
                throw new RuntimeException("Not enough stock for " + p.getName());
            }
        }

        // Calculate total and charge the customer
        double total = totalDue();
        if (!customer.withdraw(total)) {
            throw new RuntimeException("Customer balance insufficient");
        }

        // Update stock quantities
        for (CartItem item : items) {
            item.getProduct().reduceQuantity(item.getUnits());
        }

        // Finalize checkout: print receipt and ship items
        printReceipt(customer, total);
        ShippingService.ship(getShippingItems());
        items.clear();  // Empty the cart after successful checkout
    }

    // Prints a formatted receipt for the customer
    private void printReceipt(Customer customer, double total) {
        System.out.println("\n** Checkout Receipt **");
        for (CartItem item : items) {
            System.out.printf("%d x %-15s $%.2f\n",
                    item.getUnits(),
                    item.getProduct().getName(),
                    item.totalPrice());
        }
        System.out.println("---------------------");
        System.out.printf("Subtotal:     $%.2f\n", subTotal());
        System.out.printf("Shipping:     $%.2f\n", shippingFee());
        System.out.printf("Total Paid:   $%.2f\n", total);
        System.out.printf("Remaining Balance: $%.2f\n\n", customer.getBalance());
    }
}

