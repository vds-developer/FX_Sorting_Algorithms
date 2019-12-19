package SortingAlgorithms;

import javafx.scene.chart.BarChart;
import utils.ColorScheme;

public class InsertionSort extends AbstractSort {
    public InsertionSort(BarChart<String, Double> chart) {
        super(chart);
    }

    @Override
    public void run() throws InterruptedException {
        for(int toBeSortedIndex=0; toBeSortedIndex<copy_data.length; toBeSortedIndex++) {
//            Double insertValue = copy_data[toBeSortedIndex];
            timeLineSortAnimationList.colorChange("purple", toBeSortedIndex);
            for(int sortedIndex=0; sortedIndex<toBeSortedIndex ; sortedIndex++){
//                }
                if(!compare(sortedIndex, toBeSortedIndex)) {
                    System.out.println(sortedIndex + " " + toBeSortedIndex);
                    circularShiftRight(sortedIndex, toBeSortedIndex);
                    break;
                }
            }
            for (Double copy_datum : copy_data) {
                System.out.print(copy_datum + " ");
            }
            System.out.println(toBeSortedIndex);
            setSorted(toBeSortedIndex);
//            sortedQueue.forEach(x ->timeLineSortAnimationList.colorChange("green", x, 10));


        }
    }

    @Override     // i > j ?
    public boolean compare(int i, int j) {
        compareQueue.add(i);
        timeLineSortAnimationList.colorChange(ColorScheme.compareColor, i);
        removeAllComparisons();
//        removeAllComparisons();
        return copy_data[i] < copy_data[j];
    }

    @Override
    public void removeAllComparisons() {
        timeLineSortAnimationList.colorChange(ColorScheme.sortedColor, compareQueue);
        //remove everything
//        compareQueue.removeAll(compareQueue);
    }

//    //copy shift 1 right starting from i shift till j
//    // note j+1 will be elimanted
//    public void shiftOneRight(int i, int j){
//        for(int start = i; start < j+1; start ++){
//            swap(start, start+1);
//        }
//    }
}
