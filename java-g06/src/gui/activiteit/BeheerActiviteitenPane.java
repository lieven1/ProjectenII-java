/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.activiteit;

import controllers.ActiviteitenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Steve
 */
public class BeheerActiviteitenPane {

    private BeheerActiviteitenGUIController guiController;
    
    private Pane pane;

    public BeheerActiviteitenPane(ActiviteitenController contr) {
        try {
            FXMLLoader loader = new FXMLLoader(BeheerActiviteitenPane.class.getResource("BeheerActiviteiten.fxml"));
            this.guiController = new BeheerActiviteitenGUIController();
            guiController.setController(contr);
            loader.setController(guiController);
            pane = loader.getRoot();
            loader.load();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Pane getPane(){
        return pane;
    }

}
