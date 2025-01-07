import java.util.LinkedList;
import java.util.List;

public class OrderQueue {
    private final int capacity;
    private final LinkedList<String> ordersQueue;
    private final List<String> pastOrders; // LinkedList to store past orders
    private boolean waitingForOrder = false; // Flag to control printing the waiting message

    public OrderQueue(int capacity) {
        this.capacity = capacity;
        this.ordersQueue = new LinkedList<>();
        this.pastOrders = new LinkedList<>();
    }

    // Synchronize access to the queue
    public synchronized void addOrder(String order) {
        try {
            while (ordersQueue.size() >= capacity) {
                System.out.println("Queue is full. Please wait...");
                wait(); // Wait if the queue is full
            }
            ordersQueue.add(order); // Add order to the queue
            pastOrders.add(order); // Add order to past orders
            System.out.println("Order has been added: " + order);
            notifyAll(); // Notify all threads that are waiting
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted flag
        }
    }

    // Synchronize access to the queue
    public synchronized String takeOrder() {
        try {
            while (ordersQueue.isEmpty()) {
                if (!waitingForOrder) { // Print the message only once

                    waitingForOrder = true; // Set the flag to true to prevent repeating
                }
                wait(); // Wait if there are no orders in the queue
            }
            waitingForOrder = false; // Reset flag when there's an order to take
            String order = ordersQueue.poll(); // Get the next order from the queue
            notifyAll(); // Notify all threads that are waiting
            return order;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted flag
            return null;
        }
    }

    // Get a copy of the current orders in the queue
    public synchronized List<String> getOrders() {
        return new LinkedList<>(ordersQueue);
    }

    // Get a copy of the past orders
    public synchronized List<String> getPastOrders() {
        return new LinkedList<>(pastOrders);
    }
}
