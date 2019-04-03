package persistentie;

import domein.AGebruiker;
import java.util.List;

public class PersistentieController {
    private GebruikerMapper _gebruikerMapper;
    
    public List<AGebruiker> getGebruikers() {
        _gebruikerMapper = new GebruikerMapper();
        return _gebruikerMapper.getAll();
    }
}
