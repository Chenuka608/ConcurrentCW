import java.util.concurrent.Semaphore;

public class BathroomStalls {
    private final Semaphore availableStalls;  // Semaphore to manage available stalls
    public final int totalStalls;            // Total number of stalls
    private final String[] stallOccupancy;    // Array to track which stall is occupied

    public BathroomStalls(int stalls) {
        if (stalls <= 0) {
            throw new IllegalArgumentException("Stalls must be greater than zero.");
        }
        this.totalStalls = stalls;
        this.availableStalls = new Semaphore(stalls);  // Initialize semaphore with the number of stalls
        this.stallOccupancy = new String[stalls];      // Initialize stall occupancy tracking
    }

    // Method for a user to enter a stall
    public boolean enterStall(int stallNumber, String userName) {
        try {
            availableStalls.acquire();  // If no stall is available, the user will wait

            // Occupy the stall if it's free
            if (stallOccupancy[stallNumber] == null) {
                stallOccupancy[stallNumber] = userName;
                System.out.println(userName + " entered Toilet " + (stallNumber + 1));
                return true;  // Successfully entered a stall
            } else {
                availableStalls.release();  // If the stall is already occupied, release and retry
                return false;
            }

        } catch (InterruptedException e) {
            return false;  // Interrupted while waiting to enter
        }
    }

    // Method for a user to leave a stall
    public void leaveStall(int stallNumber, String userName) {
        stallOccupancy[stallNumber] = null;  // Mark the stall as available
        availableStalls.release();  // Release the stall for another user
        System.out.println(userName + " left Toilet " + (stallNumber + 1));
        System.out.println("Toilet " + (stallNumber + 1) + " is now free to use.");
    }

    // Method to check if there are available stalls and print current status
    public void printStallsStatus() {
        System.out.print("Stalls status: ");
        for (int i = 0; i < totalStalls; i++) {
            if (stallOccupancy[i] == null) {
                System.out.print("Toilet " + (i + 1) + " (Free) ");
            } else {
                System.out.print("Toilet " + (i + 1) + " (Occupied by " + stallOccupancy[i] + ") ");
            }
        }
        System.out.println();
    }
}
