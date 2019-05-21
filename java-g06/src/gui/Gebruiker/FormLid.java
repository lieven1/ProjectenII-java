package gui.Gebruiker;

import domain.controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import domain.GebruikerModels.Geslacht;
import domain.GebruikerModels.Gradatie;
import domain.GebruikerModels.ProefGebruiker;
import domain.GebruikerModels.TypeGebruiker;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class FormLid extends GebruikerForm {

    private GebruikerController gc;
    private AGebruiker oldGeb;

    @FXML
    TextField txfGebruikersnaam, txfNaam, txfVoornaam, txfRijksregisternummer, txfGeboorteplaats, txfLand, txfPostcode, txfStad, txfStraat, txfHuisnummer, txfTelefoonnummer, txfGsmnummer, txfEmail, txfEmailOuders;
    @FXML
    ChoiceBox cbGeslacht, cbGraad, cbLesformule, cbTypeGebruiker;
    @FXML
    DatePicker dpGeboortedatum, dpInschrijvingsdatum;
    @FXML
    Button btnOpslaan, btnVerwijder;
    @FXML
    MenuButton btnNieuw;
    @FXML
    Label lblFout;

    public FormLid(GebruikerController gc) {
        this.gc = gc;
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("FormLid.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        cbGeslacht.setItems(FXCollections.observableArrayList(Geslacht.values()));
        cbGraad.setItems(FXCollections.observableArrayList(Gradatie.values()));
        cbTypeGebruiker.setItems(FXCollections.observableArrayList(TypeGebruiker.Lid, TypeGebruiker.Proefgebruiker));
        cbTypeGebruiker.setValue(TypeGebruiker.Lid);
        btnOpslaan.setText("Toevoegen");
        MenuItem nieuwProefLid = new MenuItem("Proeflid");
        MenuItem nieuwLid = new MenuItem("Gebruiker");
        btnNieuw.getItems().addAll(nieuwProefLid, nieuwLid);

        btnVerwijder.setDisable(true);
        lblFout.setText("");
        dpInschrijvingsdatum.setValue(LocalDate.now());
        dpGeboortedatum.setValue(LocalDate.now().minusYears(6));
        dpGeboortedatum.setEditable(false);

        // Buttons
        nieuwProefLid.setOnAction((ActionEvent t) -> {
            gc.setCurrentGebruiker(null);
            gc.setCurrentTypeGebruiker(TypeGebruiker.Proefgebruiker);
        });
        nieuwLid.setOnAction((ActionEvent t) -> {
            gc.setCurrentGebruiker(null);
            gc.setCurrentTypeGebruiker(TypeGebruiker.Proefgebruiker);
            gc.setCurrentTypeGebruiker(TypeGebruiker.Lid);
        });
        btnOpslaan.setOnAction((ActionEvent t) -> {
            this.saveGebruiker();
        });
        btnVerwijder.setOnAction((ActionEvent t) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Verwijder gebruiker");
            alert.setHeaderText("Verwijderen?");
            alert.setContentText("Bent u zeker dat u deze gebruiker wenst te verwijderen?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                gc.delete();
                gc.setCurrentTypeGebruiker(TypeGebruiker.Lid);
            }
        });

        cbTypeGebruiker.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            gc.setCurrentTypeGebruiker((TypeGebruiker) cbTypeGebruiker.getValue());
        });
        
        txfRijksregisternummer.textProperty().addListener((arg0, oldValue, newValue) -> {
            if(newValue.matches("^\\d{11}$")){
                switch(Integer.valueOf(txfRijksregisternummer.getText().substring(6, 9))%2){
                    case 1: cbGeslacht.setValue(Geslacht.Vrouw);
                    case 2: cbGeslacht.setValue(Geslacht.Man);
                }
                int year = Integer.valueOf(txfRijksregisternummer.getText().substring(0, 2));
                if (year + 2000 > Calendar.getInstance().get(Calendar.YEAR) -5) {
                    year = year + 1900;
                }
                else {
                    year = year + 2000;
                }
                dpGeboortedatum.setValue(LocalDate.of(year, Integer.valueOf(txfRijksregisternummer.getText().substring(2, 4)), Integer.valueOf(txfRijksregisternummer.getText().substring(4, 6))));
            }
        });
    }

    @Override
    public void loadGebruiker(AGebruiker gebruiker) {
        txfGebruikersnaam.setDisable(true);
        btnOpslaan.setText("Bijwerken");
        oldGeb = gebruiker;
        btnVerwijder.setDisable(false);
        switch (gebruiker.getType()) {
            case Proefgebruiker:
                ProefGebruiker tempGeb = (ProefGebruiker) gebruiker;
                txfGebruikersnaam.setText(tempGeb.getGebruikersnaam());
                dpInschrijvingsdatum.setValue(LocalDate.ofInstant(tempGeb.getInschrijvingsDatum().toInstant(), ZoneId.systemDefault()));
                txfNaam.setText(tempGeb.getNaam());
                txfVoornaam.setText(tempGeb.getVoornaam());
                txfTelefoonnummer.setText(tempGeb.getTelefoonnummer());
                txfEmail.setText(tempGeb.getEmail());
                break;
            case Beheerder:
                // Beheerder logic
                break;
            default:
                Gebruiker g = (Gebruiker) gebruiker;
                txfGebruikersnaam.setText(g.getGebruikersnaam());
                txfNaam.setText(g.getNaam());
                txfVoornaam.setText(g.getVoornaam());
                txfRijksregisternummer.setText(g.getRijksregisternummer());
                cbGeslacht.setValue(g.getGeslacht());
                dpGeboortedatum.setValue(LocalDate.ofInstant(g.getGeboorteDatum().toInstant(), ZoneId.systemDefault()));
                txfGeboorteplaats.setText(g.getGeboorteplaats());
                txfLand.setText(g.getAdres().getLand());
                txfPostcode.setText(g.getAdres().getPostcode());
                txfStad.setText(g.getAdres().getStad());
                txfStraat.setText(g.getAdres().getStraat());
                txfHuisnummer.setText(g.getAdres().getNummer());
                txfTelefoonnummer.setText(g.getTelefoonnummer());
                txfGsmnummer.setText(g.getGsmnummer());
                txfEmail.setText(g.getEmail());
                txfEmailOuders.setText(g.getEmailOuders());
                cbGraad.setValue(g.getGraad());
                dpInschrijvingsdatum.setValue(LocalDate.ofInstant(g.getInschrijvingsDatum().toInstant(), ZoneId.systemDefault()));
                break;
        }
    }

    public void saveGebruiker() {
        try {
            Gebruiker geb = new Gebruiker(
                    txfGebruikersnaam.getText(),
                    txfRijksregisternummer.getText(),
                    new GregorianCalendar(dpInschrijvingsdatum.getValue().getYear(), dpInschrijvingsdatum.getValue().getMonthValue() - 1, dpInschrijvingsdatum.getValue().getDayOfMonth()),
                    txfNaam.getText(),
                    txfVoornaam.getText(),
                    (Geslacht) cbGeslacht.getValue(),
                    new GregorianCalendar(dpGeboortedatum.getValue().getYear(), dpGeboortedatum.getValue().getMonthValue() - 1, dpGeboortedatum.getValue().getDayOfMonth()),
                    txfGeboorteplaats.getText(),
                    txfTelefoonnummer.getText(),
                    txfGsmnummer.getText(),
                    txfEmail.getText(),
                    txfEmailOuders.getText(),
                    txfLand.getText(), txfPostcode.getText(), txfStad.getText(), txfStraat.getText(), txfHuisnummer.getText(),
                    (Gradatie) cbGraad.getValue(),
                    TypeGebruiker.Lid,
                    null
            );

            if (oldGeb == null) {
                gc.create(geb);
                oldGeb = geb;
                btnVerwijder.setDisable(false);
            } else {
                gc.modify(geb);
                oldGeb = geb;
            }
            lblFout.setText("");
        } catch (Exception e) {
            lblFout.setText(e.getMessage());
            lblFout.setVisible(true);
        }
    }

    public void deleteGebruiker() {
        gc.delete();
    }
}
