// Customer class
class Customer implements Runnable {
    private final OrderQueue orderQueue;
    private final String order;

    public Customer(OrderQueue orderQueue, String order) {
        this.orderQueue = orderQueue;
        this.order = order;
    }

    @Override
    public void run() {
        try {
            orderQueue.placeOrder(order);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
