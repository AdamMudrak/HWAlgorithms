package org.example.hillclimbing;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HCExperiment {

    public void analyzeResults(List<Integer> solutions) {
        int bestSolution = Collections.min(solutions);
        int worstSolution = Collections.max(solutions);
        System.out.println("Краще рішення: " + bestSolution);
        System.out.println("Гірше рішення: " + worstSolution);
        System.out.println("Різниця між кращим та гіршим рішенням: " + (worstSolution - bestSolution));
        System.out.println("Гістограми рішень:");
        printHistograms(solutions);
        double averageSolution = solutions.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(System.lineSeparator()
                + "Середнє значення рішень: " + averageSolution);
        System.out.println("-".repeat(120));
    }

    private void printHistograms(List<Integer> solutions) {
        Map<Integer, Long> frequencyMap = solutions.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.print("   0%");
        int startPercent = 10;
        System.out.print(" ".repeat(5) + startPercent + "%");
        for(int i = 0; i < 9; i++) {
            startPercent += 10;
            System.out.print(" ".repeat(7) + startPercent + "%");
            if (i == 8) {
                System.out.println();
            }
        }
        System.out.println("   " + "#".repeat(100));
        frequencyMap.forEach((solution, frequency) ->
                System.out.println(" ".repeat(Math.toIntExact(frequency)) + frequency + "%" + System.lineSeparator()
                        + solution + ": " + "#".repeat(Math.toIntExact(frequency))));
    }
}
