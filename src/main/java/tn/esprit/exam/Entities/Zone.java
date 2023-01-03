package tn.esprit.exam.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Zone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idZone;
    private String code;
    private String libelle;
    @Enumerated(EnumType.STRING)
    private Direction direction;
    private boolean actif;

    public Direction getDirection() {
        return direction;
    }


    public Personnel getDIRECTEUR() {
        return DIRECTEUR;
    }

    public void setDIRECTEUR(Personnel DIRECTEUR) {
        this.DIRECTEUR = DIRECTEUR;
    }

    public List<Personnel> getGARDIEN() {return GARDIEN;}

    public void setGARDIEN(List<Personnel> GARDIEN) {this.GARDIEN = GARDIEN;}
    @ManyToOne
    @JsonIgnore
    private Musee musee;


    @OneToMany(mappedBy = "zone")
    List<OeuvreArt> oeuvreArts;


    @OneToOne
    @JoinColumn(name = "Directeur_id")
    private Personnel DIRECTEUR;


    @OneToMany(mappedBy = "zone")
    private List<Personnel> GARDIEN;



}
