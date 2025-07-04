
class Main {

    public static void main(String[] args) {
        //// Test Case 1: Normal checkout with a variety of products

        System.out.println("Test Case 1: Normal checkout with a variety of products");
        Customer customer = new Customer("Salma", 1000);


        Cheese cheese = new Cheese("Cheese", 100, 5, 200);
        Biscuits biscuits = new Biscuits("Biscuits", 150, 5,  700);
        ScratchCard card = new ScratchCard("Card", 30, 5);
        Product tv = new Product("TV", 50, 5, false, true);
        //// Price: $50, Qty: 5, Non-perishable, Shippable, Weight: 5kg

        Cart cart = new Cart();
        cart.addProduct(cheese, 2);
        cart.addProduct(tv, 3);
        cart.addProduct(card, 1);
        cart.addProduct(biscuits, 1);

        try {
            cart.checkout(customer);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("--------------------");

        //// Test Case 2: Attempting checkout with an empty cart

        System.out.println("Test Case 2: Attempting checkout with an empty cart");
        Cart emptyCart = new Cart();
        try {
            emptyCart.checkout(customer);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("--------------------");

        //// Test Case 3: Checkout with insufficient customer balance

        System.out.println("Test Case 3: Checkout with insufficient customer balance");
        Customer poorCustomer = new Customer("Ali", 100);
        Cart cart2 = new Cart();
        cart2.addProduct(cheese, 2);
        try {
            cart2.checkout(poorCustomer);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("--------------------");

        //// Test Case 4: Checkout with an expired product

        System.out.println("Test Case 4: Checkout with an expired product");
        Cheese expiredCheese = new Cheese("Expired Cheese", 100, 5,  200); // Expired in 2020
        Cart cart3 = new Cart();
        cart3.addProduct(expiredCheese, 1);
        try {
            cart3.checkout(customer);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("--------------------");
    }
}