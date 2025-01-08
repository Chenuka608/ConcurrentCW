public class BathroomSimulation {
    public static void main(String[] args) {
        try {
            int totalStalls = 6;  // Number of bathroom stalls
            int totalUsers = 100; // Number of users

            if (totalStalls <= 0 || totalUsers < 0) {
                throw new IllegalArgumentException("The number of stalls must be greater than 0, and the number of users cannot be negative.");
            }

            // Create a BathroomStalls instance
            BathroomStalls bathroomStalls = new BathroomStalls(totalStalls);

            // Create and start threads for users (employees/students)
            for (int i = 0; i < totalUsers; i++) {
                new User(bathroomStalls).start();
            }

            // Wait for all threads to finish before printing final status
            Thread.sleep(10000);  // Simulate some running time (e.g., for 10 seconds)

            // Print the final stall status
            bathroomStalls.printStallsStatus();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
