package org.example.simulatedannealing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.util.CommonFunctionsUtil;

public class SumPartitionSimulatedAnnealing {
    public static final double ALPHA_COOLING_RATE = 0.995;
    private final List<Double> allSolutionCollector = new ArrayList<>();
    private final List<Double> bestSolutionCollector = new ArrayList<>();
    private final Random random = new Random();
    private final double[] set;

    public SumPartitionSimulatedAnnealing(double[] set) {
        this.set = set;
    }

    public List<Double> getAllSolutionCollector() {
        return allSolutionCollector;
    }

    public List<Double> getBestSolutionCollector() {
        return bestSolutionCollector;
    }

    public double simulatedAnnealing() {
        boolean[] partition = CommonFunctionsUtil.generateRandomPartition(set, random);
        double currentDifference = CommonFunctionsUtil.calculateDifference(set, partition);
        //TRY(x, T) ≈ 0.9 START
        double temperature = findOptimalTemperature(partition, currentDifference);

        double best = currentDifference;
        int accepted = 0;
        int rejected = 0;

        int quasiEquilibriumOnRejection = 0;

        while (quasiEquilibriumOnRejection != 3) {
            for (int i = 0; i < set.length; i++) {
                int counter = random.nextInt(0, set.length);
                partition[counter] = !partition[counter];
                double newDifference = CommonFunctionsUtil.calculateDifference(set, partition);
                double delta = newDifference - currentDifference;
                if (random.nextDouble() < Math.exp(-(delta / temperature))) {
                    currentDifference = newDifference;
                    allSolutionCollector.add(newDifference);
                    if (newDifference < best) {
                        best = currentDifference;
                        accepted++;
                        bestSolutionCollector.add(best);
                    }
                } else {
                    rejected++;
                    partition[i] = !partition[i];
                }
                if (accepted == set.length) {
                    quasiEquilibriumOnRejection = 0;
                    accepted = 0;
                    rejected = 0;
                    temperature *= ALPHA_COOLING_RATE;
                }
                if (rejected == 2 * set.length) {
                    accepted = 0;
                    rejected = 0;
                    temperature *= ALPHA_COOLING_RATE;
                    quasiEquilibriumOnRejection++;
                }
            }
        }
        return best;
    }

    private double findOptimalTemperature(boolean[] partition, double currentDifference) {
        double initialTemperature = 0.1;
        double maxTemperature = 100.0;
        double step = 0.01;

        double expectedLevelOfAcceptance = 0.9;
        double toleranceToApproximation = 0.03;

        for (double temp = initialTemperature; temp <= maxTemperature; temp += step) {
            double checkedTemperature = checkTemperature(partition, currentDifference, temp);
            int successCounter = 0;
            for (int i = 0; i < 100; i++) {
                if (approximatelyEqual(checkedTemperature, expectedLevelOfAcceptance,
                        toleranceToApproximation)) {
                    successCounter++;
                }
                if (successCounter == 90) {
                    return temp;
                }
            }
        }
        throw new IllegalArgumentException("No optimal temperature found in the given range. "
                + "Consider expanding the range or adjusting step size.");
    }

    private double checkTemperature(boolean[] partition, double currentDifference,
                                    double temperature) {
        int accepted = 0;
        int tries = set.length;
        for (int i = 0; i < tries; i++) {
            boolean[] thisPartition = partition.clone();
            int counter = random.nextInt(0, set.length);
            thisPartition[counter] = !thisPartition[counter];
            double newDifference = CommonFunctionsUtil.calculateDifference(set, thisPartition);
            double delta = newDifference - currentDifference;
            if (random.nextDouble() < Math.exp(-(delta / temperature))) {
                accepted++;
            }
        }
        return (double) accepted / tries;
    }

    private boolean approximatelyEqual(double a, double b, double epsilon) {
        return Math.abs(a - b) <= epsilon;
    }
}
