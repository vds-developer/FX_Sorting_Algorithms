package sample;

import SortingAlgorithms.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import utils.RandomArrayGenerator;

import java.util.List;

public class Controller {
    final BarChart.Series<String, Double> data = new BarChart.Series<>();
    @FXML
    Slider nSlider;
    @FXML
    Label nSize;
    ObservableList<XYChart.Series<String, Double>> barChartData = FXCollections.observableArrayList();
    @FXML
    Button resetButton;
    @FXML
    Menu sortAlgorithm;
    @FXML
    Label SortAlgorithmLabel;
    @FXML
    private BarChart<String, Double> chart;
    @FXML
    private Button playButton;
    private AbstractSort sort;

    @FXML
    public void sort() throws InterruptedException {
        sort.reset();
        sort.run();
        playButton.setDisable(false);
        resetButton.setDisable(false);
        sort.autoPlay();
        playButton.setDisable(true);
        resetButton.setDisable(false);
    }

    @FXML
    public void reset() {
        resetButton.setDisable(true);
        playButton.setDisable(false);
        initializeChart((int) Math.round(nSlider.getValue()));

    }

    @FXML
    public void forward() {
        System.out.println("Forward");
    }

    @FXML
    public void backward() {
        sort.next();
    }

    @FXML
    public void initialize() throws InterruptedException {
        nSize.setText(Integer.toString((int) Math.round(nSlider.getValue())));
        nSlider.valueProperty().addListener((observable, oldValue, newValue)
                        -> {
                    nSize.setText(Integer.toString(newValue.intValue()));
                    initializeChart(newValue.intValue());
                }
        );
        setSortingAlgoritm(SortAlgorithms.BUBBLE);
    }

    private void initializeChart(int n) {
        chart.getData().clear();
        data.getData().clear();
        List<XYChart.Data<String, Double>> list = RandomArrayGenerator.generateData(n);
        data.getData().addAll(list);
        barChartData.addAll(data);
        chart.setData(barChartData);
        playButton.setDisable(false);
    }

    public AbstractSort setSortingAlgoritm(SortAlgorithms algo) {
        initializeChart(Integer.parseInt(nSize.getText()));
        switch (algo) {
            case INSERTION: {
                chart.setTitle("Insertion Sort");
                SortAlgorithmLabel.setText("Insertion Sort");
                sort = new InsertionSort(chart);
                break;
            }
            case BUBBLE: {
                chart.setTitle("Bubble Sort");
                SortAlgorithmLabel.setText("Bubble Sort");
                sort = new BubbleSort(chart);
                break;
            }
            case MAXSELECTION: {
                chart.setTitle("Max Selection Sort");
                SortAlgorithmLabel.setText("Max Selection Sort");
                sort = new MaxSelectionSort(chart);
                break;
            }
            case MINSELECTION: {
                chart.setTitle("Min Selection Sort");
                SortAlgorithmLabel.setText("Min Selection Sort");
                sort = new MinSelectionSort(chart);
                break;
            }
            case MERGE: {
                chart.setTitle("Merge Sort");
                SortAlgorithmLabel.setText("Merge Sort");
                sort = new MergeSort(chart);
                break;
            }
            case COCKTAILSHAKER: {
                chart.setTitle("Cocktail Shaker Sort");
                SortAlgorithmLabel.setText("Cocktail Shaker Sort");
                sort = new CocktailShakerSort(chart);
                break;
            }
            default: {
                chart.setTitle("Insertion Sort");
                SortAlgorithmLabel.setText("Insertion Sort");
                sort = new InsertionSort(chart);
                break;
            }
        }
        return sort;
    }

    public void setBubbleSort(ActionEvent actionEvent) {
        setSortingAlgoritm(SortAlgorithms.BUBBLE);

    }

    public void setInsertionSort(ActionEvent actionEvent) {
        setSortingAlgoritm(SortAlgorithms.INSERTION);
    }

    public void setMaxSelectionSort(ActionEvent actionEvent) {
        setSortingAlgoritm(SortAlgorithms.MAXSELECTION);
    }

    public void setMinSelectionSort(ActionEvent actionEvent) {
        setSortingAlgoritm(SortAlgorithms.MINSELECTION);
    }

    public void setMergeSort(ActionEvent actionEvent) {
        setSortingAlgoritm(SortAlgorithms.MERGE);
    }

    public void setCocktailShakerSort(ActionEvent actionEvent) {
        setSortingAlgoritm(SortAlgorithms.COCKTAILSHAKER);
    }

    private enum SortAlgorithms {
        INSERTION, BUBBLE, MAXSELECTION, MINSELECTION, MERGE, COCKTAILSHAKER
    }

}
