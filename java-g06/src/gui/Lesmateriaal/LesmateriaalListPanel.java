package gui.Lesmateriaal;

import domain.GebruikerModels.Gradatie;
import domain.LesmateriaalModels.Lesmateriaal;
import domain.LesmateriaalModels.Thema;
import domain.controllers.LesmateriaalController;
import java.io.File;
import java.net.MalformedURLException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class LesmateriaalListPanel extends VBox {

    private final LesmateriaalController gc;
    private TableView lesmateriaalList;

    public LesmateriaalListPanel(LesmateriaalController gc) {
        this.gc = gc;

        // VBox Constraints
        this.setPrefWidth(330);
        this.setPrefHeight(1080);
        this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #999;");

        Label lblLesmateriaalTitle = new Label("Lesmateriaal");
        lblLesmateriaalTitle.setFont(new Font("Arial Black", 16.0));
        lblLesmateriaalTitle.setTextFill(Paint.valueOf("#393980"));
        lblLesmateriaalTitle.setStyle("-fx-label-padding: 0 0 0 10");
        createLesmateriaalList();

        this.getChildren().addAll(createFilterPane(), new Separator(), lblLesmateriaalTitle, lesmateriaalList);
    }

    private AnchorPane createFilterPane() {
        AnchorPane filterPane = new AnchorPane();
        filterPane.setScaleX(1);
        ImageView imgLogo = new ImageView();
        try {
            imgLogo.setImage(new Image((new File("src/resources/logoTemp.png")).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        imgLogo.setLayoutX(10);
        imgLogo.setLayoutY(10);
        Label lblFilterTitle = new Label("Filter lesmateriaal");
        lblFilterTitle.setFont(new Font("Arial Black", 16.0));
        lblFilterTitle.setTextFill(Paint.valueOf("#393980"));
        lblFilterTitle.setLayoutX(10);
        lblFilterTitle.setLayoutY(90);
        
        Label lblNaamFilter = new Label("Naam");
        lblNaamFilter.setLayoutX(15);
        lblNaamFilter.setLayoutY(111);
        TextField txfNaamFilter = new TextField();
        txfNaamFilter.setLayoutX(10);
        txfNaamFilter.setLayoutY(130);
        txfNaamFilter.setPrefSize(290, 25);
        
        Label lblGraadFilter = new Label("Graad");
        lblGraadFilter.setLayoutX(15);
        lblGraadFilter.setLayoutY(164);
        ChoiceBox cbGraad = new ChoiceBox();
        cbGraad.setItems(FXCollections.observableArrayList(Gradatie.values()));
        cbGraad.setLayoutX(10);
        cbGraad.setLayoutY(183);
        cbGraad.setPrefSize(130, 25);
        Label lblThemaFilter = new Label("Thema");
        lblThemaFilter.setLayoutX(175);
        lblThemaFilter.setLayoutY(164);
        ChoiceBox cbThema = new ChoiceBox();
        cbThema.setItems(gc.getThemaLijst());
        cbThema.setLayoutX(170);
        cbThema.setLayoutY(183);
        cbThema.setPrefSize(130, 25);
        
        
        Button btnFilter = new Button("Filter");
        btnFilter.getStyleClass().addAll("lg", "info");
        btnFilter.setPrefWidth(233);
        btnFilter.setLayoutX(67);
        btnFilter.setLayoutY(225);
        Button btnVerwFilter = new Button("X");
        btnVerwFilter.getStyleClass().addAll("lg", "danger");
        btnVerwFilter.setPrefWidth(50);
        btnVerwFilter.setLayoutX(10);
        btnVerwFilter.setLayoutY(225);

        // Use the filter
        btnFilter.setOnAction((ActionEvent t) -> {
            gc.veranderFilter(txfNaamFilter.getText(), (Gradatie)cbGraad.getValue(), (Thema)cbThema.getValue());
        });

        btnVerwFilter.setOnAction((ActionEvent t) -> {
            txfNaamFilter.setText("");
            cbGraad.getSelectionModel().clearSelection();
            cbThema.getSelectionModel().clearSelection();
            gc.veranderFilter(null, null, null);
        });

        filterPane.getChildren().addAll(lblFilterTitle, lblNaamFilter, lblGraadFilter, lblThemaFilter, txfNaamFilter, cbGraad, cbThema, btnFilter, btnVerwFilter, imgLogo);
        return filterPane;
    }
    
    private void createLesmateriaalList() {
        lesmateriaalList = new TableView<Lesmateriaal>();
        lesmateriaalList.setEditable(false);
        TableColumn graadColumn = new TableColumn("Graad");
        TableColumn nameColumn = new TableColumn("Naam");
        TableColumn themaColumn = new TableColumn("Thema");
        graadColumn.setCellValueFactory(new PropertyValueFactory<Lesmateriaal, Gradatie>("graad"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Lesmateriaal, String>("naam"));
        themaColumn.setCellValueFactory(new PropertyValueFactory<Lesmateriaal, Thema>("thema"));
        lesmateriaalList.getColumns().addAll(nameColumn, themaColumn, graadColumn);
        lesmateriaalList.setItems(gc.getLesmateriaalLijst());
        lesmateriaalList.setPrefHeight(680);

        lesmateriaalList.getSelectionModel().selectedItemProperty().addListener((ObservableValue observableValue, Object oldValue, Object newValue) -> {
            gc.setCurrentLesmateriaal(((Lesmateriaal) newValue));
        });
    }
}
