package com.osexam;
//Round Robin CPU Scheduling (Preemptive) with Gantt Chart
import java.util.*;

public class RR {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     System.out.print("Enter number of processes: ");
     int n = sc.nextInt();

     int pid[] = new int[n];
     int at[] = new int[n];
     int bt[] = new int[n];
     int rt[] = new int[n];
     int ct[] = new int[n];
     int tat[] = new int[n];
     int wt[] = new int[n];
     boolean visited[] = new boolean[n];

     // Input Arrival and Burst Times
     for (int i = 0; i < n; i++) {
         System.out.print("Enter Arrival Time and Burst Time for process " + (i + 1) + ": ");
         pid[i] = i + 1;
         at[i] = sc.nextInt();
         bt[i] = sc.nextInt();
         rt[i] = bt[i];
     }

     System.out.print("Enter Time Quantum: ");
     int quantum = sc.nextInt();

     Queue<Integer> q = new LinkedList<>();
     int time = 0;
     int completed = 0;
     StringBuilder ganttChart = new StringBuilder();
     List<Integer> timeLine = new ArrayList<>();

     // Sort processes by arrival time for consistency
     Integer[] order = new Integer[n];
     for (int i = 0; i < n; i++) order[i] = i;
     Arrays.sort(order, Comparator.comparingInt(i -> at[i]));

     // Start scheduling
     while (completed != n) {
         // Add newly arrived processes
         for (int i = 0; i < n; i++) {
             if (at[i] <= time && !visited[i]) {
                 q.add(i);
                 visited[i] = true;
             }
         }

         if (q.isEmpty()) {
             ganttChart.append("idle ");
             timeLine.add(time);
             time++;
             continue;
         }

         int idx = q.poll();
         int exec = Math.min(rt[idx], quantum);

         ganttChart.append("P").append(pid[idx]).append(" ");
         timeLine.add(time);

         rt[idx] -= exec;
         time += exec;

         // Add any newly arrived processes during this execution window
         for (int i = 0; i < n; i++) {
             if (at[i] <= time && !visited[i]) {
                 q.add(i);
                 visited[i] = true;
             }
         }

         if (rt[idx] == 0) {
             completed++;
             ct[idx] = time;
             tat[idx] = ct[idx] - at[idx];
             wt[idx] = tat[idx] - bt[idx];
         } else {
             q.add(idx);
         }
     }

     timeLine.add(time);

     // Print Gantt Chart
     System.out.println("\nGantt Chart:");
     System.out.print(" ");
     for (int i = 0; i < ganttChart.toString().trim().split(" ").length; i++) {
         System.out.print("-------");
     }
     System.out.println();
     System.out.print("|");
     for (String s : ganttChart.toString().trim().split(" ")) {
         System.out.printf(" %-5s|", s);
     }
     System.out.println();
     System.out.print(" ");
     for (int i = 0; i < ganttChart.toString().trim().split(" ").length; i++) {
         System.out.print("-------");
     }
     System.out.println();

     for (int t : timeLine) {
         System.out.printf("%-7d", t);
     }
     System.out.println("\n");

     // Output Table
     System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
     float avgWT = 0, avgTAT = 0;
     for (int i = 0; i < n; i++) {
         avgWT += wt[i];
         avgTAT += tat[i];
         System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
     }

     System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT / n);
     System.out.printf("Average Turnaround Time: %.2f\n", avgTAT / n);

     sc.close();
 }
}
