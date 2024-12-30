public class Barista extends Thread{
    private final OrderQueue orderQueue;

    public Barista(OrderQueue orderQueue){
        this.orderQueue=orderQueue;
    }

    @Override
    public void run(){
        try{
            while(true){
                String order=orderQueue.takeOrder();
                Thread.sleep(2000);
                System.out.println("Prepared : " + order);
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Sorry :( Barista encountered a problem ");
        }
    }
}
