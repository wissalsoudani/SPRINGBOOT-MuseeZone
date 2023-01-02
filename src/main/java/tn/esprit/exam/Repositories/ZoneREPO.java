package tn.esprit.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.exam.Entities.Zone;

import java.util.List;

public interface ZoneREPO extends JpaRepository<Zone,Long> {

    List<Zone>  findByActif(boolean b);
}
