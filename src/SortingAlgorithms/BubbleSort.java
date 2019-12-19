package SortingAlgorithms;

import javafx.scene.chart.BarChart;

public class BubbleSort extends AbstractSort {
    public BubbleSort(BarChart chart) {
        super(chart);
    }

    public void run() throws InterruptedException {
        data.forEach(d -> System.out.println(d.getYValue()));
        for (int i = 1; i < data.size(); i++) {
            System.out.println();
            for (int j = 0; j < data.size() - i; j++) {
                if (compare(j+1, j)) {
                    swap(j, j + 1);
                }
            }
            setSorted(data.size() - i);
        }
        setSorted(0);
    }

}
