package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class SelectContries extends Application {
    String[] Countries={"Africa","America"};


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root=new BorderPane();
        ListView<String> lv=new ListView<>(FXCollections.observableArrayList(Countries));
        FlowPane flowPane=new FlowPane();

        lv.getSelectionModel().selectedItemProperty().addListener(ov -> {
            flowPane.getChildren().clear();
             for(Integer i:lv.getSelectionModel().getSelectedIndices()){
                    flowPane.getChildren().addAll();
            }
        });


        root.setLeft(lv);
        root.setCenter(flowPane);
        stage.setTitle("List View");
        stage.setScene(new Scene(root,500,500));
        stage.show();

    }
}
