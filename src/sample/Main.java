package sample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Main extends Application {
    TableView<CovidData> table;



    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        TableColumn<CovidData, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setMinWidth(100);
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<CovidData, String> totalCasesColumn = new TableColumn<>("TotalCases");
        totalCasesColumn.setMinWidth(100);
        totalCasesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCases"));

        TableColumn<CovidData, Integer> newCasesColumn = new TableColumn<>("NewCases");
        newCasesColumn.setMinWidth(100);
        newCasesColumn.setCellValueFactory(new PropertyValueFactory<>("newCases"));

        TableColumn<CovidData, Integer> totalDeathsColumn = new TableColumn<>("TotalDeaths");
        totalDeathsColumn.setMinWidth(100);
        totalDeathsColumn.setCellValueFactory(new PropertyValueFactory<>("totalDeaths"));

        TableColumn<CovidData, Integer> newDeathsColumn = new TableColumn<>("NewDeaths");
        newDeathsColumn.setMinWidth(100);
        newDeathsColumn.setCellValueFactory(new PropertyValueFactory<>("newDeaths"));

        TableColumn<CovidData, Integer> populationColumn = new TableColumn<>("Population");
        populationColumn.setMinWidth(100);
        populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));


        TableColumn<CovidData, Double> mortalityColumn = new TableColumn<>("Mortality");
        mortalityColumn.setMinWidth(100);
        mortalityColumn.setCellValueFactory(new PropertyValueFactory<>("mortality"));


        TableColumn<CovidData, Double> attackRateColumn = new TableColumn<>("AttackRate");
        attackRateColumn.setMinWidth(100);
        attackRateColumn.setCellValueFactory(new PropertyValueFactory<>("attackRate"));


        TableColumn<CovidData, String> totalDateColumn = new TableColumn<>("Date");
        totalDateColumn.setMinWidth(100);
        totalDateColumn.setCellValueFactory(new PropertyValueFactory<>("totalDate"));

        table = new TableView<>();
        table.getColumns().addAll(countryColumn, totalCasesColumn, newCasesColumn, totalDeathsColumn, newDeathsColumn, populationColumn, mortalityColumn, attackRateColumn, totalDateColumn);
        HBox root = new HBox();
        Button button = new Button("GET DATA");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Get button action listener");
                ParseXML xmlData = new ParseXML();
                List<CovidData> covidDataListXML = new ArrayList<>();
                List<XmlData> covidDataInXmlFormat = new ArrayList<>();
                String url = "https://opendata.ecdc.europa.eu/covid19/casedistribution/xml/";
                try {
                    covidDataInXmlFormat = xmlData.parseXml(url);
                    System.out.println("After xml read");
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                for(XmlData element : covidDataInXmlFormat){
                    CovidData covidData = new CovidData();
                    covidData.setCountry(element.getCountriesAndTerritories());
                    System.out.println(element.getCountriesAndTerritories());
                    covidData.setNewCases(Integer.parseInt(element.getCases()));
                    covidData.setNewDeaths(Integer.parseInt(element.getDeaths()));
                    if(!element.getPopData2018().equals("")) {
                        covidData.setPopulation(Integer.parseInt(element.getPopData2018()));
                    }else{
                        covidData.setPopulation(0);
                    }
                    covidData.setDay(Integer.parseInt(element.getDay()));
                    covidData.setMonth(Integer.parseInt(element.getMonth()));
                    covidData.setYear(Integer.parseInt(element.getYear()));
                    covidData.setTotalDate(new String(element.getDay() + "/" + element.getMonth() + "/" + element.getYear()));
                    long myMillis = covidData.getDay() + covidData.getMonth() * 31 + covidData.getYear() * 1000;
                    covidData.setMilliseconds(myMillis);
                    covidDataListXML.add(covidData);
                }
                Collections.sort(covidDataListXML, new Comparator<CovidData>() {
                    @Override
                    public int compare(CovidData other1, CovidData other2) {
                        String x1 = other1.getCountry();
                        String x2 = other2.getCountry();
                        int sComp = x1.compareTo(x2);

                        if (sComp != 0) {
                            return sComp;
                        }

                        x1 = String.valueOf(other1.getMilliseconds());
                        x2 = String.valueOf(other2.getMilliseconds());
                        return x1.compareTo(x2);
                    }
                });
                int totalDeath = covidDataListXML.get(0).getNewDeaths();
                covidDataListXML.get(0).setTotalDeaths(totalDeath);
                for(int i = 0; i < covidDataListXML.size(); i++){
                    if(i != covidDataListXML.size() - 1) {
                        String currentCountry = covidDataListXML.get(i).getCountry();
                        String nextCountry = covidDataListXML.get(i + 1).getCountry();
                        if (currentCountry.equals(nextCountry)) {
                            totalDeath = totalDeath + covidDataListXML.get(i + 1).getNewDeaths();
                            covidDataListXML.get(i+1).setTotalDeaths(totalDeath);
                        } else {
                            totalDeath = 0;
                        }
                    }
                }

                int totalCase = covidDataListXML.get(0).getNewCases();
                covidDataListXML.get(0).setTotalCases(totalCase);
                for(int i = 0; i < covidDataListXML.size(); i++){
                    if(i != covidDataListXML.size() - 1) {
                        String currentCountry = covidDataListXML.get(i).getCountry();
                        String nextCountry = covidDataListXML.get(i + 1).getCountry();
                        if (currentCountry.equals(nextCountry)) {
                            totalCase = totalCase + covidDataListXML.get(i + 1).getNewCases();
                            covidDataListXML.get(i+1).setTotalCases(totalCase);
                        } else {
                            totalCase = 0;
                        }
                    }
                }
                double mortRate = 0.0;
                for(int i = 0; i < covidDataListXML.size(); i++) {
                    if(covidDataListXML.get(i).getTotalCases() != 0){
                        mortRate = (double)covidDataListXML.get(i).getTotalDeaths() / covidDataListXML.get(i).getTotalCases();
                    }else{
                        mortRate = 0;
                    }

                    covidDataListXML.get(i).setMortality(mortRate);
                }

                ObservableList covidDataTable = FXCollections.observableList(covidDataListXML);
                table.setItems(covidDataTable);
            }
        });

        button.setTextFill(Color.RED);
        button.setFont(Font.font("Times New Roman", FontWeight.BOLD, 10));

        root.getChildren().addAll(button, table);
        root.setAlignment(Pos.TOP_RIGHT);
        primaryStage.setTitle("Covid19 Visual Data");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

}


