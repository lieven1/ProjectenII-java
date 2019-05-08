package gui.Gebruiker;

import controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import domain.GebruikerModels.Gradatie;
import domain.GebruikerModels.TypeGebruiker;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class GebruikerBeheerListPanel extends VBox {

    GebruikerController gc;
    TableView gebruikerList;

    public GebruikerBeheerListPanel(GebruikerController gc) {
        this.gc = gc;

        // VBox Constraints
        this.setPrefWidth(330);
        this.setPrefHeight(1080);
        this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #999;");

        Label lblGebruikersTitle = new Label("Gebruikers");
        lblGebruikersTitle.setFont(new Font("Arial Black", 16.0));
        lblGebruikersTitle.setTextFill(Paint.valueOf("#393980"));
        lblGebruikersTitle.setStyle("-fx-label-padding: 0 0 0 10");

        this.getChildren().addAll(createFilterPane(), new Separator(), lblGebruikersTitle, createGebruikerList());
    }

    private AnchorPane createFilterPane() {
        AnchorPane filterPane = new AnchorPane();
        filterPane.setScaleX(1);
        ImageView imgLogo = new ImageView();
        try {
            imgLogo.setImage(new Image((new File("src/resources/logoTemp.png")).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        imgLogo.setLayoutX(10);
        imgLogo.setLayoutY(10);
        Label lblFilterTitle = new Label("Filter");
        lblFilterTitle.setFont(new Font("Arial Black", 16.0));
        lblFilterTitle.setTextFill(Paint.valueOf("#393980"));
        lblFilterTitle.setLayoutX(10);
        lblFilterTitle.setLayoutY(90);
        Label lblNaamFilter = new Label("Naam");
        lblNaamFilter.setLayoutX(15);
        lblNaamFilter.setLayoutY(111);
        Label lblVoornaamFilter = new Label("Voornaam");
        lblVoornaamFilter.setLayoutX(175);
        lblVoornaamFilter.setLayoutY(111);
        TextField txfNaamFilter = new TextField();
        txfNaamFilter.setLayoutX(10);
        txfNaamFilter.setLayoutY(130);
        txfNaamFilter.setPrefSize(130, 25);
        TextField txfVoornaamFilter = new TextField();
        txfVoornaamFilter.setLayoutX(170);
        txfVoornaamFilter.setLayoutY(130);
        txfVoornaamFilter.setPrefSize(130, 25);
        CheckBox cbLid = new CheckBox("Lid");
        cbLid.setLayoutX(20);
        cbLid.setLayoutY(174);
        cbLid.setSelected(true);
        CheckBox cbProeflid = new CheckBox("Proeflid");
        cbProeflid.setLayoutX(77);
        cbProeflid.setLayoutY(174);
        cbProeflid.setSelected(true);
        Button btnFilter = new Button("Filter");
        btnFilter.getStyleClass().addAll("lg", "info");
        btnFilter.setPrefWidth(233);
        btnFilter.setLayoutX(67);
        btnFilter.setLayoutY(210);
        Button btnVerwFilter = new Button("X");
        btnVerwFilter.getStyleClass().addAll("lg", "danger");
        btnVerwFilter.setPrefWidth(50);
        btnVerwFilter.setLayoutX(10);
        btnVerwFilter.setLayoutY(210);

        // Use the filter
        btnFilter.setOnAction((ActionEvent t) -> {
            String naam = txfNaamFilter.getText();
            String voornaam = txfVoornaamFilter.getText();
            gc.veranderFilter(naam, voornaam, cbProeflid.selectedProperty().getValue(), cbLid.selectedProperty().getValue());
        });

        btnVerwFilter.setOnAction((ActionEvent t) -> {
            txfNaamFilter.setText("");
            txfVoornaamFilter.setText("");
            gebruikerList.setItems(gc.getGebruikerLijst());
        });

        filterPane.getChildren().addAll(lblFilterTitle, lblNaamFilter, lblVoornaamFilter, txfNaamFilter, txfVoornaamFilter, cbLid, cbProeflid, btnFilter, btnVerwFilter, imgLogo);
        return filterPane;
    }

    private TableView createGebruikerList() {
        gebruikerList = new TableView<AGebruiker>();
        gebruikerList.setEditable(false);
        TableColumn graadColumn = new TableColumn("Graad");
        TableColumn firstNameColumn = new TableColumn("Voornaam");
        TableColumn lastNameColumn = new TableColumn("Achternaam");
        TableColumn typeColumn = new TableColumn("Type");
        graadColumn.setCellValueFactory(new PropertyValueFactory<Gebruiker, Gradatie>("graad"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<AGebruiker, String>("voornaam"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<AGebruiker, String>("naam"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<AGebruiker, TypeGebruiker>("type"));
        gebruikerList.getColumns().addAll(lastNameColumn, firstNameColumn, graadColumn, typeColumn);
        gebruikerList.setItems(gc.getGebruikerLijst());
        gebruikerList.setPrefHeight(1080);

        gebruikerList.getSelectionModel().selectedItemProperty().addListener((ObservableValue observableValue, Object oldValue, Object newValue) -> {
            gc.setCurrentTypeGebruiker(((AGebruiker) newValue).getType());
            gc.setCurrentGebruiker((AGebruiker) newValue);
        });

        return gebruikerList;
    }
}
