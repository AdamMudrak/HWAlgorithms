package org.example.simulatedannealing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.util.CommonFunctionsUtil;

public class SaPartitionProblemSolver {
    private final List<Double> iterationSolutions = new ArrayList<>();
    private final double[] set;
    private final Random random = new Random();

    public SaPartitionProblemSolver(double[] set) {
        this.set = set;
    }

    public List<Double> getIterationSolutions() {
        return iterationSolutions;
    }

    public void simulatedAnnealing() {
        boolean[] partition = CommonFunctionsUtil.generateRandomPartition(set, random);
        double currentDifference = CommonFunctionsUtil.calculateDifference(set, partition);

        double temperature = 10000;
        double coolingRate = 0.003;
        int accepted = 0;
        int rejected = 0;
        int rejectedInARow = 0;

        int neighborhoodSize = set.length;

        while (rejectedInARow < 3) {
            boolean[] newPartition = generateNeighborPartition(partition);
            double newDifference = CommonFunctionsUtil.calculateDifference(set, newPartition);

            if (shouldAcceptSolution(currentDifference, newDifference, temperature)) {
                partition = newPartition;
                currentDifference = newDifference;
                accepted++;
                rejectedInARow = 0;
                iterationSolutions.add(currentDifference);
            } else {
                rejected++;
                rejectedInARow++;
            }

            if (accepted >= neighborhoodSize || rejected >= 2 * neighborhoodSize) {
                temperature *= (1 - coolingRate);
                accepted = 0;
                rejected = 0;
            }
        }
    }

    private boolean[] generateNeighborPartition(boolean[] partition) {
        boolean[] newPartition = partition.clone();
        int index = random.nextInt(set.length);
        newPartition[index] = !newPartition[index];
        return newPartition;
    }

    private boolean shouldAcceptSolution(double currentDifference, double newDifference,
                                         double temperature) {
        if (newDifference < currentDifference) {
            return true;
        }
        double acceptanceProbability = Math.exp((currentDifference - newDifference) / temperature);
        return random.nextDouble() < acceptanceProbability;
    }
}
