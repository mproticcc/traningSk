package raf.repository;

import raf.domain.FiskulturnaSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiskulturnaSalaRepository extends JpaRepository<FiskulturnaSala,Long> {

    // TODO do finds
    Optional<FiskulturnaSala> findByNaziv(String name);

}
