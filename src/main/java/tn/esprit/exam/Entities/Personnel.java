package tn.esprit.exam.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Personnel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonnel;
    private String nom;
    private String prenom;
    @Temporal(value = TemporalType.DATE)
    private Date dateDerniereAffectation;
    @Enumerated(EnumType.STRING)
    private TypePersonnel typePersonnel;

    public Zone getZone() {
        return zonedirecteur;
    }

    public void setZone(Zone zone) {
        this.zonedirecteur = zone;
    }



    @OneToOne(mappedBy = "DIRECTEUR")
    private Zone zonedirecteur;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;


}
