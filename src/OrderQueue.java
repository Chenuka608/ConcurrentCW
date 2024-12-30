import java.util.LinkedList;

public class OrderQueue {
    private final LinkedList<String> queue = new LinkedList<>();
    private final int capacity;

    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

   public synchronized void addOrder(String order) throws InterruptedException{
        while(queue.size()==capacity){
            wait(); // Waits if the queue is currently full
        }
        queue.add(order);
       System.out.println("Order has been added : " + order);
       notifyAll(); //Notifies baristas that an order is available
   }
   public synchronized String takeOrder() throws InterruptedException{
        while(queue.isEmpty()){
            wait();
        }
        String order=queue.removeFirst();
       System.out.println("Order taken : " + order);
       notifyAll();
       return order;
   }
}
