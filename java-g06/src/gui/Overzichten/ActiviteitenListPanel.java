/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten;

import controllers.OverzichtController;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author boris
 */
public class ActiviteitenListPanel extends OverzichtListPanel {

    private OverzichtController oc;

    ActiviteitenListPanel(OverzichtController oc) {
        this.oc = oc;

        // VBox Constraints
        this.setPrefWidth(330);
        this.setPrefHeight(1080);
        this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #999;");

        Label lblGebruikersTitle = new Label("Hier komt een lijst van de activiteteiten");
        lblGebruikersTitle.setFont(new Font("Arial Black", 16.0));
        lblGebruikersTitle.setTextFill(Paint.valueOf("#393980"));
        lblGebruikersTitle.setStyle("-fx-label-padding: 0 0 0 10");

        this.getChildren().addAll(createFilterPane(), new Separator(), lblGebruikersTitle);
    }

    @Override
    public AnchorPane createFilterPane() {
        AnchorPane filterPane = new AnchorPane();
        filterPane.setScaleX(1);
        Label lblFilterTitle = new Label("Hier komt een filter voor activiteiten");
        lblFilterTitle.setFont(new Font("Arial Black", 16.0));
        lblFilterTitle.setTextFill(Paint.valueOf("#393980"));
        lblFilterTitle.setLayoutX(10);
        lblFilterTitle.setLayoutY(90);
        filterPane.getChildren().addAll(lblFilterTitle);
        return filterPane;
    }

    @Override
    public TableView createList() {
        return null;
    }

}