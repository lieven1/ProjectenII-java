package gui.Gebruiker;

import controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import domain.GebruikerModels.Gradatie;
import domain.GebruikerModels.TypeGebruiker;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class GebruikerBeheerListPanel extends VBox{
    GebruikerController gc;
    TableView gebruikerList;
    
    public GebruikerBeheerListPanel(GebruikerController gc){
        this.gc = gc;
        
        // VBox Constraints
        this.setPrefWidth(310);
        this.setPrefHeight(2000);
        this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #999;");
        
        Label lblGebruikersTitle = new Label("Gebruikers");
        lblGebruikersTitle.setFont(new Font("Arial Black", 16.0));
        lblGebruikersTitle.setTextFill(Paint.valueOf("#393980"));
        lblGebruikersTitle.setStyle("-fx-label-padding: 0 0 0 10");
        
        this.getChildren().addAll(createFilterPane(), new Separator(), lblGebruikersTitle, createGebruikerList());
    }
    
    private AnchorPane createFilterPane(){
        AnchorPane filterPane = new AnchorPane();
        filterPane.setScaleX(1);
        Label lblFilterTitle = new Label("Filter");
        lblFilterTitle.setFont(new Font("Arial Black", 16.0));
        lblFilterTitle.setTextFill(Paint.valueOf("#393980"));
        lblFilterTitle.setLayoutX(10);
        lblFilterTitle.setLayoutY(10);
        Label lblNaamFilter = new Label("Naam");
        lblNaamFilter.setLayoutX(15);
        lblNaamFilter.setLayoutY(80);
        Label lblVoornaamFilter = new Label("Voornaam");
        lblVoornaamFilter.setLayoutX(175);
        lblVoornaamFilter.setLayoutY(80);
        TextField txfNaamFilter = new TextField();
        txfNaamFilter.setLayoutX(10);
        txfNaamFilter.setLayoutY(99);
        txfNaamFilter.setPrefSize(130, 25);
        TextField txfVoornaamFilter = new TextField();
        txfVoornaamFilter.setLayoutX(170);
        txfVoornaamFilter.setLayoutY(99);
        txfVoornaamFilter.setPrefSize(130, 25);
        CheckBox cbLid = new CheckBox("Lid");
        cbLid.setLayoutX(20);
        cbLid.setLayoutY(135);
        cbLid.setSelected(true);
        CheckBox cbProeflid = new CheckBox("Proeflid");
        cbProeflid.setLayoutX(77);
        cbProeflid.setLayoutY(135);
        cbProeflid.setSelected(true);
        Button btnFilter = new Button("Filter");
        btnFilter.getStyleClass().addAll("lg", "info");
        btnFilter.setPrefWidth(290);
        btnFilter.setLayoutX(10);
        btnFilter.setLayoutY(160);
        
        // Use the filter
        btnFilter.setOnAction((ActionEvent t) -> {
            String naam = txfNaamFilter.getText();
            String voornaam = txfVoornaamFilter.getText();
            
            gc.veranderFilter(naam, voornaam, cbProeflid.selectedProperty().getValue(), cbLid.selectedProperty().getValue());
        });
        
        filterPane.getChildren().addAll(lblFilterTitle, lblNaamFilter, lblVoornaamFilter, txfNaamFilter, txfVoornaamFilter, cbLid, cbProeflid, btnFilter);
        return filterPane;
    }
    
    private TableView createGebruikerList(){
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
        
         gebruikerList.getSelectionModel().selectedItemProperty().addListener((ObservableValue observableValue, Object oldValue, Object newValue) -> {
             gc.setCurrentTypeGebruiker(((AGebruiker)newValue).getType());
             gc.setCurrentGebruiker((AGebruiker)newValue);
         });
        
        return gebruikerList;
    }
}
