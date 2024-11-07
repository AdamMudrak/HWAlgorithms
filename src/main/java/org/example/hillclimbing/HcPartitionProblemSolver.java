package org.example.hillclimbing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.AlgorithmType;
import org.example.util.CommonFunctionsUtil;
import org.example.util.PrinterUtil;

public class HcPartitionProblemSolver {
    private final int[] set;
    private final Random random = new Random();
    private int solutionCounter;

    public HcPartitionProblemSolver(int[] set) {
        this.set = set;
    }

    public List<Integer> multiStartHillClimb(int numStarts) {
        solutionCounter = 1;
        List<Integer> solutions = new ArrayList<>();
        for (int i = 0; i < numStarts; i++) {
            int result = hillClimb();
            solutions.add(result);
            solutionCounter++;
        }
        return solutions;
    }

    private int hillClimb() {
        PrinterUtil.printSpliterator();

        boolean[] partition = CommonFunctionsUtil.generateRandomPartition(set, random);

        int currentDifference = CommonFunctionsUtil.calculateDifference(set, partition);
        PrinterUtil.printInitialDifference(currentDifference, solutionCounter,
                AlgorithmType.HillClimbing);

        boolean notImproved = true;

        while (true) {
            boolean improved = false;
            for (int i = 0; i < set.length; i++) {

                partition[i] = !partition[i];

                int newDifference = CommonFunctionsUtil.calculateDifference(set, partition);

                if (newDifference < currentDifference) {
                    currentDifference = newDifference;
                    PrinterUtil.printImprovedDifference(currentDifference, solutionCounter,
                            AlgorithmType.HillClimbing);

                    notImproved = false;
                    improved = true;
                } else {
                    partition[i] = !partition[i];
                }
            }
            if (!improved) {
                break;
            }
        }
        if (notImproved) {
            PrinterUtil.notImprovedPrinter(solutionCounter, AlgorithmType.HillClimbing);
        }
        return currentDifference;
    }
}
