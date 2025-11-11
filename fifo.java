package com.osexam;

import java.util.Scanner;
import java.util.Arrays;

public class FIFO{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of pages in reference string:");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.println("Enter the reference string (e.g., 7 0 1 2 0 ...):");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.println("Enter the number of frames (memory capacity):");
        int capacity = sc.nextInt();

        int[] frames = new int[capacity];
        
        Arrays.fill(frames, -1); 

        int pageFaults = 0;
        
        int oldestFrameIndex = 0; 

        System.out.println("\nPage\tFrames");
        System.out.println("----\t------");

        for (int page : pages) {
            
            boolean isHit = false;
            for (int frame : frames) {
                if (frame == page) {
                    isHit = true;
                    break;
                }
            }

            if (isHit) {
                System.out.println(page + "\t" + Arrays.toString(frames) + " (Hit)");
            } else {
                pageFaults++;

                frames[oldestFrameIndex] = page;

                oldestFrameIndex = (oldestFrameIndex + 1) % capacity;

                System.out.println(page + "\t" + Arrays.toString(frames));
            }
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}
