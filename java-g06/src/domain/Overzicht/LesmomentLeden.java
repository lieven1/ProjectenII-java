/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Overzicht;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author boris
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "LesmomentLeden.findAll",
            query = "Select g from LesmomentLeden g")
})
@Table(name = "LesmomentLeden")
public class LesmomentLeden implements Serializable {

    @Id
    @Column(name = "LesmomentId")
    private String LesmomentId;
    @Id
    @Column(name = "Gebruikersnaam")
    private String Gebruikersnaam;
    @Column(name = "Ingeschreven")
    private String Ingeschreven;
    @Column(name = "Aanwezig")
    private String Aanwezig;
    @Column(name = "FormuleId")
    private String FormuleId;

    public String getLesmomentId() {
        return LesmomentId;
    }

    public String getGebruikersnaam() {
        return Gebruikersnaam;
    }

    public String getIngeschreven() {
        return Ingeschreven;
    }

    public String getAanwezig() {
        return Aanwezig;
    }

    public String getFormuleId() {
        return FormuleId;
    }

    public void setLesmomentId(String LesmomentId) {
        this.LesmomentId = LesmomentId;
    }

    public void setGebruikersnaam(String Gebruikersnaam) {
        this.Gebruikersnaam = Gebruikersnaam;
    }

    public void setIngeschreven(String Ingeschreven) {
        this.Ingeschreven = Ingeschreven;
    }

    public void setAanwezig(String Aanwezig) {
        this.Aanwezig = Aanwezig;
    }

    public void setFormuleId(String FormuleId) {
        this.FormuleId = FormuleId;
    }
    
    

}
