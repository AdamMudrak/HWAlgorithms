package org.example.util;

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

    public static void build(List<Integer> integers, String chartTitle) {

        XYSeries series = new XYSeries("Різниця цільової функції");
        for (int i = 0; i < integers.size(); i++) {
            series.add(i + 1, integers.get(i));
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

    public static void buildHistograms(Map<Integer, Long> frequencyMap) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        frequencyMap.forEach((key, value) -> dataset.addValue(value, "Frequency", key.toString()));
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
}
