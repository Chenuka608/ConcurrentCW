import java.util.LinkedList;
import java.util.Queue;

// Shared Resource: Order Queue
class OrderQueue {
    private final int capacity;
    private final Queue<String> orders;
    private int orderNumber;

    public OrderQueue(int capacity) {
        this.capacity = capacity;
        this.orders = new LinkedList<>();
        this.orderNumber = 1;
    }

    // Method to place an order
    public synchronized void placeOrder(String order) throws InterruptedException {
        while (orders.size() == capacity) {
            System.out.println("Queue is full. Please wait...");
            wait();
        }
        orders.add("Order " + orderNumber + ": " + order);
        System.out.println("Order " + orderNumber + " placed for " + order);
        orderNumber++;
        notifyAll(); // Notify baristas that a new order is available
    }

    // Method to take an order
    public synchronized String takeOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        String order = orders.poll();
        notifyAll(); // Notify customers that space is available in the queue
        return order;
    }
}
