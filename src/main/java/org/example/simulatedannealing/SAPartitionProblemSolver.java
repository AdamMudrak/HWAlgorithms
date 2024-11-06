package org.example.simulatedannealing;

import org.example.CommonFunctionsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SAPartitionProblemSolver {
    private final int[] set;
    private final Random random = new Random();

    public SAPartitionProblemSolver(int[] set) {
        this.set = set;
    }

    public List<Integer> multiStartSimulatedAnnealing(int numStarts) {
        List<Integer> solutions = new ArrayList<>();
        for (int i = 0; i < numStarts; i++) {
            int result = simulatedAnnealing();
            solutions.add(result);
        }
        return solutions;
    }

    private int simulatedAnnealing() {
        boolean[] partition = CommonFunctionsUtil.generateRandomPartition(set, random);
        int currentDifference = CommonFunctionsUtil.calculateDifference(set, partition);
        double temperature = 10000;
        double coolingRate = 0.003;

        while (temperature > 1) {
            boolean[] newPartition = generateNeighborPartition(partition);
            int newDifference = CommonFunctionsUtil.calculateDifference(set, newPartition);

            if (shouldAcceptSolution(currentDifference, newDifference, temperature)) {
                partition = newPartition;
                currentDifference = newDifference;
            }

            temperature *= (1 - coolingRate);
        }

        return currentDifference;
    }

    private boolean[] generateNeighborPartition(boolean[] partition) {
        boolean[] newPartition = partition.clone();
        int index = random.nextInt(set.length);
        newPartition[index] = !newPartition[index];  // Toggle the element's set
        return newPartition;
    }

    private boolean shouldAcceptSolution(int currentDifference, int newDifference, double temperature) {
        if (newDifference < currentDifference) {
            return true;  // Always accept better solutions
        }
        double acceptanceProbability = Math.exp((currentDifference - newDifference) / temperature);
        return random.nextDouble() < acceptanceProbability;  // Accept worse solutions with a certain probability
    }
}