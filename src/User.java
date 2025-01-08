import java.util.Random;

public class User extends Thread {
    private final BathroomStalls stalls;
    private final String userType;  // Employee or Student

    public User(BathroomStalls stalls) {
        this.stalls = stalls;
        // Randomly assign user type as Employee or Student
        this.userType = Math.random() > 0.5 ? "Employee" : "Student";
    }

    @Override
    public void run() {
        // Try to enter a random bathroom stall
        boolean entered = false;
        Random rand = new Random();

        // Try to enter any random stall until successful
        for (int i = 0; i < stalls.totalStalls; i++) {
            int stallNumber = rand.nextInt(stalls.totalStalls);  // Get a random stall number

            // Attempt to enter a free stall
            if (stalls.enterStall(stallNumber, userType + " " + Thread.currentThread().getName())) {
                entered = true;
                // Simulate using the bathroom for a random amount of time
                try {
                    Thread.sleep((int) (Math.random() * 5000));  // Simulate time spent in the stall
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Leave the stall
                stalls.leaveStall(stallNumber, userType + " " + Thread.currentThread().getName());
                break;
            }
        }

        if (!entered) {
            System.out.println(userType + " " + Thread.currentThread().getName() + " is waiting for a stall.");
        }
    }
}
