/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten.Activiteiten;

import domain.Activiteit;
import domain.controllers.ActiviteitenController;
import domain.controllers.OverzichtController;
import gui.Overzichten.OverzichtListPanel;
import gui.PanelGenerator;
import java.io.File;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author boris
 */
public class ActiviteitenListPanel extends OverzichtListPanel {

    private OverzichtController oc;
    private ActiviteitenController ac;
    private TableView<Activiteit> activiteitenTableView;
    private PanelGenerator generator;
    private final DateFormat dateFormat = new SimpleDateFormat("EEE, dd-MM-yyyy hh:mm", new Locale("nl", "BE"));

    public ActiviteitenListPanel(OverzichtController oc, ActiviteitenController ac) {
        this.oc = oc;
        this.ac = ac;
        generator = new PanelGenerator();

        // VBox Constraints
        this.setPrefWidth(330);
        this.setPrefHeight(1080);
        this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #999;");

        Label lblGebruikersTitle = new Label("Activiteteiten");
        lblGebruikersTitle.setFont(new Font("Arial Black", 16.0));
        lblGebruikersTitle.setTextFill(Paint.valueOf("#393980"));
        lblGebruikersTitle.setStyle("-fx-label-padding: 0 0 0 10");

        this.getChildren().addAll(createFilterPane(), new Separator(), lblGebruikersTitle, createList());
    }

    @Override
    public Pane createFilterPane() {
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

        //textfield filters
        Label lblNaamFilter = new Label("Naam");
        lblNaamFilter.setLayoutX(15);
        lblNaamFilter.setLayoutY(111);
        Label lblTypeFilter = new Label("Type");
        lblTypeFilter.setLayoutX(175);
        lblTypeFilter.setLayoutY(111);
        TextField txfNaamFilter = new TextField();
        txfNaamFilter.setLayoutX(10);
        txfNaamFilter.setLayoutY(130);
        txfNaamFilter.setPrefSize(130, 25);
        TextField txfTypeFilter = new TextField();
        txfTypeFilter.setLayoutX(170);
        txfTypeFilter.setLayoutY(130);
        txfTypeFilter.setPrefSize(130, 25);

        //dateFilter
        Label lblVanFilter = new Label("Van");
        lblVanFilter.setLayoutX(15);
        lblVanFilter.setLayoutY(170);
        Label lblTotFilter = new Label("Tot");
        lblTotFilter.setLayoutX(175);
        lblTotFilter.setLayoutY(170);
        DatePicker dpVanFilter = new DatePicker();
        dpVanFilter.setLayoutX(10);
        dpVanFilter.setLayoutY(190);
        dpVanFilter.setPrefSize(130, 25);
        DatePicker dpTotFilter = new DatePicker();
        dpTotFilter.setLayoutX(170);
        dpTotFilter.setLayoutY(190);
        dpTotFilter.setPrefSize(130, 25);

        //buttons
        Button btnFilter = new Button("Filter");
        btnFilter.getStyleClass().addAll("lg", "info");
        btnFilter.setPrefWidth(233);
        btnFilter.setLayoutX(67);
        btnFilter.setLayoutY(250);
        Button btnVerwFilter = new Button("X");
        btnVerwFilter.getStyleClass().addAll("lg", "danger");
        btnVerwFilter.setPrefWidth(50);
        btnVerwFilter.setLayoutX(10);
        btnVerwFilter.setLayoutY(250);

        // Use the filter
        btnFilter.setOnAction((ActionEvent t) -> {
            LocalDate van = dpVanFilter.getValue();
            LocalDate tot = dpTotFilter.getValue();
            ac.veranderFilter(txfNaamFilter.getText(), txfTypeFilter.getText(), van, tot);
        });

        btnVerwFilter.setOnAction((ActionEvent t) -> {
            dpVanFilter.setValue(null);
            dpTotFilter.setValue(null);
            txfNaamFilter.setText("");
            txfTypeFilter.setText("");
            activiteitenTableView.setItems(ac.getActiviteiten());
        });
        filterPane.getChildren().addAll(imgLogo, lblFilterTitle, lblNaamFilter, lblTypeFilter, txfNaamFilter, txfTypeFilter, lblVanFilter, lblTotFilter, dpVanFilter, dpTotFilter, btnFilter, btnVerwFilter
        );
        return filterPane;
    }

    @Override
    public TableView createList() {
        activiteitenTableView = new TableView<Activiteit>();
        activiteitenTableView.setEditable(false);
        TableColumn naamColumn = new TableColumn("Naam");
        TableColumn typeColumn = new TableColumn("Type");
        TableColumn startDatumColumn = new TableColumn("Startdatum");
        TableColumn eindDatumColumn = new TableColumn("Einddatum");

        naamColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, String>("titel"));
        startDatumColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, Calendar>("startDatum"));
        startDatumColumn.setCellFactory(p -> {
            return new CalenderCell();
        });
        eindDatumColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, Calendar>("eindDatum"));
        eindDatumColumn.setCellFactory((p) -> {
            return new CalenderCell();
        });
        typeColumn.setCellValueFactory(new PropertyValueFactory<Activiteit, String>("type"));

        activiteitenTableView.getColumns().addAll(naamColumn, typeColumn, startDatumColumn, eindDatumColumn);
        activiteitenTableView.setItems(ac.getActiviteiten());
        activiteitenTableView.setPrefHeight(1080);

        naamColumn.prefWidthProperty().bind(activiteitenTableView.widthProperty().multiply(0.25));
        typeColumn.prefWidthProperty().bind(activiteitenTableView.widthProperty().multiply(0.25));
        startDatumColumn.prefWidthProperty().bind(activiteitenTableView.widthProperty().multiply(0.25));
        eindDatumColumn.prefWidthProperty().bind(activiteitenTableView.widthProperty().multiply(0.25));

        activiteitenTableView.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ac.setCurrentActiviteit(nv);
        });

        return activiteitenTableView;
    }

    private class CalenderCell extends TableCell<Activiteit, Calendar> {

        private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", new Locale("nl", "BE"));

        @Override
        protected void updateItem(Calendar item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null) {
                setText(null);
            } else {
                setText(dateFormat.format(item.getTime()));
            }
        }
    }

}
