package tn.esprit.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.exam.Entities.OeuvreArt;

public interface OeuvreArtREPO extends JpaRepository<OeuvreArt,Long> {
}
