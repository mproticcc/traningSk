package raf.runner;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import raf.domain.FiskulturnaSala;
import raf.domain.Rezervacija;
import raf.domain.TipTreninga;
import raf.domain.Trening;
import raf.repository.FiskulturnaSalaRepository;
import raf.repository.RezervacijaRepository;
import raf.repository.TipTreningaRepository;
import raf.repository.TreningRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Component
public class TestDataRunner implements CommandLineRunner {
    private FiskulturnaSalaRepository fiskulturnaSalaRepository;

    private TipTreningaRepository tipTreningaRepository;

    private TreningRepository treningRepository;

    private RezervacijaRepository rezervacijaRepository;

    @Override
    public void run(String... args) throws Exception {
        FiskulturnaSala fiskulturnaSala = new FiskulturnaSala();
        fiskulturnaSala.setKapacitet(20);
        fiskulturnaSala.setNaziv("Ahilej");
        fiskulturnaSala.setBrojTrenera(5);
        fiskulturnaSala.setLoyalty(2);
        fiskulturnaSala.setOpis("Neki opis vezan za salu");
        fiskulturnaSala.setManager_id(Long.valueOf(1));

        FiskulturnaSala fiskulturnaSala2 = new FiskulturnaSala();
        fiskulturnaSala2.setKapacitet(15);
        fiskulturnaSala2.setNaziv("Naziv druge sale");
        fiskulturnaSala2.setBrojTrenera(4);
        fiskulturnaSala2.setLoyalty(3);
        fiskulturnaSala2.setOpis("Opis druge sale");
        fiskulturnaSala2.setManager_id(Long.valueOf(1));

        FiskulturnaSala fiskulturnaSala3 = new FiskulturnaSala();
        fiskulturnaSala3.setKapacitet(25);
        fiskulturnaSala3.setNaziv("Naziv treće sale");
        fiskulturnaSala3.setBrojTrenera(6);
        fiskulturnaSala3.setLoyalty(1);
        fiskulturnaSala3.setOpis("Opis treće sale");
        fiskulturnaSala3.setManager_id(Long.valueOf(3));


        FiskulturnaSala fiskulturnaSala4 = new FiskulturnaSala();
        fiskulturnaSala4.setKapacitet(18);
        fiskulturnaSala4.setNaziv("Naziv četvrte sale");
        fiskulturnaSala4.setBrojTrenera(3);
        fiskulturnaSala4.setLoyalty(4);
        fiskulturnaSala4.setOpis("Opis četvrte sale");
        fiskulturnaSala4.setManager_id(Long.valueOf(2));

        fiskulturnaSalaRepository.save(fiskulturnaSala);
        fiskulturnaSalaRepository.save(fiskulturnaSala2);
        fiskulturnaSalaRepository.save(fiskulturnaSala3);
        fiskulturnaSalaRepository.save(fiskulturnaSala4);



        TipTreninga tipTreninga = new TipTreninga();
        tipTreninga.setNazivTipa("joga");
        TipTreninga tipTreninga1 = new TipTreninga();
        tipTreninga1.setNazivTipa("kalistenika");
        TipTreninga tipTreninga2 = new TipTreninga();
        tipTreninga2.setNazivTipa("power lifting");
        TipTreninga tipTreninga3 = new TipTreninga();
        tipTreninga3.setNazivTipa("fitnes");

        tipTreningaRepository.save(tipTreninga);
        tipTreningaRepository.save(tipTreninga1);
        tipTreningaRepository.save(tipTreninga2);
        tipTreningaRepository.save(tipTreninga3);

        Trening trening = new Trening();
        trening.setSala(fiskulturnaSala);
        trening.setTip(tipTreninga1);
        trening.setGrupni(true);
        trening.setTerminTreninga(LocalDate.of(2024,1,15));
        trening.setPocetakTermina(LocalTime.of(15,15));
        trening.setKrajTermina(LocalTime.of(17,15));
        trening.setCenaTreninga(1500);

        Trening trening1 = new Trening();
        trening1.setSala(fiskulturnaSala);
        trening1.setTip(tipTreninga2);
        trening1.setGrupni(true);
        trening1.setTerminTreninga(LocalDate.of(2024,1,15));
        trening1.setPocetakTermina(LocalTime.of(15,15));
        trening1.setKrajTermina(LocalTime.of(16,15));
        trening1.setCenaTreninga(1300);

        Trening trening2 = new Trening();
        trening2.setSala(fiskulturnaSala2);
        trening2.setTip(tipTreninga1);
        trening2.setGrupni(false);
        trening2.setTerminTreninga(LocalDate.of(2024, 1, 16));
        trening2.setPocetakTermina(LocalTime.of(16, 15));
        trening2.setKrajTermina(LocalTime.of(17, 15));
        trening2.setCenaTreninga(1400);

        Trening trening3 = new Trening();
        trening3.setSala(fiskulturnaSala3);
        trening3.setTip(tipTreninga3);
        trening3.setGrupni(true);
        trening3.setTerminTreninga(LocalDate.of(2024, 1, 17));
        trening3.setPocetakTermina(LocalTime.of(17, 15));
        trening3.setKrajTermina(LocalTime.of(18, 15));
        trening3.setCenaTreninga(1200);


        Trening trening4 = new Trening();
        trening4.setSala(fiskulturnaSala4);
        trening4.setTip(tipTreninga1);
        trening4.setGrupni(false);
        trening4.setTerminTreninga(LocalDate.of(2024, 1, 18));
        trening4.setPocetakTermina(LocalTime.of(14, 30));
        trening4.setKrajTermina(LocalTime.of(15, 30));
        trening4.setCenaTreninga(1100);

        treningRepository.save(trening);
        treningRepository.save(trening1);
        treningRepository.save(trening2);
        treningRepository.save(trening3);
        treningRepository.save(trening4);

        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setClientID(Long.valueOf(1));
        rezervacija.setCenaTreninga(1500);
        rezervacija.setRezervisaniTrening(trening);

        Rezervacija rezervacija2 = new Rezervacija();
        rezervacija2.setClientID(Long.valueOf(1));
        rezervacija2.setCenaTreninga(1300);
        rezervacija2.setRezervisaniTrening(trening2);

        Rezervacija rezervacija3 = new Rezervacija();
        rezervacija3.setClientID(Long.valueOf(2));
        rezervacija3.setCenaTreninga(1200);
        rezervacija3.setRezervisaniTrening(trening3);

        Rezervacija rezervacija4 = new Rezervacija();
        rezervacija4.setClientID(Long.valueOf(3));
        rezervacija4.setCenaTreninga(1100);
        rezervacija4.setRezervisaniTrening(trening4);


        rezervacijaRepository.save(rezervacija);
        rezervacijaRepository.save(rezervacija2);
        rezervacijaRepository.save(rezervacija3);
        rezervacijaRepository.save(rezervacija4);
    }
}
