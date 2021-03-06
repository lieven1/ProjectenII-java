/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten.Aanwezigheden;

import domain.controllers.OverzichtController;
import domain.Overzicht.Lesmoment;
import gui.Overzichten.OverzichtListPanel;
import java.io.File;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author boris
 */
public class AanwezighedenListPanel extends OverzichtListPanel {

    private OverzichtController oc;
    private TableView lesmomentList;
    private final DateFormat dateFormat = new SimpleDateFormat("EEE, dd-MM-yyyy hh:mm", new Locale("nl", "BE"));

    public AanwezighedenListPanel(OverzichtController oc) {
        this.oc = oc;

        // VBox Constraints
        this.setPrefWidth(330);
        this.setPrefHeight(1080);
        this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #999;");

        Label lblGebruikersTitle = new Label("Lesmomenten");
        lblGebruikersTitle.setFont(new Font("Arial Black", 16.0));
        lblGebruikersTitle.setTextFill(Paint.valueOf("#393980"));
        lblGebruikersTitle.setStyle("-fx-label-padding: 0 0 0 10");

        this.getChildren().addAll(createFilterPane(), new Separator(), lblGebruikersTitle, createList());
    }

    @Override
    public AnchorPane createFilterPane() {
        AnchorPane filterPane = new AnchorPane();
        filterPane.setScaleX(1);
        //logo
        ImageView imgLogo = new ImageView();
        try {
            imgLogo.setImage(new Image((new File("src/resources/logoTemp.png")).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        imgLogo.setLayoutX(10);
        imgLogo.setLayoutY(10);
        //title
        Label lblFilterTitle = new Label("Filter lesmomenten");
        lblFilterTitle.setFont(new Font("Arial Black", 16.0));
        lblFilterTitle.setTextFill(Paint.valueOf("#393980"));
        lblFilterTitle.setLayoutX(10);
        lblFilterTitle.setLayoutY(90);

        //dateFilter
        Label lblVanFilter = new Label("Van");
        lblVanFilter.setLayoutX(15);
        lblVanFilter.setLayoutY(111);
        Label lblTotFilter = new Label("Tot");
        lblTotFilter.setLayoutX(175);
        lblTotFilter.setLayoutY(111);
        DatePicker dpVanFilter = new DatePicker();
        dpVanFilter.setLayoutX(10);
        dpVanFilter.setLayoutY(130);
        dpVanFilter.setPrefSize(130, 25);
        DatePicker dpTotFilter = new DatePicker();
        dpTotFilter.setLayoutX(170);
        dpTotFilter.setLayoutY(130);
        dpTotFilter.setPrefSize(130, 25);

        //buttons
        Button btnFilter = new Button("Filter");
        btnFilter.getStyleClass().addAll("lg", "info");
        btnFilter.setPrefWidth(233);
        btnFilter.setLayoutX(67);
        btnFilter.setLayoutY(210);
        Button btnVerwFilter = new Button("X");
        btnVerwFilter.getStyleClass().addAll("lg", "danger");
        btnVerwFilter.setPrefWidth(50);
        btnVerwFilter.setLayoutX(10);
        btnVerwFilter.setLayoutY(210);

        // Use the filter
        btnFilter.setOnAction((ActionEvent t) -> {
            LocalDate van = dpVanFilter.getValue();
            LocalDate tot = dpTotFilter.getValue();
            oc.veranderFilter(van, tot);
        });

        btnVerwFilter.setOnAction((ActionEvent t) -> {
            dpVanFilter.setValue(null);
            dpTotFilter.setValue(null);
            oc.veranderFilter(null, null);
        });

        filterPane.getChildren().addAll(imgLogo, lblFilterTitle, lblVanFilter, lblTotFilter, dpVanFilter, dpTotFilter, btnFilter, btnVerwFilter);
        return filterPane;
    }

    //lijst met lesmomenten
    @Override
    public TableView createList() {
        lesmomentList = new TableView<Lesmoment>();
        lesmomentList.setEditable(false);
        
        TableColumn<Lesmoment, String> StarttijdColumn = new TableColumn<>("Datum");
       
        StarttijdColumn.setCellValueFactory(l -> new ReadOnlyStringWrapper(dateFormat.format(l.getValue().getStartTijd())));
        lesmomentList.getColumns().addAll(StarttijdColumn);
        lesmomentList.setItems(oc.getLesomentObservableList());
        lesmomentList.setPrefHeight(1080);

        StarttijdColumn.prefWidthProperty().bind(lesmomentList.widthProperty().multiply(1));

        StarttijdColumn.setResizable(false);

        lesmomentList.getSelectionModel().selectedItemProperty().addListener((ObservableValue observableValue, Object oldValue, Object newValue) -> {
            oc.setCurrentLesmoment((Lesmoment) newValue);
        });

        return lesmomentList;
    }

}
