import { ManagerService } from './../../core/service/manager.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FiskultiranSalaService } from 'src/app/core/service/fiskultiran-sala.service';
import { NotificationService } from 'src/app/core/service/notification.service';
import { TreningService } from 'src/app/core/service/trening.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss'],
})
export class ManagerComponent implements OnInit, OnDestroy {
  sveNotifikacije: any[] | undefined;
  trenizni: any[] | undefined;
  sveSale: any[] | undefined;

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
    private managerService: ManagerService,
    private fiskulturnaSalaS: FiskultiranSalaService,
    private treningService: TreningService
  ) {}

  ngOnInit(): void {
    this.loadUserFromLocalStorage();
    this.ucitajSveSale();
    this.ucitajSveNotifikacijeManager();
    this.ucitajSveTreninge();
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

  changeMangerData(user: any) {
    this.managerService.updateProfile(this.user.id, user).subscribe();
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

  saveUserToLocalStorage() {
    localStorage.setItem('currentUser', JSON.stringify(this.user));
  }

  izmeni(sala: any) {
    console.log(sala);
    this.fiskulturnaSalaS
      .updateSala(sala.id, sala)
      .subscribe(() => this.ucitajSveSale());
  }

  onInputChange(value: string) {
    if (value.trim() === '') {
      this.ucitajSveNotifikacijeManager();
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
    this.ucitajSveNotifikacijeManager();
  }
  onOtkaziTrening(tre: any) {
    this.treningService.deleteTrainig(tre.id).subscribe();
    this.ucitajSveTreninge();
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

  private ucitajSveNotifikacijeManager() {
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

  private ucitajSveSale() {
    this.fiskulturnaSalaS.getAllSale().subscribe(
      (data) => {
        console.log(data.content);
        this.sveSale = data.content?.filter(
          (tre: any) => tre.manager_id === this.user.id
        );
      },
      (error) => {
        console.log(error);
        console.log('Ne uspesno');
      }
    );
  }

  private ucitajSveTreninge() {
    this.treningService.getAllTrainigs().subscribe(
      (data) => {
        this.trenizni = data.content?.filter(
          (tre: any) => tre.sala.manager_id === this.user.id
        );
      },
      (error) => {
        console.log(error);
        console.log('Ne uspesno');
      }
    );
  }
}
