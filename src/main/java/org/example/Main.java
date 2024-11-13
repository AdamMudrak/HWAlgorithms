package org.example;

import static org.example.hillclimbing.Db.HARD_VALUES;

import java.util.List;
import java.util.stream.Collectors;
import org.example.hillclimbing.HcPartitionProblemSolver;
import org.example.simulatedannealing.SumPartitionSimulatedAnnealing;
import org.example.util.ChartBuilderUtil;
import org.example.util.StatisticsUtil;

public class Main {

    public static void main(String[] args) {
        //hcExperimentRunner();
        saExperimentRunner();
    }

    private static void hcExperimentRunner() {
        HcPartitionProblemSolver solver = new HcPartitionProblemSolver(HARD_VALUES);

        StatisticsUtil hcExperiment = new StatisticsUtil();

        List<Double> solutions = solver.multiStartHillClimb(100);
        ChartBuilderUtil.build(solver.getHillClimbIterationSolutions(),"Графік збіжності Hill Climb");
        ChartBuilderUtil.buildHistograms(solutions.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())));
        hcExperiment.analyzeResults(solutions);
    }

    private static void saExperimentRunner() {
        SumPartitionSimulatedAnnealing simulatedAnnealing = new SumPartitionSimulatedAnnealing(HARD_VALUES);
        System.out.println("Краще: " + simulatedAnnealing.simulatedAnnealing());
        ChartBuilderUtil.build(simulatedAnnealing.getSolutionCollector(),
                "Графік збіжності Simulated Annealing");
    }
}
