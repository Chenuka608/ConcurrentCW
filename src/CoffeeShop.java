import java.util.Scanner;


public class CoffeeShop {

    private static boolean isTrue=true;

    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue(5); // Max 5 orders in the queue
        Scanner input = new Scanner(System.in);

        // Create barista threads
        Barista barista1 = new Barista(orderQueue);
        Barista barista2 = new Barista(orderQueue);

        // Start barista threads
        barista1.start();
        barista2.start();

        System.out.println("Welcome to the Coffee Shop!");
        System.out.println("Choose your order:");
        System.out.println("1. Latte");
        System.out.println("2. Espresso");
        System.out.println("3. Cappuccino");
        System.out.println("4. Mocha");
        System.out.println("5. Americano");
        System.out.println("0. Exit");

        while (isTrue) {
            System.out.println("Enter your choice :");

        }

    }
}
