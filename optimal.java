package com.osexam;

import java.util.*;

public class optimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of frames: ");
        int frames = sc.nextInt();
        System.out.println("Enter number of pages: ");
        int pagesCount = sc.nextInt();

        int[] pages = new int[pagesCount];
        System.out.println("Enter the page reference string: ");
        for (int i = 0; i < pagesCount; i++) {
            pages[i] = sc.nextInt();
        }

        int[] frameArray = new int[frames];
        Arrays.fill(frameArray, -1);
        int pageFaults = 0;
        int pageHits = 0;

        for (int i = 0; i < pagesCount; i++) {
            int currentPage = pages[i];
            boolean isHit = isInFrames(frameArray, currentPage);
            
            if (!isHit) {
                pageFaults++;
                int replaceIndex = findOptimalIndex(frameArray, pages, i + 1);
                frameArray[replaceIndex] = currentPage;
            } else {
                pageHits++;
            }

            System.out.print("Frames after inserting page " + currentPage + ": ");
            printFrames(frameArray);
            
            if (isHit) {
                System.out.println(" (Hit)");
            } else {
                System.out.println();
            }
        }

        System.out.println("Total page faults: " + pageFaults);
        System.out.println("Total page hits: " + pageHits);
    }

    private static boolean isInFrames(int[] frames, int page) {
        for (int frame : frames) {
            if (frame == page) {
                return true;
            }
        }
        return false;
    }

    private static int findOptimalIndex(int[] frames, int[] pages, int start) {
        int farthest = -1; 
        int index = 0;     

        for (int i = 0; i < frames.length; i++) {
            
            if (frames[i] == -1) {
                return i;
            }

            int j;
            for (j = start; j < pages.length; j++) {
                if (frames[i] == pages[j]) {
                    if (j > farthest) {
                        farthest = j; 
                        index = i;    
                    }
                    break; 
                }
            }

            if (j == pages.length) {
                return i;
            }
        }
        
        return index;
    }

    private static void printFrames(int[] frames) {
        for (int frame : frames) {
            if (frame == -1) {
                System.out.print("[ ] ");
            } else {
                System.out.print("[" + frame + "] ");
            }
        }
    }
}
