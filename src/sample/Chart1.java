package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.stage.Stage;

public class Chart1 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        NumberAxis xAxis = new NumberAxis(0, 100, 0);
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis   (0, 100, 0);
        yAxis.setLabel("TotalDeaths");
        LineChart linechart = new LineChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("Data");
        series.getData().add(new XYChart.Data(0,0));

        linechart.getData().add(series);


        Group root = new Group(linechart);
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Line Chart");
        stage.setScene(scene);
        stage.show();

    }

    }


