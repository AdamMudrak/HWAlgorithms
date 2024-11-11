package org.example.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartBuilderUtil {

    public static void build(List<Double> doubles, String chartTitle) {

        XYSeries series = new XYSeries("Різниця цільової функції");
        for (int i = 0; i < doubles.size(); i++) {
            series.add(i + 1, doubles.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,
                "Ітерація",
                "Різниця цільової функції",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame frame = new JFrame(chartTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void buildHistograms(Map<Double, Long> frequencyMap) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Double, Long> frequencyMapRanged = new LinkedHashMap<>();
        fillFrequencyMapRanged(frequencyMapRanged);
        for (Map.Entry<Double, Long> entry : frequencyMap.entrySet()) {
            if (entry.getKey() < 0.1) {
                frequencyMapRanged.put(0.1, frequencyMapRanged.get(0.1) + entry.getValue());
            } else if (entry.getKey() < 0.2) {
                frequencyMapRanged.put(0.2, frequencyMapRanged.get(0.2) + entry.getValue());
            } else if (entry.getKey() < 0.3) {
                frequencyMapRanged.put(0.3, frequencyMapRanged.get(0.3) + entry.getValue());
            } else if (entry.getKey() < 0.4) {
                frequencyMapRanged.put(0.4, frequencyMapRanged.get(0.4) + entry.getValue());
            } else if (entry.getKey() < 0.5) {
                frequencyMapRanged.put(0.5, frequencyMapRanged.get(0.5) + entry.getValue());
            } else if (entry.getKey() < 0.6) {
                frequencyMapRanged.put(0.6, frequencyMapRanged.get(0.6) + entry.getValue());
            } else if (entry.getKey() < 0.7) {
                frequencyMapRanged.put(0.7, frequencyMapRanged.get(0.7) + entry.getValue());
            } else if (entry.getKey() < 0.8) {
                frequencyMapRanged.put(0.8, frequencyMapRanged.get(0.8) + entry.getValue());
            } else if (entry.getKey() < 0.9) {
                frequencyMapRanged.put(0.9, frequencyMapRanged.get(0.9) + entry.getValue());
            } else if (entry.getKey() < 1.0) {
                frequencyMapRanged.put(1.0, frequencyMapRanged.get(1.0) + entry.getValue());
            }
        }
        frequencyMapRanged.forEach((key, value) -> dataset.addValue(value,
                "Frequency", key.toString()));
        JFreeChart chart = ChartFactory.createBarChart(
                "Frequency Chart",
                "Value",
                "Frequency",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void fillFrequencyMapRanged(Map<Double, Long> frequencyMapRanged) {
        frequencyMapRanged.put(0.1, 0L);
        frequencyMapRanged.put(0.2, 0L);
        frequencyMapRanged.put(0.3, 0L);
        frequencyMapRanged.put(0.4, 0L);
        frequencyMapRanged.put(0.5, 0L);
        frequencyMapRanged.put(0.6, 0L);
        frequencyMapRanged.put(0.7, 0L);
        frequencyMapRanged.put(0.8, 0L);
        frequencyMapRanged.put(0.9, 0L);
        frequencyMapRanged.put(1.0, 0L);
    }
}
