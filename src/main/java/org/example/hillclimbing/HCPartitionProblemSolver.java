package org.example.hillclimbing;

import org.example.CommonFunctionsUtil;

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
        boolean[] partition = CommonFunctionsUtil.generateRandomPartition(set, random);
        int currentDifference = CommonFunctionsUtil.calculateDifference(set, partition);
        while (true) {
            boolean improved = false;
            for (int i = 0; i < set.length; i++) {
                partition[i] = !partition[i];
                int newDifference = CommonFunctionsUtil.calculateDifference(set, partition);
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
}
