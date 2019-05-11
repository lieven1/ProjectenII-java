/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten.Aanwezigheden;

import controllers.OverzichtController;
import gui.Overzichten.DetailPanel;
import gui.Overzichten.DetailPanel;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * ✓
 *
 * @author boris
 */
public class AanwezighedenDetailPanel extends DetailPanel {

    private OverzichtController oc;

    public AanwezighedenDetailPanel(OverzichtController oc) {
        this.oc = oc;
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

}
