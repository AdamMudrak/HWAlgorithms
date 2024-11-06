package org.example;

import org.example.hillclimbing.HCExperiment;
import org.example.hillclimbing.HCPartitionProblemSolver;
import org.example.simulatedannealing.SAExperiment;
import org.example.simulatedannealing.SAPartitionProblemSolver;

import java.util.List;

import static org.example.constants.ConsoleColoursConstants.ANSI_RED;
import static org.example.constants.ConsoleColoursConstants.ANSI_RESET;

public class Main {
    private static final int[] DATA_TO_TEST_AGAINST = {1, 2, 3, 9, 8, 7, 6, 5, 4};
    private static final int ALGORITHMS_CALLS = 100;
    private static final int PROGRAMME_RESTART_IMITATION = 3;

    public static void main(String[] args) {
        System.out.println(ANSI_RED + "Hill Climbing" + ANSI_RESET);
        hcExperimentRunner();
        System.out.println(ANSI_RED + "SimulatedAnnealing" + ANSI_RESET);
        saExperimentRunner();
    }

    private static void hcExperimentRunner() {
        HCPartitionProblemSolver solver = new HCPartitionProblemSolver(DATA_TO_TEST_AGAINST);

        HCExperiment hcExperiment = new HCExperiment();

        for (int i = 0; i < PROGRAMME_RESTART_IMITATION; i++) {
            List<Integer> solutions = solver.multiStartHillClimb(ALGORITHMS_CALLS);
            hcExperiment.analyzeResults(solutions);
        }
    }

    private static void saExperimentRunner() {
        SAPartitionProblemSolver solver = new SAPartitionProblemSolver(DATA_TO_TEST_AGAINST);

        SAExperiment saExperiment = new SAExperiment();

        for (int i = 0; i < PROGRAMME_RESTART_IMITATION; i++) {
            List<Integer> solutions = solver.multiStartSimulatedAnnealing(ALGORITHMS_CALLS);
            saExperiment.analyzeResults(solutions);
        }
    }
}