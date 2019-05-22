/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Overzicht;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Column;
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
    @NamedQuery(name = "Lesmoment.findAll",
            query = "Select g from Lesmoment g")
})
@Table(name = "Lesmoment")
public class Lesmoment implements Serializable {

    @Id
    @Column(name = "LesmomentId")
    private String LesmomentId;
    @Column(name = "StartTijd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date StartTijd;
    @Column(name = "EindTijd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date EindTijd;

    public String getLesmomentId() {
        return LesmomentId;
    }

    public Date getStartTijd() {
        return StartTijd;
    }
    
    public Date getEindTijd() {
        return EindTijd;
    }
    
     public String getStartTijdToString() {
        return (LocalDate.ofInstant(StartTijd.toInstant(), ZoneId.systemDefault())).toString();
    }

    public String getEindTijdToString() {
        return (LocalDate.ofInstant(EindTijd.toInstant(), ZoneId.systemDefault())).toString();
    }

   

}
