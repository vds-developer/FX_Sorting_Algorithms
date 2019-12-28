package SortingAlgorithms;

import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import sample.TimeLineSortAnimation;
import utils.ColorScheme;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractSort {

    public TimeLineSortAnimation timeLineSortAnimationList;
    protected ObservableList<XYChart.Data<String, Double>> data;
    // for
    protected Double[] copy_data;
    LinkedList<Integer[]> swaps;
    Queue<Integer> compareQueue;
    Queue<Integer> sortedQueue;

    int currentSwapPostion = 0;
    int n;
    private BarChart<String, Double> chart;

    //index i is index i in list

    public AbstractSort(BarChart<String, Double> chart) {
        this.chart = chart;
        reset();
    }

    // i > j ?
    public boolean compare(int i, int j) {
        compareQueue.add(i);
        compareQueue.add(j);
        timeLineSortAnimationList.swapColorChange(ColorScheme.compareColor, i, j);
        removeAllComparisons();
        return copy_data[i] < copy_data[j];
    }


    public void setSorted(int i) {
        sortedQueue.add(i);
        timeLineSortAnimationList.colorChange(ColorScheme.sortedColor, i);
    }

    public void removeAllComparisons() {
        timeLineSortAnimationList.colorChange(ColorScheme.defaultColor, compareQueue);
    }

    public void swap(int i, int j) {
        timeLineSortAnimationList.addSwapAnimation(i, j);
        Double temp = copy_data[i];
        copy_data[i] = copy_data[j];
        copy_data[j] = temp;
    }

    public void setValue(int index, double value) {
        copy_data[index] = value;
        timeLineSortAnimationList.setValue(index, value);
    }


    //circular shift
    public void circularShiftRight(int i, int j) {
//        if (copy_data[j]  > copy_data.length) return;
        Double temp = copy_data[j].doubleValue();
        for (int start = j - 1; start >= i; start--) {
//            System.out.println("Shift start: " + start + " to : " + i);
            copy_data[start + 1] = copy_data[start].doubleValue();
            timeLineSortAnimationList.setValue(start + 1, copy_data[start]);
//            System.out.println(i+1 + " value: " + copy_data[i]);
        }
        copy_data[i] = temp;
        timeLineSortAnimationList.setValue(i, temp);
    }


    public void forward() {
        if (currentSwapPostion != swaps.size() - 1) currentSwapPostion++;
    }

    public void backward() {
        if (currentSwapPostion != 0) currentSwapPostion--;
    }

    public abstract void run() throws InterruptedException;

    public void next() {
        timeLineSortAnimationList.next();
    }

    public void autoPlay() {
        timeLineSortAnimationList.autoPlay();
//        CountDownLatch latch = new CountDownLatch(1);
//        while (!timeLineSortAnimationList.isFinished()){
//
//        }
    }

    public boolean isFinished() {
        return timeLineSortAnimationList.isFinished();
    }


    public void reset() {
        this.compareQueue = new LinkedList<>();
        this.sortedQueue = new LinkedList<>();
//        System.out.println("Starting size " + compareQueue.size());
        this.timeLineSortAnimationList = new TimeLineSortAnimation(chart);
        this.data = chart.getData().get(0).getData();
        this.copy_data = new Double[data.size()];
        data.forEach(x -> copy_data[Integer.parseInt(x.getXValue())] = x.getYValue());
    }

}
