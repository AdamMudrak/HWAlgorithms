package org.example;

import org.example.HillClimbing.HCExperiment;
import org.example.HillClimbing.HCPartitionProblemSolver;

import java.util.List;

public class Main {
    private static final int[] DATA_TO_TEST_AGAINST = {1, 2, 3, 9, 8, 7, 6, 5, 4};
    private static final int ALGORITHMS_CALLS = 100;
    private static final int PROGRAMME_RESTART_IMITATION = 10;

    public static void main(String[] args) {
        hcExperimentRunner();
    }

    private static void hcExperimentRunner() {
        HCPartitionProblemSolver solver = new HCPartitionProblemSolver(DATA_TO_TEST_AGAINST);

        HCExperiment hcExperiment = new HCExperiment();

        for (int i = 0; i < PROGRAMME_RESTART_IMITATION; i++) {
            List<Integer> solutions = solver.multiStartHillClimb(ALGORITHMS_CALLS);
            hcExperiment.analyzeResults(solutions);
        }
    }
}