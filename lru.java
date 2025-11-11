package com.osexam;

import java.util.*;

public class lru {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of frames: ");
        int numberOfFrames = scanner.nextInt();
        
        System.out.print("Enter the number of pages: ");
        int numberOfPages = scanner.nextInt();
        
        System.out.print("Enter the page reference string (space-separated): ");
        int[] pageReferenceString = new int[numberOfPages];
        for (int i = 0; i < numberOfPages; i++) {
            pageReferenceString[i] = scanner.nextInt();
        }

        LinkedList<Integer> frames = new LinkedList<>();
        int pageFaults = 0;
        int pageHits = 0;

        System.out.println("\nPage\tFrames");
        System.out.println("----\t------");

        for (int page : pageReferenceString) {
            System.out.print(page + "\t");

            if (!frames.contains(page)) {
                // --- Page Fault ---
                pageFaults++;
                if (frames.size() >= numberOfFrames) {
                    frames.removeFirst(); // Remove the least recently used page
                }
                frames.addLast(page); // Add new page to the end (most recent)
                
                System.out.println(frames);

            } else {
                // --- Page Hit ---
                pageHits++;
                frames.remove(Integer.valueOf(page)); // Remove page from its old position
                frames.addLast(page); // Move page to the end (most recent)
                
                System.out.println(frames + " (Hit)");
            }
        }
        
        System.out.println("\nTotal Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);
        
        scanner.close();
    }
}
