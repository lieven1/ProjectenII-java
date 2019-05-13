package gui.Start;

import beheerders.ActiviteitenBeheerder;
import gui.Gebruiker.GuiGebruikerController;
import gui.Panel;
import controllers.DomeinController;
import domain.Activiteit;
import beheerders.GebruikerBeheerder;
import domain.GebruikerModels.ProefGebruiker;
import gui.Gebruiker.GuiGebruikerController;
import gui.Overzichten.OverzichtPanelController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicatieFrameController extends VBox implements Panel, PropertyChangeListener {

    private DomeinController dc;

    @FXML
    private MenuItem appMenuStart;
    @FXML
    private MenuItem appMenuStop;
    @FXML
    private MenuItem gebMenuBeheer;
    @FXML
    private MenuItem actMenuBeheer;
    @FXML
    private MenuItem overzichtAanwezigheden;
    @FXML
    private MenuItem overzichtActiviteiten;
    @FXML
    private MenuItem overzichtKampioenschap;

    @FXML
    private AnchorPane contentPane;

    public ApplicatieFrameController(DomeinController dc) {
        this.dc = dc;
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("ApplicatiePanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // contentPane
        Pane pane = new ApplicatieStartPanel(dc);
        contentPane.getChildren().add(pane);
        dc.getGuiController().setPane(pane, this.getHeight(), this.getWidth());

        // EventHandlers
        appMenuStart.setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new ApplicatieStartPanel(dc), this.getHeight(), this.getWidth());
        });
        appMenuStop.setOnAction((ActionEvent t) -> {
            // Voeg confirmationknop toe
            ((Stage) this.getScene().getWindow()).close();
        });
        gebMenuBeheer.setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new GuiGebruikerController(dc.getGebruikerController()), this.getHeight(), this.getWidth());
        });
        overzichtAanwezigheden.setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new OverzichtPanelController(dc.getOverzichtController(), dc.getGebruikerController(), 0), this.getHeight(), this.getWidth());
        });
        overzichtActiviteiten.setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new OverzichtPanelController(dc.getOverzichtController(), dc.getGebruikerController(), 1), this.getHeight(), this.getWidth());
        });
        overzichtKampioenschap.setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new OverzichtPanelController(dc.getOverzichtController(), dc.getGebruikerController(), 2), this.getHeight(), this.getWidth());
        });

        this.dc.getGuiController().addPropertyChangeListener(this);
    }

    @Override
    public void resizeWidth(double width) {
        ((Panel) contentPane.getChildren().get(0)).resizeWidth(width);
    }

    @Override
    public void resizeHeight(double height) {
        ((Panel) contentPane.getChildren().get(0)).resizeHeight(height);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        contentPane.getChildren().set(0, (Pane) evt.getNewValue());
        this.resizeWidth(dc.getGuiController().getWidth());
        this.resizeHeight(dc.getGuiController().getHeight());
    }
}
