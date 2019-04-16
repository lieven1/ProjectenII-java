package Gebruiker;

import Gebruiker.Models.AGebruiker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GebruikerBeheerGegevensPanel extends GridPane{
    private GebruikerBeheerPanelController controller;
    private TextField txfGebruikersnaam, txfTypeGebruiker;
    
    public GebruikerBeheerGegevensPanel(GebruikerBeheerPanelController controller){
        this.controller = controller;
        this.setLayoutX(320);
        
        // TextFields
        txfGebruikersnaam = new TextField("Gebruikersnaam");
        txfTypeGebruiker = new TextField("TypeGebruiker");        
        this.add(new Label("Gebruikersnaam"), 0, 0);
        this.add(txfGebruikersnaam, 0, 1);
        this.add(new Label("TypeGebruiker"), 1, 0);
        this.add(txfTypeGebruiker, 1, 1);
    }
    
    public void beheerGebruiker(AGebruiker gebruiker){
        if(gebruiker != null){
            txfGebruikersnaam.setText(gebruiker.getGebruikersnaam());
            txfTypeGebruiker.setText(gebruiker.getType().toString());
        }else{
            txfGebruikersnaam.setText("Gebruikersnaam");
            txfTypeGebruiker.setText("TypeGebruiker");
        }
    }
}
