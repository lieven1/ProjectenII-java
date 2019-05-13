package gui;

import controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import domain.GebruikerModels.Gradatie;
import domain.GebruikerModels.TypeGebruiker;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class PanelGenerator {

    public PanelGenerator() {
    }

    public Label createTitleLabel(String title) {
        Label label = new Label(title);
        label.setFont(new Font("Arial Black", 16.0));
        label.setTextFill(Paint.valueOf("#393980"));
        return label;
    }

    public Button createFilterButton() {
        Button filterButton = new Button("Filter");
        filterButton.getStyleClass().addAll("lg", "info");
        return filterButton;
    }

    public Button createClearButton() {
        Button clearButton = new Button("X");
        clearButton.getStyleClass().addAll("lg", "danger");
        return clearButton;
    }

    public DatePicker createDatePicker(String text) {
        DatePicker dp = new DatePicker();
        dp.setPromptText(text);
        return dp;
    }

    public TextField createTextField(String text) {
        TextField tf = new TextField();
        tf.setPromptText(text);
        return tf;
    }

    public VBox createEmptyFilterPane() {
        VBox filterPane = new VBox();
        filterPane.setScaleX(1);
        ImageView imgLogo = new ImageView();
        try {
            imgLogo.setImage(new Image((new File("src/resources/logoTemp.png")).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }

        Label title = createFilterPaneTitle();

        filterPane.getChildren().addAll(imgLogo, title);
        return filterPane;
    }

    private TableView createTableView() {
        TableView tv = new TableView<AGebruiker>();
        tv.setEditable(false);
        tv.setPrefHeight(1080);

        return tv;
    }

    private Label createFilterPaneTitle() {
        Label lblFilterTitle = new Label("Filter");
        lblFilterTitle.setFont(new Font("Arial Black", 16.0));
        lblFilterTitle.setTextFill(Paint.valueOf("#393980"));
        return lblFilterTitle;
    }
}
