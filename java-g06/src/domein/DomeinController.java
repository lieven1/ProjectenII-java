package domein;

import java.util.List;
import java.util.stream.Collectors;
import persistentie.PersistentieController;

public class DomeinController {

    // Declarations
    private List<AGebruiker> _gebruikers;

    // Constructors
    public DomeinController() {
        _gebruikers = new PersistentieController().getGebruikers();
    }

    // Methods
    // RepositoryMethods Gebruiker
    public AGebruiker getByGebruikersnaam(String gebruikersnaam) {
        return _gebruikers.stream().filter(g -> g.getGebruikersnaam().equals(gebruikersnaam)).findAny().orElse(null);
    }
    
    public List<AGebruiker> getAllGebruikers() {
        return _gebruikers;
    }

    public List<Gebruiker> getGebruikers() {
        return _gebruikers.stream().filter(Gebruiker.class::isInstance)
                .map(Gebruiker.class::cast).collect(Collectors.toList());
    }

    public List<ProefGebruiker> getProefGebruikers() {
        return _gebruikers.stream().filter(ProefGebruiker.class::isInstance)
                .map(ProefGebruiker.class::cast).collect(Collectors.toList());
    }

    public void addGebruiker(Gebruiker gebruiker) {
        if (getGebruikers().stream().anyMatch(g -> g.getGebruikersnaam().equals(gebruiker.getGebruikersnaam()) || g.getRijksregisternummer().equals(gebruiker.getRijksregisternummer()))) {
            throw new IllegalArgumentException("Gebruikersnaam en rijksregisternummer moeten uniek zijn.");
        }
        _gebruikers.add(gebruiker);
    }

    public void addProefGebruiker(Gebruiker gebruiker) {
        if (_gebruikers.stream().anyMatch(g -> g.getGebruikersnaam().equals(gebruiker.getGebruikersnaam()))) {
            throw new IllegalArgumentException("Gebruikersnaam moet uniek zijn.");
        }
        _gebruikers.add(gebruiker);
    }

    public void deleteGebruiker(AGebruiker gebruiker) {
        if (!_gebruikers.contains(gebruiker)) {
            throw new IllegalArgumentException("De gebruiker die u wilt verwijderen behoort niet meer tot de database.");
        }
        _gebruikers.remove(gebruiker);
    }

    public void updateGebruiker(AGebruiker gebruiker) {
        if (_gebruikers.stream().noneMatch(g -> g.getGebruikersnaam().equals(gebruiker.getGebruikersnaam()))) {
            throw new IllegalArgumentException("De gebruiker die u wilt wijzigen behoort niet tot de database.");
        }

        for (AGebruiker tempGebruiker : _gebruikers) {
            if (tempGebruiker != null && tempGebruiker.getGebruikersnaam().equals(gebruiker.getGebruikersnaam())) {
                tempGebruiker = gebruiker;
                break;
            }
        }
    }
}
