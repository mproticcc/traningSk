package raf.repository;

import raf.domain.TipTreninga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipTreningaRepository extends JpaRepository<TipTreninga,Long> {
    // TODO do all finds
    Optional<TipTreninga> findByNazivTipa(String tip);
}
