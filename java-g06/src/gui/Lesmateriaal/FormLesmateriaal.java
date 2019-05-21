package gui.Lesmateriaal;

import domain.GebruikerModels.Gradatie;
import domain.LesmateriaalModels.Foto;
import domain.LesmateriaalModels.Lesmateriaal;
import domain.LesmateriaalModels.Thema;
import domain.controllers.LesmateriaalController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class FormLesmateriaal extends ScrollPane implements PropertyChangeListener {

    private LesmateriaalController gc;

    @FXML
    TextField txfNaam;
    @FXML
    ChoiceBox cbGraad, cbThema;
    @FXML
    Button btnNieuw, btnOpslaan, btnVerwijder, btnFotoToevoegen, btnFotoVerwijderen;
    @FXML
    Label lblFout;
    @FXML
    TextArea txaBeschrijving;
    @FXML
    ListView listFoto;
    @FXML
    ImageView imgFoto;

    public FormLesmateriaal(LesmateriaalController gc) {
        this.gc = gc;
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("FormLesmateriaal.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        cbGraad.setItems(FXCollections.observableArrayList(Gradatie.values()));
        cbThema.setItems(gc.getThemaLijst());

        btnOpslaan.setText("Toevoegen");

        btnVerwijder.setDisable(true);
        btnFotoVerwijderen.setDisable(true);

        lblFout.setText("");
        txaBeschrijving.setWrapText(true);

        // Buttons
        btnNieuw.setOnAction((ActionEvent t) -> {
            gc.setCurrentLesmateriaal(null);
            this.resetForm();
        });
        btnOpslaan.setOnAction((ActionEvent t) -> {
            this.saveLesmateriaal();
        });
        btnVerwijder.setOnAction((ActionEvent t) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Verwijder Lesmateriaal");
            alert.setHeaderText("Verwijderen?");
            alert.setContentText("Bent u zeker dat u dit lesmateriaal wenst te verwijderen?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                gc.delete();
                gc.setCurrentLesmateriaal(null);
            }
        });

        btnFotoToevoegen.setOnAction((ActionEvent t) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Foto toevoegen");
            List<File> fotos = fileChooser.showOpenMultipleDialog(this.getContextMenu());
            if(fotos != null){
                fotos.forEach((file) -> {
                    listFoto.getItems().add(
                            new Foto(file.getName().substring(0, file.getName().lastIndexOf(".")), file.getName().substring(file.getName().lastIndexOf(".")+1))
                    );
                });
            }
        });
        btnFotoVerwijderen.setOnAction((ActionEvent t) -> {
            listFoto.getItems().remove(listFoto.getSelectionModel().getSelectedItem());
        });

        listFoto.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                try {
                    imgFoto.setImage(new Image((new File("src/resources/logoTemp.png")).toURI().toURL().toExternalForm()));
                } catch (MalformedURLException ex) {
                    System.err.println(ex);
                }
            } else {
                imgFoto.setImage(null);
            }
            if (btnFotoVerwijderen.disableProperty().get() || newV != null) {
                btnFotoVerwijderen.setDisable(false);
            } else {
                btnFotoVerwijderen.setDisable(true);
            }
        });

        gc.addPropertyChangeListener(this);
    }

    public void resetForm() {
        btnOpslaan.setText("Opslaan");
        btnVerwijder.setDisable(true);

        txfNaam.clear();
        cbGraad.getSelectionModel().clearSelection();
        cbThema.getSelectionModel().clearSelection();
        txaBeschrijving.clear();
        listFoto.setItems(null);
        imgFoto.setImage(null);
    }

    public void loadLesmateriaal(Lesmateriaal lesmateriaal) {
        btnOpslaan.setText("Bijwerken");
        btnVerwijder.setDisable(false);

        txfNaam.setText(lesmateriaal.getNaam());
        cbGraad.setValue(lesmateriaal.getGraad());
        cbThema.setValue(lesmateriaal.getThema());
        txaBeschrijving.setText(lesmateriaal.getBeschrijving());

        // Clone list instead of referencing
        List<Foto> tempFotos = new ArrayList<>();
        tempFotos.addAll(lesmateriaal.getFotos());
        listFoto.setItems(FXCollections.observableList(tempFotos));
    }

    public void saveLesmateriaal() {
        try {
            Lesmateriaal lesm = gc.getCurrentLesmateriaal();
            if(lesm == null){
                lesm = new Lesmateriaal();
            }
            lesm.setNaam(txfNaam.getText());
            lesm.setGraad((Gradatie) cbGraad.getValue());
            lesm.setFotos(listFoto.getItems());
            lesm.setThema((Thema) cbThema.getValue());
            lesm.setBeschrijving(txaBeschrijving.getText());
            if(gc.getCurrentLesmateriaal() != null)
                gc.modify();
            else
                gc.create(lesm);
            lblFout.setText("");
        } catch (Exception e) {
            lblFout.setText(e.getMessage());
            lblFout.setVisible(true);
        }
    }

    public void deleteLesmateriaal() {
        gc.delete();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "currentLesmateriaal":
                if (evt.getNewValue() != null) {
                    this.loadLesmateriaal((Lesmateriaal) evt.getNewValue());
                }
                break;
        }
    }
}
