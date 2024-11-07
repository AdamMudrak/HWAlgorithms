package org.example.simulatedannealing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.util.CommonFunctionsUtil;

public class SaPartitionProblemSolver {
    private final List<Integer> iterationSolutions = new ArrayList<>();
    private final int[] set;
    private final Random random = new Random();

    public SaPartitionProblemSolver(int[] set) {
        this.set = set;
    }

    public List<Integer> getIterationSolutions() {
        return iterationSolutions;
    }

    public void multiStartSimulatedAnnealing(int numStarts) {
        for (int i = 0; i < numStarts; i++) {
            simulatedAnnealing();
        }
    }

    private void simulatedAnnealing() {

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
                iterationSolutions.add(currentDifference);
            }
            temperature *= (1 - coolingRate);
        }
    }

    private boolean[] generateNeighborPartition(boolean[] partition) {
        boolean[] newPartition = partition.clone();
        int index = random.nextInt(set.length);
        newPartition[index] = !newPartition[index];
        return newPartition;
    }

    private boolean shouldAcceptSolution(int currentDifference, int newDifference,
                                         double temperature) {
        if (newDifference < currentDifference) {
            return true;
        }
        double acceptanceProbability = Math.exp((currentDifference - newDifference) / temperature);
        return random.nextDouble() < acceptanceProbability;
    }
}
