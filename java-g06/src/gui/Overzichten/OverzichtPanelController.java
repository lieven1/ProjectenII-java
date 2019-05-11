/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten;

import gui.Overzichten.Aanwezigheden.AanwezighedenListPanel;
import gui.Overzichten.Aanwezigheden.AanwezighedenDetailPanel;
import controllers.OverzichtController;
import gui.Panel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.layout.HBox;

/**
 *
 * @author boris
 */
public class OverzichtPanelController extends HBox implements Panel, PropertyChangeListener {

    private OverzichtListPanel listPanel;
    private DetailPanel gegevensPanel;

    private OverzichtController oc;

    //meerdere ctor per type overzicht --> in framecontroller wordt juist ctor aangeroepen bij constructie
    //default ctor --> aanwezigheden
    public OverzichtPanelController(OverzichtController oc, int typeOverzicht) {
        this.oc = oc;

        // Panels
        if (typeOverzicht == 2) {
            listPanel = new KampioenschapListPanel(oc);
            gegevensPanel = new KampioenschapDetailPanel(oc);
        } else if (typeOverzicht == 1) {
            listPanel = new ActiviteitenListPanel(oc);
            gegevensPanel = new ActiviteitenDetailPanel(oc);
        } else {
            listPanel = new AanwezighedenListPanel(oc);
            gegevensPanel = new AanwezighedenDetailPanel(oc);
        }

        oc.addPropertyChangeListener(this);
        this.getChildren().addAll(listPanel, gegevensPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //wanneer item uit lijst geselecteerd word --> pas detailpanel aan
    }

    @Override
    public void resizeWidth(double width) {
        listPanel.setPrefWidth(width * 0.2);
        gegevensPanel.setPrefWidth(width * 0.8);
    }

    @Override
    public void resizeHeight(double height) {
        gegevensPanel.setPrefHeight(height * 2.25);
    }
}
