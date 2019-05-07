package domain.LesmateriaalModels;

public final class Foto {
    private int id;
    private String bestandsnaam;
    private String extensie;
    
    /*public static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "dkbfdmful",
        "api_key", "863719494787761",
        "api_secret", "DpGFihSrXeYjXljkQiaFcaqR14c"));*/
    
    public Foto(String bestandsnaam, String extensie){
        setBestandsnaam(bestandsnaam);
        setExtensie(extensie);
    }

    public String getBestandsnaam() {
        return bestandsnaam;
    }

    public void setBestandsnaam(String bestandsnaam) {
        if(bestandsnaam == null || bestandsnaam.isBlank())
            throw new IllegalArgumentException("Bestandsnaam mag geen lege waarde bevatten.");
        
        this.bestandsnaam = bestandsnaam;
    }

    public String getExtensie() {
        return extensie;
    }

    public void setExtensie(String extensie) {
        if(extensie == null || extensie.isBlank())
            throw new IllegalArgumentException("Extensie mag geen lege waarde bevatten.");
        
        this.extensie = extensie;
    }
}
