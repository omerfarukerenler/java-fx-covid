package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static javafx.collections.FXCollections.observableArrayList;

public class Barchart extends Application {



    @Override
    public void start(Stage stage) throws Exception {


        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Europe","America","Asia","Oceania","Africa")));
        xAxis.setLabel("Country");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("TotalDeath");


        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("BarChart Data");


        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Europe");
        series1.getData().add(new XYChart.Data<>("",0));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("America");
        series2.getData().add(new XYChart.Data<>("",0));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Asia");
        series3.getData().add(new XYChart.Data<>("",0));

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("Oceania");
        series4.getData().add(new XYChart.Data<>("",0));

        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("Africa");
        series5.getData().add(new XYChart.Data<>("",0));

        barChart.getData().addAll(series1, series2, series3,series4,series5);

        Group root = new Group(barChart);
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Bar Chart");
        stage.setScene(scene);
        stage.show();
    }
}
