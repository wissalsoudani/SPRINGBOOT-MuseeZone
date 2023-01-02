package tn.esprit.exam.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Musee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMusee;
    @Temporal(value = TemporalType.DATE)
    private Date dateInauguration;
    private String designation;
    private String adresse;

    @OneToMany(mappedBy = "musee")
     private List<Zone> zones;
}
