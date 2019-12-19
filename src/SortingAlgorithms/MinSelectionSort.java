package SortingAlgorithms;

import javafx.scene.chart.BarChart;

public class MinSelectionSort extends AbstractSort {
    public MinSelectionSort(BarChart<String, Double> chart) {
        super(chart);
    }

    @Override
    public void run() throws InterruptedException {

        for (int i = 0; i < copy_data.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < copy_data.length; j++) {
                if (compare(j, minIndex)) minIndex = j;
            }
            swap(i, minIndex);
            setSorted(i);
        }
    }
}
