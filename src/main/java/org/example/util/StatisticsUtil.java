package org.example.util;

import java.util.Collections;
import java.util.List;

public class StatisticsUtil {

    public void analyzeResults(List<Integer> solutions) {
        int bestSolution = Collections.min(solutions);
        int worstSolution = Collections.max(solutions);
        System.out.println("Краще рішення: " + bestSolution);
        System.out.println("Гірше рішення: " + worstSolution);
        System.out.println("Різниця між кращим та гіршим рішенням: "
                + (worstSolution - bestSolution));
        double averageSolution = solutions.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(System.lineSeparator()
                + "Середнє значення рішень: " + averageSolution);
    }
}
