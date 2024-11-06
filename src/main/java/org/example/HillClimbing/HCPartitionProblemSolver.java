package org.example.HillClimbing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HCPartitionProblemSolver {
    private final int[] set;
    private final Random random = new Random();

    public HCPartitionProblemSolver(int[] set) {
        this.set = set;
    }

    public List<Integer> multiStartHillClimb(int numStarts) {
        List<Integer> solutions = new ArrayList<>();
        for (int i = 0; i < numStarts; i++) {
            int result = hillClimb();
            solutions.add(result);
        }
        return solutions;
    }

    private int hillClimb() {
        boolean[] partition = generateRandomPartition();
        int currentDifference = calculateDifference(partition);

        while (true) {
            boolean improved = false;
            for (int i = 0; i < set.length; i++) {
                partition[i] = !partition[i];
                int newDifference = calculateDifference(partition);
                if (newDifference < currentDifference) {
                    currentDifference = newDifference;
                    improved = true;
                } else {
                    partition[i] = !partition[i];
                }
            }
            if (!improved) break;
        }

        return currentDifference;
    }

    private boolean[] generateRandomPartition() {
        boolean[] partition = new boolean[set.length];
        for (int i = 0; i < set.length; i++) {
            partition[i] = random.nextBoolean();
        }
        return partition;
    }

    private int calculateDifference(boolean[] partition) {
        int sumA = 0, sumB = 0;
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
