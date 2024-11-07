package org.example;

import static org.example.hillclimbing.HcIterationDb.hillClimbIterationSolutions;

import java.util.List;
import java.util.stream.Collectors;
import org.example.hillclimbing.HcPartitionProblemSolver;
import org.example.simulatedannealing.SaPartitionProblemSolver;
import org.example.util.ChartBuilderUtil;
import org.example.util.StatisticsUtil;

public class Main {
    private static final int[] DATA_TO_TEST_AGAINST = {1, 2, 3, 9, 8, 7, 6, 5, 4};

    public static void main(String[] args) {
        hcExperimentRunner();
        saExperimentRunner();
    }

    private static void hcExperimentRunner() {
        HcPartitionProblemSolver solver = new HcPartitionProblemSolver(DATA_TO_TEST_AGAINST);

        StatisticsUtil hcExperiment = new StatisticsUtil();

        List<Integer> solutions = solver.multiStartHillClimb(100);
        ChartBuilderUtil.build(hillClimbIterationSolutions,"Графік збіжності Hill Climb");
        ChartBuilderUtil.buildHistograms(solutions.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())));
        hcExperiment.analyzeResults(solutions);
    }

    private static void saExperimentRunner() {
        SaPartitionProblemSolver solver = new SaPartitionProblemSolver(DATA_TO_TEST_AGAINST);
        solver.multiStartSimulatedAnnealing(1);
        ChartBuilderUtil.build(solver.getIterationSolutions(),
                "Графік збіжності Simulated Annealing");
    }
}
