package org.example;

import static org.example.constants.Constants.ANSI_PURPLE;
import static org.example.constants.Constants.ANSI_RESET;
import static org.example.constants.Constants.HC_ALGORITHMS_CALLS;
import static org.example.constants.Constants.SA_ALGORITHMS_CALLS;

import java.util.List;
import org.example.hillclimbing.HcPartitionProblemSolver;
import org.example.simulatedannealing.SaPartitionProblemSolver;

public class Main {
    private static final int[] DATA_TO_TEST_AGAINST = {1, 2, 3, 9, 8, 7, 6, 5, 4};

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hill Climbing" + ANSI_RESET);
        hcExperimentRunner();
        System.out.println(ANSI_PURPLE + "SimulatedAnnealing" + ANSI_RESET);
        saExperimentRunner();
    }

    private static void hcExperimentRunner() {
        HcPartitionProblemSolver solver = new HcPartitionProblemSolver(DATA_TO_TEST_AGAINST);

        Experiment hcExperiment = new Experiment();

        List<Integer> solutions = solver.multiStartHillClimb(HC_ALGORITHMS_CALLS);
        hcExperiment.analyzeResults(solutions);
    }

    private static void saExperimentRunner() {
        SaPartitionProblemSolver solver = new SaPartitionProblemSolver(DATA_TO_TEST_AGAINST);

        Experiment saExperiment = new Experiment();

        List<Integer> solutions = solver.multiStartSimulatedAnnealing(SA_ALGORITHMS_CALLS);
        saExperiment.analyzeResults(solutions);
    }
}
