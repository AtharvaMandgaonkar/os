package com.osexam;
import java.util.Scanner;

class Process {
    int pid, burstTime, arrivalTime, waitingTime, turnaroundTime;

    public Process(int pid, int burstTime, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes[i] = new Process(i + 1, bt, at);
        }

        // Sort processes based on arrival time (Bubble Sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime) {
                    Process temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        // Calculate waiting time and turnaround time
        int currentTime = 0; // Tracks the current completion time
        int totalWT = 0;
        int totalTAT = 0;

        for (Process p : processes) {
            
            // Check if the CPU was idle before this process arrived
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            // Calculate waiting time
            p.waitingTime = currentTime - p.arrivalTime;
            
            // Update current time to this process's completion time
            currentTime += p.burstTime; 

            // Calculate turnaround time
            p.turnaroundTime = p.waitingTime + p.burstTime; 

            // Add to totals
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        // Print Gantt Chart
        System.out.println("\nGantt Chart: ");
        for (Process p : processes) {
            System.out.print("P" + p.pid + " ");
        }
        System.out.println("\n");

        // Print process details
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (Process p : processes) {
            System.out.println("P" + p.pid + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" + p.waitingTime + "\t" + p.turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (totalWT / (float) n));
        System.out.println("Average Turnaround Time: " + (totalTAT / (float) n));

        sc.close();
    }
}
