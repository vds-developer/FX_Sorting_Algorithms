package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TimeLineSortAnimation {
    private final int swapDuration = 10;
    private final int colorSwitchDuration = 10;


    int current = 0;
    private BarChart<String, Double> chart;
    private ObservableList<XYChart.Data<String, Double>> data;
    private List<Timeline> timelineList;
    private Iterator<Timeline> iterator;
    private boolean isFinished;

    public TimeLineSortAnimation(BarChart<String, Double> chart) {
        this.chart = chart;
        this.data = chart.getData().get(0).getData();
        timelineList = new LinkedList<>();
        iterator = timelineList.iterator();
        isFinished = false;
    }


    public void colorChange(String color, int i, int duration) {
        timelineList.add(new Timeline(
                new KeyFrame(Duration.millis(duration),
                        event -> {
                            data.get(i).getNode().setStyle("-fx-bar-fill:" + color + ";");
                        }
                )
        ));
    }

    public void colorChange(String color, int i) {
        colorChange(color, i, colorSwitchDuration);
    }

    public void swapColorChange(String color, int i, int j, int duration) {
        timelineList.add(new Timeline(
                new KeyFrame(Duration.millis(duration),
                        event -> {
                            data.get(i).getNode().setStyle("-fx-bar-fill:" + color + ";");
                            data.get(j).getNode().setStyle("-fx-bar-fill:" + color + ";");
                        }
                )
        ));
    }

    public void swapColorChange(String color, int i, int j) {
        swapColorChange(color, i, j, colorSwitchDuration);
    }

    public void colorChange(String color, Queue<Integer> i) {
        int[] temp = new int[i.size()];
        for (int pos = 0; pos < temp.length; pos++) {
            temp[pos] = i.remove();
        }
        timelineList.add(new Timeline(
                new KeyFrame(Duration.millis(colorSwitchDuration),
                        event -> {
                            for (int iter : temp
                            ) {
                                data.get(iter).getNode().setStyle("-fx-bar-fill:" + color + ";");
                            }
                        }
                )
        ));
    }

    public void addSwapAnimation(int i, int j) {
        timelineList.add(new Timeline(
                new KeyFrame(Duration.millis(swapDuration),
                        event -> {
                            Double temp = data.get(i).getYValue().doubleValue();
                            data.get(i).setYValue(data.get(j).getYValue());
                            data.get(j).setYValue(temp);
                        }
                )
        ));
    }

//    public void addShiftRightAnimation(int i, int numberPosition){
//        timelineList.add(new Timeline(
//                new KeyFrame(Duration.millis(swapDuration),
//                        event -> {
//                            data.get(i).setYValue(data.get(i).getYValue());
//                        }
//                )
//        ));
//    }

    public void setValue(int i, Double value, double duration) {
        timelineList.add(new Timeline(
                new KeyFrame(Duration.millis(duration),
                        event -> {
                            data.get(i).setYValue(value);
                        }
                )
        ));
    }

    public void setValue(int i, Double value) {
        setValue(i, value, swapDuration);
    }


    public void next() {
        timelineList.get(current).play();
        current++;
    }

    public boolean isFinished() {
        return isFinished;
    }

//    public void addFinish(){
//        timelineList.add(new Timeline(
//                new KeyFrame( Duration.millis(0),
//                        event -> {
//                            isFinished = true;
//                        }
//                )));
//    }

    public void autoPlay() {
//        addFinish();
        Iterator<Timeline> iterator = timelineList.iterator();
        Timeline current = iterator.next();
        Timeline next;
        current.play();
        System.out.println(timelineList.size());
        while (iterator.hasNext()) {
            next = iterator.next();
            Timeline finalNext = next;
            current.setOnFinished(e -> finalNext.play());
            current = finalNext;
        }

    }
}
