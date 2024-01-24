package raf.repository;

import raf.domain.Trening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TreningRepository extends JpaRepository<Trening,Long> {
    // TODO: Napravi findByApppointment

    Optional<Trening> findByTerminTreninga(Date datum);
}
