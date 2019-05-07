package controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.Pane;

public class GuiController {
    private Pane contentPane;
    private Map<String, Double> dimensions;
    private PropertyChangeSupport subject;
    
    public GuiController(){
        subject = new PropertyChangeSupport(this);
        dimensions = new HashMap<>();
    }
    
    // PaneListener
    public Pane getPane(){
        return contentPane;
    }
    
    public void setPane(Pane pane, double height, double width){
        this.setHeight(height);
        this.setWidth(width);
        
        subject.firePropertyChange("contentPane", contentPane, pane);
        contentPane = pane;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "contentPane", null, contentPane));
    }
    
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }
    
    public double getWidth(){
        return dimensions.get("Width");
    }
    
    public double getHeight(){
        return dimensions.get("Height");
    }
    
    public void setWidth(double width){
        dimensions.put("Width", width);
    }
    
    public void setHeight(double height){
        dimensions.put("Height", height);
    }
}
