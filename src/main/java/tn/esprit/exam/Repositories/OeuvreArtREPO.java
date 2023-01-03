package tn.esprit.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.exam.Entities.Direction;
import tn.esprit.exam.Entities.OeuvreArt;

import java.util.List;

public interface OeuvreArtREPO extends JpaRepository<OeuvreArt,Long> {

    @Query("SELECT o.titreTableau from OeuvreArt o " +
            "where o.zone.musee.idMusee= :idMusee " +
            "and o.zone.direction= :direction")
    List<String> titreTableauxParMuseeEtDirection(@PathVariable Long idMusee, @PathVariable Direction direction);
}
