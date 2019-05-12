/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten.Aanwezigheden;

import controllers.GebruikerController;
import controllers.OverzichtController;
import domain.Overzicht.Lesmoment;
import domain.Overzicht.LesmomentLeden;
import gui.Overzichten.DetailPanel;
import gui.Overzichten.DetailPanel;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * ✓
 *
 * @author boris
 */
public class AanwezighedenDetailPanel extends DetailPanel {

    private OverzichtController oc;
    private GebruikerController gc;

    @FXML
    Label titelLabel, detailGebruikerNaam, detailTelefoonValue, detailEmailValue, detailGradatieValue, detailPuntenValue;
    @FXML
    TableView tableViewAanwezigheden;
    @FXML
    TableColumn naamGebruiker, VoornaamGebruiker, AanwezigGebruiker, Ingeschreven, LesformuleGebruiker;

    public AanwezighedenDetailPanel(OverzichtController oc, GebruikerController gc) {
        this.oc = oc;
        this.gc = gc;
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("AanwezighedenDetailPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void loadItems(Object obj) {
        Lesmoment lesmoment = (Lesmoment) obj;
        List<LesmomentLeden> aanwezigheden = oc.getLesmomentLeden().stream().filter(l -> l.getLesmomentId().equals(lesmoment.getLesmomentId())).collect(Collectors.toList());
        
        //opvullen tableview 
        //klasse maken die gegevens lesmontlid en gebruier houdt --> vullend tableview adhv die klasse
    }

}
