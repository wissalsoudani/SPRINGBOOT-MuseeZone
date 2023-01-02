package tn.esprit.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.exam.Entities.Personnel;

public interface PersonnelREPO extends JpaRepository<Personnel,Long> {
}
