package tn.esprit.exam.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OeuvreArt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOeuvreArt;
    private String artiste;
    private String titreTableau;

    public String getTitreTableau() {
        return titreTableau;
    }

    @ManyToOne
    @JsonIgnore
    private  Zone zones;
}
