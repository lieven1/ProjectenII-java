package gui.Start;

import gui.Panel;
import domain.controllers.DomeinController;
import gui.Gebruiker.GuiGebruikerController;
import gui.Lesmateriaal.GuiLesmateriaalController;
import gui.Overzichten.OverzichtPanelController;
import gui.activiteit.GuiActiviteitController;
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
    private MenuItem actMenuBeheer;
    @FXML
    private MenuItem overzichtAanwezigheden;
    @FXML
    private MenuItem overzichtActiviteiten;
//    @FXML
//    private MenuItem overzichtKampioenschap;
    @FXML
    private MenuItem overzichtRaadplegingen;
    @FXML
    private MenuItem lesmateriaalBeheer;

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
            dc.getGuiController().setPane(new OverzichtPanelController(dc.getOverzichtController(), dc.getGebruikerController(), dc.getActiviteitenController(), dc.getRaadplegingController(), dc.getLesmateriaalController(), 0), this.getHeight(), this.getWidth());
        });
        overzichtActiviteiten.setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new OverzichtPanelController(dc.getOverzichtController(), dc.getGebruikerController(), dc.getActiviteitenController(), dc.getRaadplegingController(), dc.getLesmateriaalController(), 1), this.getHeight(), this.getWidth());
        });
//        overzichtKampioenschap.setOnAction((ActionEvent t) -> {
//            dc.getGuiController().setPane(new OverzichtPanelController(dc.getOverzichtController(), dc.getGebruikerController(), dc.getActiviteitenController(), dc.getRaadplegingController(), dc.getLesmateriaalController(), 2), this.getHeight(), this.getWidth());
//        });
        overzichtRaadplegingen.setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new OverzichtPanelController(dc.getOverzichtController(), dc.getGebruikerController(), dc.getActiviteitenController(), dc.getRaadplegingController(), dc.getLesmateriaalController(), 3), this.getHeight(), this.getWidth());
        });
        actMenuBeheer.setOnAction((t) -> {
            dc.getGuiController().setPane(new GuiActiviteitController(dc.getActiviteitenController()), this.getHeight(), this.getWidth());
        });
        lesmateriaalBeheer.setOnAction((t) -> {
            dc.getGuiController().setPane(new GuiLesmateriaalController(dc.getLesmateriaalController()), this.getHeight(), this.getWidth());
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
