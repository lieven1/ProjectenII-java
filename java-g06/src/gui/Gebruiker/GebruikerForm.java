package gui.Gebruiker;

import domain.GebruikerModels.AGebruiker;
import javafx.scene.control.ScrollPane;

public abstract class GebruikerForm extends ScrollPane {
    public abstract void loadGebruiker(AGebruiker gebruiker);
}
