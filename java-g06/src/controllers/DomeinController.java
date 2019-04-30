package controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.Pane;

// Collection van controllers
public class DomeinController {
    private Pane contentPane;
    private Map<String, Double> dimensions;
            
    private PropertyChangeSupport subject;
    
    private static GebruikerController _gebruikerController;
    private static ActiviteitenController _activiteitenController;
    
    public DomeinController(){
        subject = new PropertyChangeSupport(this);
        dimensions = new HashMap();
    }
    
    // Controllers
    public GebruikerController getGebruikerController(){
        if(_gebruikerController == null)
            _gebruikerController = new GebruikerController();
        return _gebruikerController;
    }
    
    // Andere controllers ....
    
    
    
    
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
