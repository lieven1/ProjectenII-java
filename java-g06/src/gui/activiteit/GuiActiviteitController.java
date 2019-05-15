/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.activiteit;

import domain.controllers.ActiviteitenController;
import gui.Panel;
import javafx.scene.layout.HBox;

/**
 *
 * @author Steve
 */
public class GuiActiviteitController extends HBox implements Panel {

    private ActiviteitenListPanel listPanel;
    private FormActiviteiten detailPanel;

    public GuiActiviteitController(ActiviteitenController ac) {
        super();
        // Panels
        listPanel = new ActiviteitenListPanel(ac);
        detailPanel = new FormActiviteiten(ac);

        this.getChildren().addAll(listPanel, detailPanel);
    }

    @Override
    public void resizeWidth(double width) {
        listPanel.setPrefWidth(width * 0.2);
    }

    @Override
    public void resizeHeight(double height) {
    }

}
