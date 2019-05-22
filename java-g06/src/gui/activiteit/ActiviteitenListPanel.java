/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.activiteit;

import domain.controllers.ActiviteitenController;
import domain.Activiteit;
import gui.ActiviteitenListPanelGenerator;
import java.text.DateFormat;
import java.util.Calendar;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Steve
 */
public class ActiviteitenListPanel extends VBox {

    private ActiviteitenController controller;
    private TableView<Activiteit> activiteitenTableView;
    private ActiviteitenListPanelGenerator generator;

    public ActiviteitenListPanel(ActiviteitenController controller) {
        this.controller = controller;
        generator = new ActiviteitenListPanelGenerator();

        this.getChildren().addAll(createFilterPane(), new Separator(), generator.createTitleLabel("Activiteiten"), createTableView());
    }

    private VBox createFilterPane() {

        VBox filterPane = generator.createEmptyFilterPane();
        filterPane.setPadding(new Insets(10));
        filterPane.setSpacing(10);

        TextField naamField = generator.createTextField("Naam");
        TextField typeField = generator.createTextField("Type");
        HBox textFieldBox = new HBox(naamField, typeField);
        textFieldBox.setSpacing(30);

        DatePicker fromPicker = generator.createDatePicker("Van...");
        DatePicker untilPicker = generator.createDatePicker("Tot...");
        HBox datePickerBox = new HBox(fromPicker, untilPicker);
        datePickerBox.setSpacing(30);

        Button filterButton = generator.createFilterButton();
        Button clearButton = generator.createClearButton();
        HBox buttonBox = new HBox(clearButton, filterButton);
        buttonBox.setSpacing(30);

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
            controller.veranderFilter(naamField.getText(), typeField.getText(), fromPicker.getValue(), untilPicker.getValue());
        });

        filterPane.getChildren().addAll(textFieldBox, datePickerBox, buttonBox);
        return filterPane;
    }

    private TableView<Activiteit> createTableView() {
        activiteitenTableView = new TableView<>();
        activiteitenTableView.setEditable(false);
        TableColumn<Activiteit, String> naamColumn = new TableColumn<>("Naam");
        TableColumn<Activiteit, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Activiteit, Calendar> startDatumColumn = new TableColumn<>("Startdatum");
        TableColumn<Activiteit, Calendar> eindDatumColumn = new TableColumn<>("Einddatum");
        TableColumn<Activiteit, String> aantalDeelnemersColumn = new TableColumn<>("Deelnemers");

        naamColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDatumColumn.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        startDatumColumn.setCellFactory(p -> {
            return new CalenderCell();
        });
        eindDatumColumn.setCellValueFactory(new PropertyValueFactory<>("eindDatum"));
        eindDatumColumn.setCellFactory((p) -> {
            return new CalenderCell();
        });
        aantalDeelnemersColumn.setCellValueFactory(cd -> {              
            Activiteit act = (Activiteit) cd.getValue();
            return new SimpleStringProperty(act.getAantalDeelnemers() + " / " + act.getMaxAantalDeelnemers());
        });

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
