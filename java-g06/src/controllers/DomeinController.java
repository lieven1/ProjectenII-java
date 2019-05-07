package controllers;

// Collection van controllers
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
}
