import { Component, OnDestroy, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/core/service/notification.service';
import { RezervacijaService } from 'src/app/core/service/rezervacija.service';
import { TreningService } from 'src/app/core/service/trening.service';
import { UserService } from 'src/app/core/service/user.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss'],
})
export class ClientComponent implements OnInit, OnDestroy {
  sveNotifikacije: any[] | undefined;

  trenizni: any[] | undefined;
  trenizniSlobodni: any[] | undefined;
  rezervacije: any | undefined;
  tipoviTreninga: any | undefined;

  user: any = {
    username: '',
    password: '',
    email: '',
    datumRodjenja: '',
    firstName: '',
    lastName: '',
  };

  constructor(
    private notifiService: NotificationService,
    private userService: UserService,
    private treningService: TreningService,
    private rezervacijaService: RezervacijaService
  ) {}

  ngOnInit(): void {
    this.loadUserFromLocalStorage();
    this.ucitajSveNotifikacijeKorinsik();
    this.ucitajSveSlobodneTreninge();
    this.ucitajSveRezervacijeZaKorisnika();
  }

  ngOnDestroy(): void {
    localStorage.removeItem('currentUser');
  }

  loadUserFromLocalStorage() {
    const storedUser = localStorage.getItem('currentUser');

    if (storedUser) {
      this.user = JSON.parse(storedUser);
      const userData = {
        id: this.user.id,
        username: this.user.username,
        password: this.user.password,
        email: this.user.email,
        firstName: this.user.firstName,
        lastName: this.user.lastName,
        datumRodjenja: this.user.datumRodjenja,
      };
      this.user = {
        ...this.user,
        ...userData,
      };
    }
  }

  saveUserToLocalStorage() {
    localStorage.setItem('currentUser', JSON.stringify(this.user));
  }

  onInputChange(value: string) {
    if (value.trim() === '') {
      this.ucitajSveNotifikacijeKorinsik();
    } else {
      this.sveNotifikacije = this.sveNotifikacije?.filter((notifikacija) => {
        return (
          notifikacija.tekst.toLowerCase().includes(value.toLowerCase()) ||
          notifikacija.tipNotifikacije.naziv
            .toLowerCase()
            .includes(value.toLowerCase())
        );
      });
    }
  }

  onInputFilter(value: string) {
    if (value.trim() === '') {
      this.ucitajSveSlobodneTreninge();
    } else if ('grupni'.includes(value)) {
      this.trenizniSlobodni = this.trenizniSlobodni?.filter((data) => {
        return data.grupni == true;
      });
    } else if ('individualni'.includes(value)) {
      this.trenizniSlobodni = this.trenizniSlobodni?.filter((data) => {
        return data.grupni == false;
      });
    } else {
      this.trenizniSlobodni = this.trenizniSlobodni?.filter((data) => {
        return data.tip.nazivTipa.toLowerCase().includes(value.toLowerCase());
      });
    }
  }

  changeClientData(user: any) {
    this.userService.updateProfile(this.user.id, user).subscribe();
    let storedData = JSON.parse(localStorage.getItem('currentUser')!);

    storedData.datumRodjenja = user.datumRodjenja;
    storedData.email = user.email;
    storedData.firstName = user.firstName;
    storedData.lastName = user.lastName;
    storedData.password = user.password;
    storedData.username = user.username;

    localStorage.setItem('currentUser', JSON.stringify(storedData));
    this.loadUserFromLocalStorage();
  }

  minTime: string = '';
  maxTime: string = '';

  onMinTimeChange(value: string) {
    this.minTime = value;
    this.filterByTimeRange();
  }

  onMaxTimeChange(value: string) {
    this.maxTime = value;
    this.filterByTimeRange();
  }

  onResetTable() {
    this.ucitajSveNotifikacijeKorinsik();
  }

  onOtkaziRezervaciju(rez: any) {
    this.rezervacijaService
      .deleteReservation(rez.rezervacija_id, this.user.id)
      .subscribe();
    this.ucitajSveRezervacijeZaKorisnika();
    this.ucitajSveNotifikacijeKorinsik();
  }

  onZakaziRezervaciju(trening: any) {
    const treningObjekat: any = {
      rezervisaniTrening: {
        trening_id: trening.id,
        sala: {
          sala_id: trening.sala.sala_id,
          name: trening.sala.name,
          kapacitet: trening.sala.kapacitet,
          loyalty: trening.sala.loyalty,
          manager_id: trening.sala.manager_id,
          opis: trening.sala.opis,
          brojTrenera: trening.sala.brojTrenera,
        },
        tip: {
          tipTreninga_id: trening.tip.tipTreninga_id,
          nazivTipa: trening.tip.nazivTipa,
        },
        cenaTreninga: trening.cenaTreninga,
        brRezervacija: trening.brRezervacija,
        grupni: trening.grupni,
        // terminTreninga: trening.terminTreninga,
        // pocetakTermina: trening.pocetakTermina,
        // krajTermina: trening.krajTermina,
      },
      cenaTreninga: trening.cenaTreninga,
      clientID: this.user.id,
    };
    this.rezervacijaService.addNewReservation(treningObjekat).subscribe();
    this.ucitajSveRezervacijeZaKorisnika();
    this.ucitajSveNotifikacijeKorinsik();
  }

  filterByTimeRange() {
    this.sveNotifikacije = this.sveNotifikacije?.filter((notifikacija) => {
      const notifikacijaVreme = new Date(notifikacija.vremeSlanja).getTime();
      const minVreme = this.minTime ? new Date(this.minTime).getTime() : 0;
      const maxVreme = this.maxTime
        ? new Date(this.maxTime).getTime()
        : Number.MAX_SAFE_INTEGER;
      return notifikacijaVreme >= minVreme && notifikacijaVreme <= maxVreme;
    });
  }

  private ucitajSveNotifikacijeKorinsik() {
    this.notifiService.getAllNotifications().subscribe(
      (data) => {
        this.sveNotifikacije = data.content;
      },
      (error) => {
        console.log(error);
        console.log('Ne uspesno');
      }
    );
  }

  private ucitajSveRezervacijeZaKorisnika() {
    this.rezervacijaService.getAllReservations().subscribe(
      (data) => {
        this.rezervacije = data.content?.filter(
          (client: any) => client.clientID == this.user.id
        );
      },
      (error) => {
        console.log(error);
        console.log('Ne uspesno');
      }
    );
    this.treningService.getAllTrainigs().subscribe(
      (data) => {
        this.rezervacije.forEach((rezervacija: any) => {
          data.content?.forEach((res: any) => {
            if (res.id === rezervacija.id) {
              this.trenizni = this.trenizni ? [...this.trenizni, res] : [res];
            }
          });
        });
      },
      (error) => {
        console.log(error);
        console.log('Ne uspesno');
      }
    );
  }

  private ucitajSveSlobodneTreninge() {
    this.treningService.getAllTrainigs().subscribe(
      (data) => {
        this.trenizniSlobodni = data.content?.filter(
          (trening: any) => trening.brRezervacija < trening.sala.kapacitet
        );
      },
      (error) => {
        console.log(error);
        console.log('Ne uspešno');
      }
    );
  }
}
