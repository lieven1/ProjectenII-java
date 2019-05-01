/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.activiteit;

import controllers.ActiviteitenController;
import domain.Activiteit;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Steve
 */
public class BeheerActiviteitenGUIController implements Initializable {

    @FXML
    private Button clearButton;
    @FXML
    private TextField filterTextField;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker untilDatePicker;
    @FXML
    private CheckBox checkAllPicker;
    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<ObservableValue<Activiteit>> tableView;
    @FXML
    private TableColumn<ObservableValue<Activiteit>, String> checkColumn;
    @FXML
    private TableColumn<ObservableValue<Activiteit>, String> naamColumn;
    @FXML
    private TableColumn<ObservableValue<Activiteit>, String> typeColumn;
    @FXML
    private TableColumn<ObservableValue<Activiteit>, Integer> aantalDeelnemersColumn;
    @FXML
    private TableColumn<ObservableValue<Activiteit>, LocalDate> startDateColumn;
    @FXML
    private TableColumn<ObservableValue<Activiteit>, LocalDate> eindDateColumn;

    private ActiviteitenController controller;

    private ObservableList<ObservableValue<Activiteit>> activiteiten;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.setItems(activiteiten);
        
        naamColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getTitel()));
        typeColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getType()));
        aantalDeelnemersColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getDeelnemers().size()));
        startDateColumn.setCellValueFactory(p -> new SimpleObjectProperty(calendarToLocalDate(p.getValue().getValue().getStartDatum())));
        eindDateColumn.setCellValueFactory(p -> new SimpleObjectProperty(calendarToLocalDate(p.getValue().getValue().getEindDatum())));
    }

    public void setController(ActiviteitenController ac) {
        controller = ac;
        activiteiten = controller.getActiviteiten();
    }

    public void clear() {
        filterTextField.clear();
        fromDatePicker.setValue(null);
        untilDatePicker.setValue(null);
        activiteiten = controller.getActiviteiten();
    }

    public void search() {
        activiteiten = controller.getActiviteiten();
        filterText();
        filterFrom();
        filterUntil();
    }

    public void checkUncheckAll() {

    }

    public void create() {

    }

    public void delete() {

    }

    private void filterText() {
        activiteiten = activiteiten.stream().filter(obsObj -> obsObj.getValue().getTitel().contains(filterTextField.getText()) || obsObj.getValue().getType().contains(filterTextField.getText())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private void filterFrom() {
        if (fromDatePicker.getValue() != null) {
            activiteiten = activiteiten.stream().filter(obsObj -> calendarToLocalDate(obsObj.getValue().getStartDatum()).isAfter(fromDatePicker.getValue())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }
    }

    private void filterUntil() {
        if (fromDatePicker.getValue() != null) {
            activiteiten = activiteiten.stream().filter(obsObj -> calendarToLocalDate(obsObj.getValue().getStartDatum()).isBefore(untilDatePicker.getValue())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }
    }

    private LocalDate calendarToLocalDate(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDate.ofInstant(calendar.toInstant(), zid);
    }

}
