package raf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.domain.Rezervacija;
import raf.domain.Trening;

import java.util.List;
import java.util.Optional;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija,Long> {


    Page<Rezervacija> findAllByClientID(Pageable pageable,Long client_id);

    List<Rezervacija> findAllByRezervisaniTrening(Trening trening);

}
