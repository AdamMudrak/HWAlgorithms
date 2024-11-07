package org.example.util;

import static org.example.constants.Constants.ANSI_RED;
import static org.example.constants.Constants.ANSI_RESET;
import static org.example.constants.Constants.REPEAT_SPLITERATOR;
import static org.example.constants.Constants.SPLITERATOR;

public class PrinterUtil {

    public static void printSpliterator() {
        System.out.println(ANSI_RED
                + SPLITERATOR.repeat(REPEAT_SPLITERATOR) + ANSI_RESET);
    }

    public static void printInitialDifference(int currentDifference, int solutionCounter) {
        System.out.println("Solution: " + solutionCounter + ", current difference: "
                + currentDifference);
    }

    public static void printImprovedDifference(int currentDifference, int solutionCounter) {
        System.out.println("Solution: " + solutionCounter + ", current difference: "
                + currentDifference + "; improvement progress "
                + "#".repeat(currentDifference));
    }

    public static void notImprovedPrinter(int solutionCounter) {
        System.out.println("No improvement for solution " + solutionCounter);
    }
}
