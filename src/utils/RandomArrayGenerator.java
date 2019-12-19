package utils;

import javafx.scene.chart.XYChart;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomArrayGenerator {
    private static final Random rand = new Random();
    private static final double maxInteger = 101;
    private static final double minInteger = 1;


    public static Double[] generate(int n) {
        Double[] data = new Double[n];
        for (int i = 0; i < n; i++) {
            data[i] = rand.nextDouble() * maxInteger + minInteger;
        }
        return data;
    }


    public static List<XYChart.Data<String, Double>> generateData(int n) {
        List<XYChart.Data<String, Double>> data = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            data.add(new XYChart.Data<String, Double>(Integer.toString(i), (double) Math.round(rand.nextDouble() * maxInteger + minInteger)));
        }
        return data;
    }

}
