package controllers;

// Collection van controllers

import gui.GuiController;

public class DomeinController {
    private static GebruikerController gebruikerController;
    private static ActiviteitenController activiteitenController;
    private static GuiController guiController;
    
    public DomeinController(){
        guiController = new GuiController();
    }
    
    // Controllers
    public GuiController getGuiController(){
        return guiController;
    }
    public GebruikerController getGebruikerController(){
        if(gebruikerController == null)
            gebruikerController = new GebruikerController();
        return gebruikerController;
    }
    
    public ActiviteitenController getActiviteitenController(){
        if(activiteitenController == null)
            activiteitenController = new ActiviteitenController(new ActiviteitenBeheerder());
        return activiteitenController;
    }
}
