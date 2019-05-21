/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten;

import gui.Overzichten.Activiteiten.ActiviteitenListPanel;
import domain.controllers.ActiviteitenController;
import domain.controllers.GebruikerController;
import domain.controllers.LesmateriaalController;
import gui.Overzichten.Aanwezigheden.AanwezighedenListPanel;
import domain.controllers.OverzichtController;
import domain.controllers.RaadplegingController;
import gui.Overzichten.Aanwezigheden.AanwezighedenDetailPanelController;
import gui.Overzichten.Activiteiten.ActiviteitenDetailPanelController;
import gui.Overzichten.Raadplegingen.RaadplegingDetailPanelController;
import gui.Overzichten.Raadplegingen.RaadplegingListPanel;
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
    public OverzichtPanelController(OverzichtController oc, GebruikerController gc, ActiviteitenController ac, RaadplegingController rc, LesmateriaalController lc, int typeOverzicht) {
        this.oc = oc;

        // Panels
        if (typeOverzicht == 3) {
            listPanel = new RaadplegingListPanel(rc, lc);
            gegevensPanel = new RaadplegingDetailPanelController(gc);
            rc.addPropertyChangeListener(this);
        } else if (typeOverzicht == 2) {
            listPanel = new KampioenschapListPanel(oc);
            gegevensPanel = new KampioenschapDetailPanel(oc);
        } else if (typeOverzicht == 1) {
            listPanel = new ActiviteitenListPanel(oc, ac);
            gegevensPanel = new ActiviteitenDetailPanelController(oc, ac, gc);
            ac.addPropertyChangeListener(this);
        } else {
            listPanel = new AanwezighedenListPanel(oc);
            gegevensPanel = new AanwezighedenDetailPanelController(oc, gc);
            oc.addPropertyChangeListener(this);
        }
        oc.setCurrentLesmoment(oc.getLesomentList().stream().findFirst().get());
        this.getChildren().addAll(listPanel, gegevensPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        gegevensPanel.loadItems(evt.getNewValue());
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
