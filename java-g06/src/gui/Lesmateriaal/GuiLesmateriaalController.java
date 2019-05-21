package gui.Lesmateriaal;

import domain.controllers.LesmateriaalController;
import gui.Panel;
import javafx.scene.layout.HBox;

public class GuiLesmateriaalController extends HBox implements Panel {
    private final LesmateriaalListPanel listPanel;
    private final FormLesmateriaal detailPanel;
    
    public GuiLesmateriaalController(LesmateriaalController lc){
        // Panels
        listPanel = new LesmateriaalListPanel(lc);
        detailPanel = new FormLesmateriaal(lc);
        
        this.getChildren().addAll(listPanel, detailPanel);
        lc.veranderFilter("", null, null);
    }
    
    
    @Override
    public void resizeWidth(double width) {
        listPanel.setPrefWidth(width*0.2);
    }

    @Override
    public void resizeHeight(double height) {
    }
}
