package org.example.util;

import java.util.Collections;
import java.util.List;

public class StatisticsUtil {

    public void analyzeResults(List<Double> solutions) {
        double bestSolution = Collections.min(solutions);
        double worstSolution = Collections.max(solutions);
        System.out.println("Краще рішення: " + bestSolution);
        System.out.println("Гірше рішення: " + worstSolution);
        System.out.println("Різниця між кращим та гіршим рішенням: "
                + (worstSolution - bestSolution));
        double sum = 0.0;
        for (double solution : solutions) {
            sum += solution;
        }
        double averageSolution = sum / solutions.size();

        System.out.println(System.lineSeparator()
                + "Середнє значення рішень: " + averageSolution);
    }
}
