// Main class
public class CoffeeShop {
    public static void main(String[] args) {
        final int QUEUE_CAPACITY = 1;
        OrderQueue orderQueue = new OrderQueue(QUEUE_CAPACITY);

        // Create baristas
        Thread barista1 = new Thread(new Barista(orderQueue), "Barista-1");
        Thread barista2 = new Thread(new Barista(orderQueue), "Barista-2");

        // Start baristas
        barista1.start();
        barista2.start();

        // Predefined customers with their orders
        String[][] customerOrders = {
                {"Customer-1", "Latte"},
                {"Customer-2", "Espresso"},
                {"Customer-3", "Cappuccino"},
                {"Customer-4", "Mocha"},
                {"Customer-5", "Americano"},
                {"Customer-6", "Latte"},
                {"Customer-7", "Espresso"},
                {"Customer-8", "Cappuccino"},
                {"Customer-9", "Mocha"},
                {"Customer-10", "Americano"}
        };

        // Create customers
        for (String[] customerOrder : customerOrders) {
            String customerName = customerOrder[0];
            String coffeeType = customerOrder[1];
            Thread customer = new Thread(new Customer(orderQueue, coffeeType), customerName);
            customer.start();

            try {
                Thread.sleep(500); // Simulate time between customer arrivals
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
