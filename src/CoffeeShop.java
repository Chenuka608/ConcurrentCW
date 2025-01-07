import java.util.Scanner;

public class CoffeeShop {
    private static boolean isTrue = true;

    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue(5);  // Max 5 orders in the queue
        Scanner input = new Scanner(System.in);

        // Create barista threads
        Barista barista1 = new Barista(orderQueue);
        Barista barista2 = new Barista(orderQueue);

        // Start barista threads
        barista1.start();
        barista2.start();

        System.out.println("-----------------------------------------------");
        System.out.println("Welcome to the Coffee Shop! ☕️");

        while (isTrue) {
            // Display menu
            System.out.println("Choose your order :");
            System.out.println("1. Latte");
            System.out.println("2. Espresso");
            System.out.println("3. Cappuccino");
            System.out.println("4. Mocha");
            System.out.println("5. Americano");
            System.out.println("6. See Current Orders");
            System.out.println("7. See Past Orders");
            System.out.println("0. Exit");
            System.out.println("-------------------------------------------------\n");

            // After displaying the menu, check if there are any orders
            if (orderQueue.getOrders().isEmpty()) {
                System.out.println("No orders in the queue. Waiting for orders...\n");
            }

            // Prompt for user choice
            System.out.print("Enter your choice  : \n");

            // Validate input
            int choice;
            if (input.hasNextInt()) {
                choice = input.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number between 0 and 7.");
                input.next();  // Clear the invalid input
                continue;
            }

            // Handle the user's menu choice
            switch (choice) {
                case 1:
                    new Customer(orderQueue, "Latte").start();
                    break;
                case 2:
                    new Customer(orderQueue, "Espresso").start();
                    break;
                case 3:
                    new Customer(orderQueue, "Cappuccino").start();
                    break;
                case 4:
                    new Customer(orderQueue, "Mocha").start();
                    break;
                case 5:
                    new Customer(orderQueue, "Americano").start();
                    break;
                case 6:
                    seeOrders(orderQueue); // See current orders
                    break;
                case 7:
                    seePastOrders(orderQueue); // See past orders
                    break;
                case 0:
                    System.out.println("Exiting the Coffee Shop. Thank you!");
                    isTrue = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 0 and 7.");
            }
        }

        input.close();
    }

    private static void seeOrders(OrderQueue orderQueue) {
        System.out.println("Current Orders in Queue: " + orderQueue.getOrders());
    }

    private static void seePastOrders(OrderQueue orderQueue) {
        System.out.println("Past Orders: " + orderQueue.getPastOrders());
    }
}
