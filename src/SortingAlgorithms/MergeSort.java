package SortingAlgorithms;

import com.sun.deploy.util.ArrayUtil;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import javafx.scene.chart.BarChart;
import utils.ColorScheme;
import utils.RandomArrayGenerator;

import java.util.Arrays;
import java.util.stream.Stream;

public class MergeSort extends AbstractSort {
    public MergeSort(BarChart chart) {
        super(chart);
    }



    @Override
    public void run() throws InterruptedException {
       mergeSort(copy_data, 0, copy_data.length - 1 );
    }

   //start to length end length not including right
    private void mergeSort(Double[] arr, int left, int right){
        if(left  >= right ) return;
        int middle = ((right - left) / 2) + left;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    private void merge(Double[] arr, int left, int middle, int right) {
        System.out.println(left + " " +  middle + " " + right);
        Double[] leftTemp = Arrays.copyOfRange(arr, left, middle + 1);
        Double[] rightTemp = Arrays.copyOfRange(arr, middle + 1, right + 1);
        int l = 0, r = 0, current = left;
        setSortRegionColor(left, right);
        while (l < leftTemp.length && r < rightTemp.length) { // for reaching end of array
            timeLineSortAnimationList.colorChange(ColorScheme.sortedColor, current);
            if(leftTemp[l] < rightTemp[r]){
                setValue(current, leftTemp[l]);
                arr[current++] = leftTemp[l++];
            }else {
                setValue(current, rightTemp[r]);
                arr[current++] = rightTemp[r++];
            }

        }
        //merge rest of left;
        while(l < leftTemp.length){
            timeLineSortAnimationList.colorChange(ColorScheme.sortedColor, current);
            setValue(current, leftTemp[l]);
            arr[current++] = leftTemp[l++];
        }
        //merge rest of right
        while(r < rightTemp.length){
            timeLineSortAnimationList.colorChange(ColorScheme.sortedColor, current);
            setValue(current, rightTemp[r]);
            arr[current++] = rightTemp[r++];
        }
    }

    private void setSortRegionColor(int start, int end) {
        for (int iterator = start; iterator < end; iterator ++) {
            timeLineSortAnimationList.colorChange(ColorScheme.mergeSortRegion, iterator);
        }
    }

    private void removeSortRegionColor(int start, int end) {
        for (int iterator = start; iterator < end; iterator ++) {
            timeLineSortAnimationList.colorChange(ColorScheme.defaultColor, iterator);
        }
    }

}
