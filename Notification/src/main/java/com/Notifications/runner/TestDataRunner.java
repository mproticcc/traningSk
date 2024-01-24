package com.Notifications.runner;

import com.Notifications.domain.Notifikacija;
import com.Notifications.domain.TipNotifikacije;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.repository.NotificationTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@Component
public class TestDataRunner implements CommandLineRunner {

    private NotificationTypeRepository notificationTypeRepository;

    private NotificationRepository notificationRepository;

    @Override
    public void run(String... args) throws Exception {
        TipNotifikacije tipNotifikacije = new TipNotifikacije();
        tipNotifikacije.setNaziv("Aktivacioni email");

        TipNotifikacije tipNotifikacije1 = new TipNotifikacije();
        tipNotifikacije1.setNaziv("Promena lozinke");

        TipNotifikacije tipNotifikacije2 = new TipNotifikacije();
        tipNotifikacije2.setNaziv("Zakazivanje treninga");

        TipNotifikacije tipNotifikacije3 = new TipNotifikacije();
        tipNotifikacije3.setNaziv("Otkazivanje rezervacije za trening");

        TipNotifikacije tipNotifikacije4 = new TipNotifikacije();
        tipNotifikacije4.setNaziv("Otkazivanje treninga");

        TipNotifikacije tipNotifikacije5 = new TipNotifikacije();
        tipNotifikacije5.setNaziv("Azuriranje profila");

        TipNotifikacije tipNotifikacije6 = new TipNotifikacije();
        tipNotifikacije6.setNaziv("Aktivacija profila");

        notificationTypeRepository.save(tipNotifikacije);
        notificationTypeRepository.save(tipNotifikacije1);
        notificationTypeRepository.save(tipNotifikacije2);
        notificationTypeRepository.save(tipNotifikacije3);
        notificationTypeRepository.save(tipNotifikacije4);
        notificationTypeRepository.save(tipNotifikacije5);
        notificationTypeRepository.save(tipNotifikacije6);

        Notifikacija notifikacija = new Notifikacija();
        notifikacija.setId(Long.valueOf(1));
        notifikacija.setTipNotifikacije(tipNotifikacije1);
        notifikacija.setLink("nekiLink");
        notifikacija.setText("Neki tekst");
        notifikacija.setClientID(Long.valueOf(1));
        LocalDateTime today = LocalDateTime.now();
        notifikacija.setDatumSlanja(today);


        Notifikacija notifikacija1 = new Notifikacija();
        notifikacija1.setId(Long.valueOf(2));
        notifikacija1.setTipNotifikacije(tipNotifikacije);
        notifikacija1.setLink("nekiLink1");
        notifikacija1.setText("Neki tekst1");
        notifikacija1.setClientID(Long.valueOf(1));
        notifikacija1.setDatumSlanja(today);

        Notifikacija notifikacija2 = new Notifikacija();
        notifikacija2.setId(Long.valueOf(3));
        notifikacija2.setTipNotifikacije(tipNotifikacije2);
        notifikacija2.setLink("nekiLink2");
        notifikacija2.setText("Neki tekst2");
        notifikacija2.setClientID(Long.valueOf(1));
        notifikacija2.setDatumSlanja(today);

        Notifikacija notifikacija3 = new Notifikacija();
        notifikacija3.setId(Long.valueOf(4));
        notifikacija3.setTipNotifikacije(tipNotifikacije1);
        notifikacija3.setLink("nekiLink3");
        notifikacija3.setText("Promenjen tekst 3");
        notifikacija3.setClientID(Long.valueOf(2));
        notifikacija3.setDatumSlanja(today);

        Notifikacija notifikacija4 = new Notifikacija();
        notifikacija4.setId(Long.valueOf(5));
        notifikacija4.setTipNotifikacije(tipNotifikacije2);
        notifikacija4.setLink("nekiLink4");
        notifikacija4.setText("Promenjen tekst 4");
        notifikacija4.setClientID(Long.valueOf(2));
        notifikacija4.setDatumSlanja(today);

        Notifikacija notifikacija5 = new Notifikacija();
        notifikacija5.setId(Long.valueOf(6));
        notifikacija5.setTipNotifikacije(tipNotifikacije);
        notifikacija5.setLink("nekiLink5");
        notifikacija5.setText("Promenjen tekst 5");
        notifikacija5.setClientID(Long.valueOf(3));
        notifikacija5.setDatumSlanja(today);

        Notifikacija notifikacija6 = new Notifikacija();
        notifikacija6.setId(Long.valueOf(7));
        notifikacija6.setTipNotifikacije(tipNotifikacije1);
        notifikacija6.setLink("nekiLink6");
        notifikacija6.setText("Promenjen tekst 6");
        notifikacija6.setClientID(Long.valueOf(3));
        notifikacija6.setDatumSlanja(today);

        Notifikacija notifikacija7 = new Notifikacija();
        notifikacija7.setId(Long.valueOf(8));
        notifikacija7.setTipNotifikacije(tipNotifikacije2);
        notifikacija7.setLink("nekiLink7");
        notifikacija7.setText("Promenjen tekst 7");
        notifikacija7.setClientID(Long.valueOf(3));
        notifikacija7.setDatumSlanja(today);

        notificationRepository.save(notifikacija);
        notificationRepository.save(notifikacija1);
        notificationRepository.save(notifikacija2);
        notificationRepository.save(notifikacija3);
        notificationRepository.save(notifikacija4);
        notificationRepository.save(notifikacija5);
        notificationRepository.save(notifikacija6);
        notificationRepository.save(notifikacija7);

    }
}
