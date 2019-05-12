/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten;

import domain.Overzicht.Lesmoment;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author boris
 */
public abstract class DetailPanel extends ScrollPane {

    public abstract void loadItems(Object obj);
}
