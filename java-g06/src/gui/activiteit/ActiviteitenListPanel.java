/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.activiteit;

import controllers.ActiviteitenController;
import controllers.GebruikerController;
import domain.Activiteit;
import domain.GebruikerModels.AGebruiker;
import gui.PanelGenerator;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author Steve
 */
public class ActiviteitenListPanel extends VBox {

    private ActiviteitenController controller;
    private TableView<Activiteit> activiteitenTableView;
    private PanelGenerator generator;

    public ActiviteitenListPanel(ActiviteitenController controller) {
        this.controller = controller;
        generator = new PanelGenerator();

        this.getChildren().addAll(createFilterPane(), new Separator(), generator.createTitleLabel("Activiteiten"), createTableView());
    }

    private VBox createFilterPane() {

        VBox filterPane = generator.createEmptyFilterPane();

        TextField naamField = generator.createTextField("Naam");
        TextField typeField = generator.createTextField("Type");
        HBox textFieldBox = new HBox(naamField, typeField);

        DatePicker fromPicker = generator.createDatePicker("Van...");
        DatePicker untilPicker = generator.createDatePicker("Tot...");
        HBox datePickerBox = new HBox(fromPicker, untilPicker);

        Button filterButton = generator.createFilterButton();
        Button clearButton = generator.createClearButton();
        HBox buttonBox = new HBox(clearButton, filterButton);

        // Use the filter
        filterButton.setOnAction((ActionEvent t) -> {
            controller.veranderFilter(naamField.getText(), typeField.getText(), fromPicker.getValue(), untilPicker.getValue());
        });

        clearButton.setOnAction((ActionEvent t) -> {
            naamField.setText("");
            typeField.setText("");
            fromPicker.setValue(null);
            untilPicker.setValue(null);
            activiteitenTableView.setItems(controller.getActiviteiten());
        });

        filterPane.getChildren().addAll(textFieldBox, datePickerBox, buttonBox);
        return filterPane;
    }

    private TableView<Activiteit> createTableView() {
        activiteitenTableView = new TableView<Activiteit>();
        activiteitenTableView.setEditable(false);
        TableColumn naamColumn = new TableColumn("Naam");
        TableColumn typeColumn = new TableColumn("Type");
        TableColumn startDatumColumn = new TableColumn("Startdatum");
        TableColumn eindDatumColumn = new TableColumn("Einddatum");
        TableColumn aantalDeelnemersColumn = new TableColumn("Aantal deelnemers");

        naamColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, String>("titel"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, String>("type"));
        startDatumColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, Calendar>("startDatum"));
        startDatumColumn.setCellFactory(p -> {
            return new CalenderCell();
        });
        eindDatumColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, Calendar>("eindDatum"));
        eindDatumColumn.setCellFactory((p) -> {
            return new CalenderCell();
        });
        aantalDeelnemersColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, Integer>("maxAantalDeelnemers"));

        activiteitenTableView.getColumns().addAll(naamColumn, typeColumn, aantalDeelnemersColumn, startDatumColumn, eindDatumColumn);
        activiteitenTableView.setItems(controller.getActiviteiten());
        activiteitenTableView.setPrefHeight(1080);

        activiteitenTableView.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            controller.setCurrentActiviteit(nv);
        });

        return activiteitenTableView;
    }

    private class CalenderCell extends TableCell<Activiteit, Calendar> {

        private DateFormat dateFormat = DateFormat.getDateInstance();

        @Override
        protected void updateItem(Calendar item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null) {
                setText(null);
            } else {
                setText(dateFormat.format(item.getTime()));
            }
        }
    }

}