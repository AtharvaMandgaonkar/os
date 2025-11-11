package com.osexam;

import java.util.Scanner;

class PriorityProcess {
    int pid, burstTime, priority, waitingTime, turnaroundTime, completionTime;

    public PriorityProcess(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class Priority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        PriorityProcess[] processes = new PriorityProcess[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time and priority for process " + (i + 1) + ": ");
            int bt = sc.nextInt();
            int priority = sc.nextInt();
            processes[i] = new PriorityProcess(i + 1, bt, priority);
        }

        // Sort processes by priority (Lower number = Higher priority)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (processes[j].priority > processes[j + 1].priority) {
                    PriorityProcess temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        int currentTime = 0;
        float totalWT = 0, totalTAT = 0;

        // Calculate WT, TAT, and CT
        for (PriorityProcess p : processes) {
            p.waitingTime = currentTime;
            p.completionTime = currentTime + p.burstTime;
            p.turnaroundTime = p.completionTime;
            currentTime += p.burstTime;

            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        // Print Gantt Chart
        System.out.println("\nGantt Chart:");
        System.out.print(" ");
        for (PriorityProcess p : processes) {
            System.out.print("-------");
        }
        System.out.println();
        System.out.print("|");
        for (PriorityProcess p : processes) {
            System.out.print("  P" + p.pid + "  |");
        }
        System.out.println();
        System.out.print(" ");
        for (PriorityProcess p : processes) {
            System.out.print("-------");
        }
        System.out.println();

        // Print time scale
        System.out.print("0");
        for (PriorityProcess p : processes) {
            System.out.printf("%7d", p.completionTime);
        }
        System.out.println("\n");

        // Print process table
        System.out.println("Process\tBT\tPriority\tCT\tTAT\tWT");
        for (PriorityProcess p : processes) {
            System.out.println("P" + p.pid + "\t" + p.burstTime + "\t" + p.priority + "\t\t" +  p.completionTime + "\t" + p.turnaroundTime + "\t" + p.waitingTime);
        }

        // Print averages
        System.out.printf("\nAverage Waiting Time: %.2f", (totalWT / n));
        System.out.printf("\nAverage Turnaround Time: %.2f\n", (totalTAT / n));

        sc.close();
    }
}
