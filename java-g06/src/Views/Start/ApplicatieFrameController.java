package Views.Start;

import controllers.GebruikerController;
import Views.Gebruiker.GebruikerBeheerPanelController;
import Views.Panel;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicatieFrameController extends VBox implements Panel {
    
    private Pane contentPane;
    private GebruikerController gc;
    
    public ApplicatieFrameController(GebruikerController gc){
        // MenuBar
        MenuBar mainMenu = new MenuBar();
        VBox.setVgrow(mainMenu, Priority.NEVER);
        mainMenu.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: #999;");
        Menu appMenu = new Menu("Applicatie");
        MenuItem appMenu1 = new MenuItem("Startpagina");
        MenuItem appMenu2 = new MenuItem("Afsluiten");
        Menu gebMenu = new Menu("Gebruiker");
        MenuItem gebMenu1 = new MenuItem("Beheren");
        // --
        appMenu.getItems().addAll(appMenu1, appMenu2);
        gebMenu.getItems().addAll(gebMenu1);
        mainMenu.getMenus().addAll(appMenu, gebMenu);
        this.getChildren().add(mainMenu);
        
        this.gc = gc;
        
        // contentPane
        contentPane = new ApplicatieStartPanel(this, gc);
        this.getChildren().add(contentPane);
        
        // EventHandlers
        appMenu1.setOnAction((ActionEvent t) -> {
            setContentPane(new ApplicatieStartPanel(this, gc));
        });
        appMenu2.setOnAction((ActionEvent t) -> {
            // Voeg confirmationknop toe
            ((Stage)this.getScene().getWindow()).close();
        });
        gebMenu1.setOnAction((ActionEvent t) -> {
            setContentPane(new GebruikerBeheerPanelController(this, gc));
        });
    }
    
    public void setContentPane(Pane contentPane){
        this.contentPane = contentPane;
        this.getChildren().set(1, contentPane);
    }

    @Override
    public void resizeWidth(double width) {
        ((Panel)contentPane).resizeWidth(width);
    }

    @Override
    public void resizeHeight(double height) {
        ((Panel)contentPane).resizeHeight(height);
    }
}