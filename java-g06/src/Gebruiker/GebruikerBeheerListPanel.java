package Gebruiker;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class GebruikerBeheerListPanel extends VBox {
    GebruikerBeheerPanelController controller;
    
    public GebruikerBeheerListPanel(GebruikerBeheerPanelController controller){
        this.controller = controller;
        
        // VBox Constraints
        this.setPrefWidth(310);
        this.setPrefHeight(800);
        this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #999;");
        
        this.getChildren().addAll(createFilterPane(), new Separator());
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
        CheckBox cbProeflid = new CheckBox("Proeflid");
        cbProeflid.setLayoutX(77);
        cbProeflid.setLayoutY(135);
        CheckBox cbBeheerder = new CheckBox("Beheerder");
        cbBeheerder.setLayoutX(160);
        cbBeheerder.setLayoutY(135);
        Button btnFilter = new Button("Filter");
        btnFilter.getStyleClass().addAll("lg", "info");
        btnFilter.setPrefWidth(290);
        btnFilter.setLayoutX(10);
        btnFilter.setLayoutY(160);
        
        filterPane.getChildren().addAll(lblFilterTitle, lblGebruikersnaamFilter, lblNaamFilter, lblVoornaamFilter, txfGebruikersnaamFilter, txfNaamFilter, txfVoornaamFilter, cbLid, cbProeflid, cbBeheerder, btnFilter);
        return filterPane;
    }
}
