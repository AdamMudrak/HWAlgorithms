package org.example;

import static org.example.hillclimbing.Db.VALUES;
import static org.example.hillclimbing.Db.hillClimbIterationSolutions;

import java.util.List;
import java.util.stream.Collectors;
import org.example.hillclimbing.HcPartitionProblemSolver;
import org.example.simulatedannealing.SaPartitionProblemSolver;
import org.example.util.ChartBuilderUtil;
import org.example.util.StatisticsUtil;

public class Main {

    public static void main(String[] args) {
        hcExperimentRunner();
        saExperimentRunner();
    }

    private static void hcExperimentRunner() {
        HcPartitionProblemSolver solver = new HcPartitionProblemSolver(VALUES);

        StatisticsUtil hcExperiment = new StatisticsUtil();

        List<Double> solutions = solver.multiStartHillClimb(100);
        ChartBuilderUtil.build(hillClimbIterationSolutions,"Графік збіжності Hill Climb");
        ChartBuilderUtil.buildHistograms(solutions.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())));
        hcExperiment.analyzeResults(solutions);
    }

    private static void saExperimentRunner() {
        SaPartitionProblemSolver solver = new SaPartitionProblemSolver(VALUES);
        solver.simulatedAnnealing();
        ChartBuilderUtil.build(solver.getIterationSolutions(),
                "Графік збіжності Simulated Annealing");
    }
}
