// Barista class
class Barista implements Runnable {
    private final OrderQueue orderQueue;

    public Barista(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String order = orderQueue.takeOrder();
                System.out.println(Thread.currentThread().getName() + " is preparing: " + order);
                Thread.sleep(2000); // Simulate time taken to prepare the coffee
                System.out.println(order + " is ready! ENJOY!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}