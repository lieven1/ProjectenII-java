package gui.Start;

import gui.Gebruiker.GebruikerBeheerPanelController;
import gui.Panel;
import controllers.DomeinController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicatieFrameController extends VBox implements Panel, PropertyChangeListener {
    private DomeinController dc;
    
    @FXML 
    private MenuItem appMenuStart;
    @FXML 
    private MenuItem appMenuStop;
    @FXML 
    private MenuItem gebMenuBeheer;
    @FXML
    private AnchorPane contentPane;
    
    public ApplicatieFrameController(DomeinController dc){
        this.dc = dc;
        FXMLLoader loader = 
            new FXMLLoader(getClass().getResource("ApplicatiePanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
        
        // contentPane
        Pane pane = new ApplicatieStartPanel(dc);
        contentPane.getChildren().add(pane);
        dc.setPane(pane, this.getHeight(), this.getWidth());
        
        // EventHandlers
        appMenuStart.setOnAction((ActionEvent t) -> {
            dc.setPane(new ApplicatieStartPanel(dc), this.getHeight(), this.getWidth());
        });
        appMenuStop.setOnAction((ActionEvent t) -> {
            // Voeg confirmationknop toe
            ((Stage)this.getScene().getWindow()).close();
        });
        gebMenuBeheer.setOnAction((ActionEvent t) -> {
            dc.setPane(new GebruikerBeheerPanelController(dc.getGebruikerController()), this.getHeight(), this.getWidth());
        });
        this.dc.addPropertyChangeListener(this);
    }

    @Override
    public void resizeWidth(double width) {
        ((Panel)contentPane.getChildren().get(0)).resizeWidth(width);
    }

    @Override
    public void resizeHeight(double height) {
        ((Panel)contentPane.getChildren().get(0)).resizeHeight(height);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        contentPane.getChildren().set(0, (Pane)evt.getNewValue());
        this.resizeWidth(dc.getWidth());
        this.resizeHeight(dc.getHeight());
    }
}
