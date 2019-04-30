package gui.Gebruiker;

import controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.TypeGebruiker;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class GebruikerBeheerListPanel extends VBox{
    GebruikerController gc;
    ListView<String> gebruikerList;
    
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
        Label lblGebruikersnaamFilter = new Label("Gebruikersnaam");
        lblGebruikersnaamFilter.setLayoutX(15);
        lblGebruikersnaamFilter.setLayoutY(31);
        Label lblNaamFilter = new Label("Naam");
        lblNaamFilter.setLayoutX(15);
        lblNaamFilter.setLayoutY(80);
        Label lblVoornaamFilter = new Label("Voornaam");
        lblVoornaamFilter.setLayoutX(175);
        lblVoornaamFilter.setLayoutY(80);
        TextField txfGebruikersnaamFilter = new TextField();
        txfGebruikersnaamFilter.setLayoutX(10);
        txfGebruikersnaamFilter.setLayoutY(50);
        txfGebruikersnaamFilter.setPrefSize(290, 25);
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
        CheckBox cbBeheerder = new CheckBox("Beheerder");
        cbBeheerder.setLayoutX(160);
        cbBeheerder.setLayoutY(135);
        cbBeheerder.setSelected(true);
        Button btnFilter = new Button("Filter");
        btnFilter.getStyleClass().addAll("lg", "info");
        btnFilter.setPrefWidth(290);
        btnFilter.setLayoutX(10);
        btnFilter.setLayoutY(160);
        
        // Use the filter
        btnFilter.setOnAction((ActionEvent t) -> {
            List<AGebruiker> _gebruikerList = new ArrayList<>();
            String gebruikersnaam = txfGebruikersnaamFilter.getText();
            String naam = txfNaamFilter.getText();
            String voornaam = txfVoornaamFilter.getText();
            if(!cbLid.isSelected())                
                _gebruikerList.removeAll(_gebruikerList.stream().filter(g -> g.getType().equals(TypeGebruiker.Lid)).collect(Collectors.toList()));
            if(!cbProeflid.isSelected())                
                _gebruikerList.removeAll(_gebruikerList.stream().filter(g -> g.getType().equals(TypeGebruiker.Proefgebruiker)).collect(Collectors.toList()));
            if(!cbBeheerder.isSelected())                
                _gebruikerList.removeAll(_gebruikerList.stream().filter(g -> g.getType().equals(TypeGebruiker.Beheerder)).collect(Collectors.toList()));
            if(gebruikersnaam != null && !gebruikersnaam.isBlank())
                _gebruikerList.removeAll(_gebruikerList.stream().filter(g -> !g.getGebruikersnaam().toUpperCase().contains(gebruikersnaam.toUpperCase())).collect(Collectors.toList()));
            if(naam != null && !naam.isBlank())
                _gebruikerList.removeAll(_gebruikerList.stream().filter(g -> !g.getNaam().toUpperCase().contains(naam.toUpperCase())).collect(Collectors.toList()));
            if(voornaam != null && !voornaam.isBlank())
                _gebruikerList.removeAll(_gebruikerList.stream().filter(g -> !g.getVoornaam().toUpperCase().contains(voornaam.toUpperCase())).collect(Collectors.toList()));
            
            ObservableList<String> gebruikerListContent = FXCollections.observableArrayList(_gebruikerList.stream().map(g -> g.getGebruikersnaam()).collect(Collectors.toList()));
            gebruikerList.getItems().setAll(gebruikerListContent);
        });
        
        filterPane.getChildren().addAll(lblFilterTitle, lblGebruikersnaamFilter, lblNaamFilter, lblVoornaamFilter, txfGebruikersnaamFilter, txfNaamFilter, txfVoornaamFilter, cbLid, cbProeflid, cbBeheerder, btnFilter);
        return filterPane;
    }
    
    private ListView createGebruikerList(){
        ObservableList<String> gebruikerListContent = FXCollections.observableArrayList(gc.getObservableList().stream().map(g -> g.getGebruikersnaam()).collect(Collectors.toList()));
        gebruikerList = new ListView<>(gebruikerListContent);
        
        gebruikerList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        });
        
        return gebruikerList;
    }
}
