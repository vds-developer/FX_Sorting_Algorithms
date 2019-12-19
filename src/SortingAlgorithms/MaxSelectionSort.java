package SortingAlgorithms;

import javafx.scene.chart.BarChart;

public class MaxSelectionSort extends AbstractSort {
    public MaxSelectionSort(BarChart<String, Double> chart) {
        super(chart);
    }

    @Override
    public void run() throws InterruptedException {

        for (int i = 0; i < copy_data.length; i++) {
            int maxIndex = copy_data.length - i - 1;
            for (int j = copy_data.length - i - 2; j >= 0; j--) {
                if (compare(maxIndex, j)) maxIndex = j;
            }
            swap(copy_data.length - i - 1, maxIndex);
            setSorted(copy_data.length - i - 1);
        }
    }
}