package gui.Lesmateriaal;

import domain.GebruikerModels.TypeGebruiker;
import domain.controllers.LesmateriaalController;
import gui.Panel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.layout.HBox;

public class GuiLesmateriaalController extends HBox implements Panel, PropertyChangeListener {
    private final LesmateriaalListPanel listPanel;
    
    private final LesmateriaalController lc;
    
    public GuiLesmateriaalController(LesmateriaalController lc){
        this.lc = lc;
        
        // Panels
        listPanel = new LesmateriaalListPanel(lc);
        
        lc.addPropertyChangeListener(this);
        this.getChildren().addAll(listPanel);
    }
    
    public void createForm(TypeGebruiker type){
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
    
    @Override
    public void resizeWidth(double width) {
        listPanel.setPrefWidth(width*0.2);
    }

    @Override
    public void resizeHeight(double height) {
    }
}
