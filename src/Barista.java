public class Barista extends Thread {
    private final OrderQueue orderQueue;

    public Barista(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        try {
            while (true) { // Continuously process orders
                String order = orderQueue.takeOrder(); // Take order from the queue
                System.out.println("\nOrder taken : " + order); // Log when an order is taken
                System.out.println("Preparing: " + order); // Log when preparation starts
                Thread.sleep(2000); // Simulate coffee preparation time
                System.out.println("Prepared: " + order +"\n"); // Log when preparation is complete

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Reset the interrupt flag
            System.out.println("Barista interrupted while processing orders.");
        }
    }
}
