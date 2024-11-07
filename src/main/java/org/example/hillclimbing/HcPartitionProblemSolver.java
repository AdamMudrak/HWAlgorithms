package org.example.hillclimbing;

import static org.example.HcIterationDb.hillClimbIterationSolutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.util.CommonFunctionsUtil;

public class HcPartitionProblemSolver {

    private final int[] set;
    private final Random random = new Random();

    public HcPartitionProblemSolver(int[] set) {
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
                    hillClimbIterationSolutions.add(currentDifference);
                } else {
                    partition[i] = !partition[i];
                }
            }
            if (!improved) {
                break;
            }
        }
        return currentDifference;
    }
}
