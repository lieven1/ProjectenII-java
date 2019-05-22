/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten;

import javafx.scene.control.ScrollPane;

/**
 *
 * @author boris
 */
public abstract class DetailPanel extends ScrollPane {

    public abstract void loadItems(Object obj);
}
