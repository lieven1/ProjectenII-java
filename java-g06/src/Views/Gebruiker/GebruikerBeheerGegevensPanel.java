package Views.Gebruiker;

import Domain.GebruikerModels.AGebruiker;
import Domain.GebruikerModels.Gebruiker;
import Domain.GebruikerModels.Geslacht;
import Domain.GebruikerModels.Gradatie;
import Domain.GebruikerModels.Lesformule;
import Domain.GebruikerModels.ProefGebruiker;
import Domain.GebruikerModels.TypeGebruiker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GebruikerBeheerGegevensPanel extends VBox{
    
    private final GebruikerBeheerPanelController controller;
    private TypeGebruiker formType;
    private GridPane formPane;
    
    // Form Controls
    private final Label lblGebruikersnaam, lblRijksregisternummer, lblNaam, lblVoornaam, lblGeboorteplaats, lblTelefoonnummer, lblGsmnummer, lblEmail, lblEmailOuders, lblLand, lblPostcode,
            lblStad, lblStraat, lblNummer, lblGeslacht, lblGraad, lblLesformule, lblInschrijvingsdatum, lblGeboortedatum;
    private final TextField txfGebruikersnaam, txfRijksregisternummer, txfNaam, txfVoornaam, txfGeboorteplaats, txfTelefoonnummer, txfGsmnummer, txfEmail, txfEmailOuders, txfLand, txfPostcode,
            txfStad, txfStraat, txfNummer;
    private final ChoiceBox choiceGeslacht, choiceGraad, choiceLesformule;
    private final DatePicker dpInschrijvingsdatum, dpGeboortedatum;
    
    public GebruikerBeheerGegevensPanel(GebruikerBeheerPanelController controller){
        this.controller = controller;
        this.setLayoutX(320);
        formPane = new GridPane();
        formPane.setAlignment(Pos.CENTER);
        formPane.setMinWidth(480);
        formPane.setHgap(10);
        formPane.setVgap(5);
        ColumnConstraints c = new ColumnConstraints();
        c.setMinWidth(240);
        formPane.getColumnConstraints().add(c);
        this.getChildren().addAll(createGebruikerMenu(), new GridPane());
      
        // Formelements init
        lblGebruikersnaam = new Label("Gebruikersnaam");
        txfGebruikersnaam = new TextField();
        txfGebruikersnaam.maxWidth(Double.MAX_VALUE);
        lblRijksregisternummer = new Label("Rijksregisternummer");
        txfRijksregisternummer = new TextField();
        lblNaam = new Label("Naam");
        txfNaam = new TextField();
        lblVoornaam = new Label("Voornaam");
        txfVoornaam = new TextField();
        lblGeboorteplaats = new Label("Geboorteplaats");
        txfGeboorteplaats = new TextField();
        lblTelefoonnummer = new Label("Telefoonnummer");
        txfTelefoonnummer = new TextField();
        lblGsmnummer = new Label("Gsmnummer");
        txfGsmnummer = new TextField();
        lblEmail = new Label("Email");
        txfEmail = new TextField();
        lblEmailOuders = new Label("EmailOuders");
        txfEmailOuders = new TextField();
        lblLand = new Label("Land");
        txfLand = new TextField();
        lblPostcode = new Label("Postcode");
        txfPostcode = new TextField();
        lblStad = new Label("Stad");
        txfStad = new TextField();
        lblStraat = new Label("Straat");
        txfStraat = new TextField();
        lblNummer = new Label("Nummer");
        txfNummer = new TextField();
        lblGeslacht = new Label("Geslacht");
        choiceGeslacht = new ChoiceBox<Geslacht>();
        choiceGeslacht.getItems().setAll(FXCollections.observableArrayList(Geslacht.values()));
        lblGraad = new Label("Graad");
        choiceGraad = new ChoiceBox<Gradatie>();
        choiceGraad.getItems().setAll(FXCollections.observableArrayList(Gradatie.values()));
        lblLesformule = new Label("Lesformule");
        choiceLesformule = new ChoiceBox<Lesformule>();
        choiceLesformule.getItems().setAll(FXCollections.observableArrayList(new ArrayList<>()));
        lblInschrijvingsdatum = new Label("Inschrijvingsdatum");
        dpInschrijvingsdatum = new DatePicker();
        lblGeboortedatum = new Label("Geboortedatum");
        dpGeboortedatum = new DatePicker();
        createForm(TypeGebruiker.Lid);
        formType = TypeGebruiker.Lid;
    }

    private MenuBar createGebruikerMenu(){
        // GegevensPanel
        MenuBar gebruikerMenu = new MenuBar();
        gebruikerMenu.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: #999;");
        Menu menuGebruikerToevoegen = new Menu("+ Nieuw");
        MenuItem nieuwTypeLid = new MenuItem("Lid");
        MenuItem nieuwTypeProeflid = new MenuItem("Proeflid");
        MenuItem nieuwTypeBeheerder = new MenuItem("Beheerder");
        menuGebruikerToevoegen.getItems().addAll(nieuwTypeLid, nieuwTypeProeflid, nieuwTypeBeheerder);
        Menu menuGebruikerVerwijderen = new Menu("- Verwijderen");
        menuGebruikerVerwijderen.getItems().add(new MenuItem());
        Menu menuGebruikerType = new Menu("Verander type");
        MenuItem veranderTypeLid = new MenuItem("Lid");
        MenuItem veranderTypeProeflid = new MenuItem("Proeflid");
        MenuItem veranderTypeBeheerder = new MenuItem("Beheerder");
        menuGebruikerType.getItems().addAll(veranderTypeLid, veranderTypeProeflid, veranderTypeBeheerder);
        gebruikerMenu.getMenus().addAll(menuGebruikerToevoegen, menuGebruikerVerwijderen, menuGebruikerType);
        // Menu EventHandler
        nieuwTypeLid.setOnAction((ActionEvent t) -> {createForm(TypeGebruiker.Lid);});
        nieuwTypeProeflid.setOnAction((ActionEvent t) -> {createForm(TypeGebruiker.Proefgebruiker);});
        nieuwTypeBeheerder.setOnAction((ActionEvent t) -> {createForm(TypeGebruiker.Beheerder);});
        menuGebruikerVerwijderen.showingProperty().addListener((observableValue, oldValue, newValue) -> {
            menuGebruikerVerwijderen.getItems().get(0).fire();
            beheerGebruiker(null);
        });
        veranderTypeLid.setOnAction((ActionEvent t) -> {createForm(TypeGebruiker.Lid);});
        veranderTypeProeflid.setOnAction((ActionEvent t) -> {createForm(TypeGebruiker.Proefgebruiker);});
        veranderTypeBeheerder.setOnAction((ActionEvent t) -> {createForm(TypeGebruiker.Beheerder);});
        return gebruikerMenu;
    }
    
    private void createForm(TypeGebruiker type){
        formPane.getChildren().clear();
        
        formType = type;
        // Reset fields
        txfGebruikersnaam.setText(null);
        txfRijksregisternummer.setText(null);
        txfNaam.setText(null);
        txfVoornaam.setText(null);
        txfGeboorteplaats.setText(null);
        txfTelefoonnummer.setText(null);
        txfGsmnummer.setText(null);
        txfEmail.setText(null);
        txfEmailOuders.setText(null);
        txfLand.setText(null);
        txfPostcode.setText(null);
        txfStad.setText(null);
        txfStraat.setText(null);
        txfNummer.setText(null);
        choiceGeslacht.getSelectionModel().clearSelection();
        choiceGraad.getSelectionModel().clearSelection();
        choiceLesformule.getSelectionModel().clearSelection();
        dpInschrijvingsdatum.setValue(LocalDate.now());
        dpGeboortedatum.setValue(LocalDate.now());
        
        switch(type){
            case Lid:
                formPane.add(txfGebruikersnaam, 1, 0);
                formPane.add(txfRijksregisternummer, 1, 1);
                formPane.add(txfNaam, 1, 2);
                formPane.add(txfVoornaam, 1, 3);
                formPane.add(txfGeboorteplaats, 1, 4);
                formPane.add(txfTelefoonnummer, 1, 5);
                formPane.add(txfGsmnummer, 1, 6);
                formPane.add(txfEmail, 1, 7);
                formPane.add(txfEmailOuders, 1, 8);
                formPane.add(txfLand, 1, 9);
                formPane.add(txfPostcode, 1, 10);
                formPane.add(txfStad, 1, 11);
                formPane.add(txfStraat, 1, 12);
                formPane.add(txfNummer, 1, 13);
                formPane.add(choiceGeslacht, 1, 14);
                formPane.add(choiceGraad, 1, 15);
                formPane.add(choiceLesformule, 1, 16);
                formPane.add(dpInschrijvingsdatum, 1, 17);
                formPane.add(dpGeboortedatum, 1, 18);
                formPane.add(lblGebruikersnaam, 0, 0);
                formPane.add(lblRijksregisternummer, 0, 1);
                formPane.add(lblNaam, 0, 2);
                formPane.add(lblVoornaam, 0, 3);
                formPane.add(lblGeboorteplaats, 0, 4);
                formPane.add(lblTelefoonnummer, 0, 5);
                formPane.add(lblGsmnummer, 0, 6);
                formPane.add(lblEmail, 0, 7);
                formPane.add(lblEmailOuders, 0, 8);
                formPane.add(lblLand, 0, 9);
                formPane.add(lblPostcode, 0, 10);
                formPane.add(lblStad, 0, 11);
                formPane.add(lblStraat, 0, 12);
                formPane.add(lblNummer, 0, 13);
                formPane.add(lblGeslacht, 0, 14);
                formPane.add(lblGraad, 0, 15);
                formPane.add(lblLesformule, 0, 16);
                formPane.add(lblInschrijvingsdatum, 0, 17);
                formPane.add(lblGeboortedatum, 0, 18);
                break;
            case Proefgebruiker:
                formPane.add(txfGebruikersnaam, 1, 0);
                formPane.add(txfNaam, 1, 1);
                formPane.add(txfVoornaam, 1, 2);
                formPane.add(txfTelefoonnummer, 1, 3);
                formPane.add(txfEmail, 1, 4);
                formPane.add(dpInschrijvingsdatum, 1, 5);
                formPane.add(lblGebruikersnaam, 0, 0);
                formPane.add(lblNaam, 0, 1);
                formPane.add(lblVoornaam, 0, 2);
                formPane.add(lblTelefoonnummer, 0, 3);
                formPane.add(lblEmail, 0, 4);
                formPane.add(lblInschrijvingsdatum, 0, 5);
                break;
            case Beheerder:
                break;
        }
        this.getChildren().set(1, formPane);
    }
    
    public void beheerGebruiker(AGebruiker gebruiker){
        if(gebruiker != null){
            if(gebruiker.getType() != formType)
                createForm(gebruiker.getType());
            switch(formType){
                case Lid:
                    Gebruiker temp = (Gebruiker) gebruiker;
                    txfGebruikersnaam.setText(temp.getGebruikersnaam());
                    txfRijksregisternummer.setText(temp.getRijksregisternummer());
                    txfNaam.setText(temp.getNaam());
                    txfVoornaam.setText(temp.getVoornaam());
                    txfGeboorteplaats.setText(temp.getGeboorteplaats());
                    txfTelefoonnummer.setText(temp.getTelefoonnummer());
                    txfGsmnummer.setText(temp.getGsmnummer());
                    txfEmail.setText(temp.getEmail());
                    txfEmailOuders.setText(temp.getEmailOuders());
                    txfLand.setText(temp.getAdres().getLand());
                    txfPostcode.setText(temp.getAdres().getPostcode());
                    txfStad.setText(temp.getAdres().getStad());
                    txfStraat.setText(temp.getAdres().getStraat());
                    txfNummer.setText(temp.getAdres().getNummer());
                    choiceGeslacht.getSelectionModel().select(temp.getGeslacht());
                    choiceGraad.getSelectionModel().select(temp.getGraad());
                    choiceLesformule.getSelectionModel().select(null);
                    dpInschrijvingsdatum.setValue(LocalDateTime.ofInstant(temp.getInschrijvingsDatum().toInstant(), ZoneId.systemDefault()).toLocalDate());
                    dpGeboortedatum.setValue(LocalDateTime.ofInstant(temp.getGeboorteDatum().toInstant(), ZoneId.systemDefault()).toLocalDate());
                    break;
                case Proefgebruiker:                    
                    ProefGebruiker tempP = (ProefGebruiker) gebruiker;
                    txfGebruikersnaam.setText(tempP.getGebruikersnaam());
                    txfNaam.setText(tempP.getNaam());
                    txfVoornaam.setText(tempP.getVoornaam());
                    txfTelefoonnummer.setText(tempP.getTelefoonnummer());
                    txfEmail.setText(tempP.getEmail());
                    dpInschrijvingsdatum.setValue(LocalDateTime.ofInstant(tempP.getInschrijvingsDatum().toInstant(), ZoneId.systemDefault()).toLocalDate());
                    break;
                case Beheerder:
                    break;
            }
        }else{
            if(formType != TypeGebruiker.Lid)
                createForm(TypeGebruiker.Lid);
        }
    }
}
