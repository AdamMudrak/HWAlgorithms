package org.example;

import static org.example.HcIterationDb.hillClimbIterationSolutions;
import static org.example.constants.Constants.HC_ALGORITHMS_CALLS;
import static org.example.constants.Constants.SA_ALGORITHMS_CALLS;

import java.util.List;
import java.util.stream.Collectors;
import org.example.hillclimbing.HcPartitionProblemSolver;
import org.example.simulatedannealing.SaPartitionProblemSolver;

public class Main {
    private static final int[] DATA_TO_TEST_AGAINST = {1, 2, 3, 9, 8, 7, 6, 5, 4};

    public static void main(String[] args) {
        hcExperimentRunner();
        saExperimentRunner();
    }

    private static void hcExperimentRunner() {
        HcPartitionProblemSolver solver = new HcPartitionProblemSolver(DATA_TO_TEST_AGAINST);

        Statistics hcExperiment = new Statistics();

        List<Integer> solutions = solver.multiStartHillClimb(HC_ALGORITHMS_CALLS);
        ChartBuilder.build(hillClimbIterationSolutions,"Графік збіжності Hill Climb");
        ChartBuilder.buildHistograms(solutions.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())));
        hcExperiment.analyzeResults(solutions);
    }

    private static void saExperimentRunner() {
        SaPartitionProblemSolver solver = new SaPartitionProblemSolver(DATA_TO_TEST_AGAINST);
        solver.multiStartSimulatedAnnealing(SA_ALGORITHMS_CALLS);
        ChartBuilder.build(solver.getIterationSolutions(), "Графік збіжності Simulated Annealing");
    }
}
