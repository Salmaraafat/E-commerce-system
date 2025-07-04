
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShippingService {
    //// Ships a list of items and prints a shipment summary
    public static void ship(List<Shippable> items) {
        if (items.isEmpty()) {
            return;  /// Nothing to ship
        }

        System.out.println("** Shipment Notice **");

        //// Count items by name
        Map<String, Long> itemCounts = items.stream()
                .collect(Collectors.groupingBy(Shippable::getName, Collectors.counting()));

        //// Calculate total weight by name
        Map<String, Double> itemWeights = items.stream()
                .collect(Collectors.groupingBy(Shippable::getName, Collectors.summingDouble(Shippable::getWeight)));

        //// Print shipment details
        for (String name : itemCounts.keySet()) {

            long count = itemCounts.get(name);
            double totalWeight = itemWeights.get(name);
            System.out.printf("%d x %-15s %.0fg\n", count, name, totalWeight);

        }

        //// Calculate and display total package weight in kilograms
        double totalWeight = itemWeights.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.printf("Total Package Weight: %.1fkg\n\n", totalWeight / 1000.0);
    }
}
