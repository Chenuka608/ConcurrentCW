
public class Customer extends Thread {
    private final OrderQueue orderQueue;
    private final String order;

    public Customer(OrderQueue orderQueue,String order){
        this.orderQueue=orderQueue;
        this.order=order;
    }

}
