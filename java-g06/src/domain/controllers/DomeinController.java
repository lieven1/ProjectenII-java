package domain.controllers;

// Collection van controllers
import controllers.GuiController;
import domain.beheerders.ActiviteitenBeheerder;

public class DomeinController {

    private static GebruikerController gebruikerController;
    private static LesmateriaalController lesmateriaalController;
    private static ActiviteitenController activiteitenController;
    private static OverzichtController overzichtController;
    private static RaadplegingController raadplegingController;
    private static GuiController guiController;

    public DomeinController() {
        guiController = new GuiController();
    }

    // Controllers
    public GuiController getGuiController() {
        return guiController;
    }

    public GebruikerController getGebruikerController() {
        if (gebruikerController == null) {
            gebruikerController = new GebruikerController();
        }
        return gebruikerController;
    }

    public LesmateriaalController getLesmateriaalController() {
        if (lesmateriaalController == null) {
            lesmateriaalController = new LesmateriaalController();
        }
        return lesmateriaalController;
    }

    public ActiviteitenController getActiviteitenController() {
        if (activiteitenController == null) {
            activiteitenController = new ActiviteitenController();
        }
        return activiteitenController;
    }

    public OverzichtController getOverzichtController() {
        if (overzichtController == null) {
            overzichtController = new OverzichtController();
        }
        return overzichtController;
    }

    public RaadplegingController getRaadplegingController() {
        if (raadplegingController == null) {
            raadplegingController = new RaadplegingController();
        }
        return raadplegingController;
    }

}
