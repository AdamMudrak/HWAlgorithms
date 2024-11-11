package org.example.hillclimbing;

import static org.example.hillclimbing.Db.hillClimbIterationSolutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.util.CommonFunctionsUtil;

public class HcPartitionProblemSolver {
    private final double[] set;
    private final Random random = new Random();

    public HcPartitionProblemSolver(double[] set) {
        this.set = set;
    }

    public List<Double> multiStartHillClimb(int numStarts) {
        List<Double> solutions = new ArrayList<>();
        for (int i = 0; i < numStarts; i++) {
            double result = hillClimb();
            solutions.add(result);
        }
        return solutions;
    }

    private double hillClimb() {

        boolean[] partition = CommonFunctionsUtil.generateRandomPartition(set, random);

        double currentDifference = CommonFunctionsUtil.calculateDifference(set, partition);

        while (true) {
            boolean improved = false;
            for (int i = 0; i < set.length; i++) {

                partition[i] = !partition[i];

                double newDifference = CommonFunctionsUtil.calculateDifference(set, partition);

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
