import java.util.*;

class Job {
    int id, deadline, profit;

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobScheduling {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the no of Job you want to enter: ");
        int n = in.nextInt();
        Job[] jobs = new Job[n];
        System.out.print("Enter the details of the Job: \n");
        for (int i = 0; i < n; i++) {
            System.out.println("Job " + (i + 1) + " :");
            System.out.print("Enter the id of Job: ");
            int id = in.nextInt();
            System.out.print("Enter the deadline of Job: ");
            int deadline = in.nextInt();
            System.out.print("Enter the profit of Job: ");
            int profit = in.nextInt();
            jobs[i] = new Job(id, deadline, profit);
        }

        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find the maximum deadline
        int maxDeadline = Integer.MIN_VALUE;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Create an array to keep track of scheduled jobs
        int[] slots = new int[maxDeadline + 1];
        int totalProfit = 0;

        // Schedule jobs
        for (Job job : jobs) {
            // Try to find a free slot for this job
            for (int i = job.deadline; i > 0; i--) {
                if (slots[i] == 0) { // If the slot is free
                    slots[i] = job.id; // Schedule the job
                    totalProfit += job.profit; // Add profit
                    System.out.println("Scheduled Job ID: " + job.id + " at slot: " + i + " (Profit: " + job.profit + ")");
                    break; // Break out of the loop once the job is scheduled
                }
            }
        }

        // Output the scheduled jobs
        System.out.print("Scheduled Jobs: ");
        for (int i = 1; i < slots.length; i++) {
            if (slots[i] != 0) {
                System.out.print(slots[i] + " ");
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }
}
