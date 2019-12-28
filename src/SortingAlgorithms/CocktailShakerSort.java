package SortingAlgorithms;

import javafx.scene.chart.BarChart;

public class CocktailShakerSort extends AbstractSort {

    public CocktailShakerSort(BarChart<String, Double> chart) {
        super(chart);
    }

    @Override
    public void run() throws InterruptedException {
        int start = 0;
        int end = copy_data.length - 1;
        while(start <= end){
            for (int i = 0; i < end; i++){
                if(copy_data[i] > copy_data[i+1]){
                    swap(i, i+1);
                }
            }
            setSorted(end);
            end--;
            for(int i = end; i>start; i -- ) {
                if(copy_data[i] < copy_data[i-1]){
                    swap(i, i-1);
                }
            }
            setSorted(start);
            start ++;
        }

    }
}
