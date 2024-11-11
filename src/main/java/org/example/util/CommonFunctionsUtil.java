package org.example.util;

import java.util.Random;

public class CommonFunctionsUtil {
    public static boolean[] generateRandomPartition(double[] set, Random random) {
        boolean[] partition = new boolean[set.length];
        for (int i = 0; i < set.length; i++) {
            partition[i] = random.nextBoolean();
        }
        return partition;
    }

    public static double calculateDifference(double[] set, boolean[] partition) {
        double sumA = 0;
        double sumB = 0;
        for (int i = 0; i < set.length; i++) {
            if (partition[i]) {
                sumA += set[i];
            } else {
                sumB += set[i];
            }
        }
        return Math.abs(sumA - sumB);
    }
}
