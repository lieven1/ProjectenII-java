package Applicatie;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ApplicatieFrameController extends VBox {
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
    }
    
    public void setContentPane(Pane contentPane){
        this.contentPane = contentPane;
    }
}
