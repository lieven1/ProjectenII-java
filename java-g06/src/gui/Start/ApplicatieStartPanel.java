package gui.Start;
//startpage
import gui.Panel;
import domain.controllers.DomeinController;
import gui.Gebruiker.GuiGebruikerController;
import gui.Lesmateriaal.GuiLesmateriaalController;
import gui.Overzichten.OverzichtPanelController;
import gui.activiteit.GuiActiviteitController;
import java.io.File;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ApplicatieStartPanel extends AnchorPane implements Panel {

    private ImageView logo;
    private GridPane menuPanel;

    private DomeinController dc;

    public ApplicatieStartPanel(DomeinController dc) {
        this.dc = dc;
        
        // Applicatie Logo
        logo = new ImageView();
        try {
            logo.setImage(new Image((new File("src/resources/logo.jpg")).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        logo.fitHeightProperty().setValue(233.0);

        // MenuPanel
        menuPanel = new GridPane();
        menuPanel.setPrefWidth(1000);
        menuPanel.setLayoutY(230);
        //menuPanel.getColumnConstraints().add(new ColumnConstraints(250));

        // Label
        Label lblTitle = new Label("Taijitan");
        lblTitle.setFont(new Font("Arial Black", 96.0));
        lblTitle.setTextFill(Paint.valueOf("#393980"));
        lblTitle.setLayoutY(242);
        lblTitle.setPadding(new Insets(0, 0, 20, 0));
        menuPanel.add(lblTitle, 0, 0, 2, 1);
        menuPanel.setHgap(50);
        GridPane.setHalignment(lblTitle, HPos.CENTER);

        // Buttons
        createButton("Gebruikers", 0, 1).setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new GuiGebruikerController(dc.getGebruikerController()), this.getHeight(), this.getWidth());
        });
        createButton("Lesmateriaal", 1, 1).setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new GuiLesmateriaalController(dc.getLesmateriaalController()), this.getHeight(), this.getWidth());
        });
        createButton("Activiteiten", 2, 1).setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new GuiActiviteitController(dc.getActiviteitenController()), this.getHeight(), this.getWidth());
        });
        createButton("Overzichten", 3, 1).setOnAction((ActionEvent t) -> {
            dc.getGuiController().setPane(new OverzichtPanelController(dc.getOverzichtController(), dc.getGebruikerController(), dc.getActiviteitenController(), dc.getRaadplegingController(), dc.getLesmateriaalController(), 3), this.getHeight(), this.getWidth());
        });
        this.getChildren().addAll(logo, menuPanel);
    }

    private Button createButton(String text, int x, int y) {
        Button btn = new Button(text);
        btn.getStyleClass().addAll("lg", "primary");
        btn.setPrefWidth(150);
        menuPanel.add(btn, x, y);
        GridPane.setHalignment(btn, HPos.CENTER);
        return btn;
    }

    @Override
    public void resizeWidth(double width) {
        logo.fitWidthProperty().setValue(width);
        menuPanel.setLayoutX(width / 2 - 400);
    }

    @Override
    public void resizeHeight(double height) {
        // No resizeHeight actions just yet
    }
}
