public class Cheese extends Product implements Shippable {
    private final double weight;


    public Cheese(String name, double price, int quantity, double weight) {

        super(name, price, quantity, true, true);
        this.weight = weight;

    }

    @Override public String getName() {
        return super.getName();
    }

    @Override public double getWeight() {
        return weight;
    }

}

