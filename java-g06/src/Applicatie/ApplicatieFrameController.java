package Applicatie;

import Gebruiker.GebruikerBeheerPanel;
import Gebruiker.GebruikerToevoegenPanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicatieFrameController extends VBox implements Panel {
    Pane contentPane;
    
    public ApplicatieFrameController(){
        // MenuBar
        MenuBar mainMenu = new MenuBar();
        VBox.setVgrow(mainMenu, Priority.NEVER);
        Menu appMenu = new Menu("Applicatie");
        MenuItem appMenu1 = new MenuItem("Startpagina");
        MenuItem appMenu2 = new MenuItem("Afsluiten");
        Menu gebMenu = new Menu("Gebruiker");
        MenuItem gebMenu1 = new MenuItem("Beheren");
        MenuItem gebMenu2 = new MenuItem("Toevoegen");
        // --
        appMenu.getItems().addAll(appMenu1, appMenu2);
        gebMenu.getItems().addAll(gebMenu1, gebMenu2);
        mainMenu.getMenus().addAll(appMenu, gebMenu);
        this.getChildren().add(mainMenu);
        
        // contentPane
        contentPane = new ApplicatieStartPanel(this);
        this.getChildren().add(contentPane);
        
        // EventHandlers
        appMenu1.setOnAction((ActionEvent t) -> {
            setContentPane(new ApplicatieStartPanel(this));
        });
        appMenu2.setOnAction((ActionEvent t) -> {
            ((Stage)this.getScene().getWindow()).close();
        });
        gebMenu1.setOnAction((ActionEvent t) -> {
            setContentPane(new GebruikerBeheerPanel(this));
        });
        gebMenu2.setOnAction((ActionEvent t) -> {
            setContentPane(new GebruikerToevoegenPanel(this));
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
