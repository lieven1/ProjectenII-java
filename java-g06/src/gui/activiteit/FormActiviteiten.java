/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.activiteit;

import domain.Activiteit;
import domain.LesmateriaalModels.Lesmateriaal;
import domain.controllers.ActiviteitenController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Steve
 */
public class FormActiviteiten extends ScrollPane implements PropertyChangeListener, Initializable {

    @FXML
    private Button btnOpslaan;
    @FXML
    private Button btnVerwijder;
    @FXML
    private TextField txfType;
    @FXML
    private TextField txfTitel;
    @FXML
    private Label lblFout;
    @FXML
    private DatePicker dpEinddatum;
    @FXML
    private DatePicker dpStartdatum;
    @FXML
    private TextField txfMaxAantalDeelnemers;
    @FXML
    private Button deelnemersButton;
    @FXML
    private TextField txfAantalDeelnemers;
    @FXML
    private ListView<?> alleGebruikersListView;
    @FXML
    private Button begeleidersButton;
    @FXML
    private ListView<?> gekozenGebruikersListView;
    @FXML
    private Button addGebruikerButton;
    @FXML
    private Button removeGebruikerButton;

    private ActiviteitenController controller;
    private Activiteit currentActiviteit;

    public FormActiviteiten(ActiviteitenController contr) {
        controller = contr;
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("formActiviteiten.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnVerwijder.setDisable(true);
    }

    @FXML
    private void slaActiviteitOp(ActionEvent event) {
    }

    @FXML
    private void verwijderActiviteit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Verwijder activiteit");
        alert.setHeaderText("Verwijderen?");
        alert.setContentText("Bent u zeker dat u deze activiteit wenst te verwijderen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            controller.delete();
            controller.setCurrentActiviteit(null);
        }
    }

    @FXML
    private void nieuweActiviteit(ActionEvent event) {
    }

    @FXML
    private void addGebruiker(ActionEvent event) {
    }

    @FXML
    private void removeGebruiker(ActionEvent event) {
    }

    private void loadActiviteit(Activiteit act) {
        btnOpslaan.setText("Bijwerken");
        btnVerwijder.setDisable(false);

        currentActiviteit = act;

        setDetailData(act.getTitel(), act.getType(), calendarToLocalDate(act.getStartDatum()), calendarToLocalDate(act.getEindDatum()), Integer.toString(act.getAantalDeelnemers()), Integer.toString(act.getMaxAantalDeelnemers()));
    }

    private void setDetailData(String titel, String type, LocalDate startDatum, LocalDate eindDatum, String aantalDeelnemers, String maxAantalDeelnemers) {
        txfTitel.setText(titel);
        txfType.setText(type);
        dpStartdatum.setValue(startDatum);
        dpEinddatum.setValue(eindDatum);
        txfAantalDeelnemers.setText(aantalDeelnemers);
        txfMaxAantalDeelnemers.setText(maxAantalDeelnemers);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("currentActiviteit") && evt.getNewValue() != null) {
            this.loadActiviteit((Activiteit) evt.getNewValue());
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
