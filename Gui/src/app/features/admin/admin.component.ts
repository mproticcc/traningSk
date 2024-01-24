import { FiskultiranSalaService } from './../../core/service/fiskultiran-sala.service';
import { AdminService } from './../../core/service/admin.service';
import { ManagerService } from './../../core/service/manager.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/core/service/notification.service';
import { UserService } from 'src/app/core/service/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})
export class AdminComponent implements OnInit, OnDestroy {
  clients: any[] | undefined;
  managers: any[] | undefined;
  tipNotifikacija: any[] | undefined;
  sveNotifikacije: any[] | undefined;

  constructor(
    private userService: UserService,
    private managerService: ManagerService,
    private notifiService: NotificationService,
    private adminService: AdminService
  ) {}

  ngOnDestroy(): void {
    localStorage.removeItem('currentUser');
  }

  ngOnInit(): void {
    this.ucitajSveKlijente();
    this.ucitajSveMenadzere();
    this.ucitajSveTipoveNOtifikacija();
    this.ucitajSveNotifikacije();
  }

  banUser(user: any) {
    if (!user.isBanovan) {
      this.adminService.banUser(user.id).subscribe(
        () => {},
        (error) => {
          console.error('Greška prilikom zabrane korisnika:', error);
          this.ucitajSveKlijente();
        }
      );
    }
    if (user.isBanovan) {
      this.adminService.unBanUser(user.id).subscribe(
        () => {},
        (error) => {
          console.error('Greška prilikom zabrane korisnika:', error);
          this.ucitajSveKlijente();
        }
      );
    }
  }

  banManager(manager: any) {
    if (!manager.banovan) {
      this.adminService.banManger(manager.id).subscribe(
        () => {},
        (error) => {
          console.error('Greška prilikom zabrane korisnika:', error);
          this.ucitajSveMenadzere();
        }
      );
    }
    if (manager.banovan) {
      this.adminService.unBanManager(manager.id).subscribe(
        () => {},
        (error) => {
          console.error('Greška prilikom zabrane korisnika:', error);
          this.ucitajSveMenadzere();
        }
      );
    }
  }

  obrisiTip(tip: any) {
    this.notifiService
      .deleteTypeNoti(tip.id)
      .subscribe(() => this.ucitajSveTipoveNOtifikacija());
  }

  izmeniTip(tip: any) {
    this.notifiService
      .updateTypeNot(tip.id, tip)
      .subscribe(() => this.ucitajSveTipoveNOtifikacija());
  }
  onResetTable() {
    this.ucitajSveNotifikacije();
  }

  onInputChange(value: string) {
    const clientEmail: any = this.clients?.find(
      (user: { email: string; id: any; manager: any }) =>
        user.email.includes(value)
    );

    const managerFilter: any = this.managers?.find(
      (user: { email: any; id: any; manager: any }) =>
        user.email.includes(value)
    );
    console.log(managerFilter);
    if (value.trim() === '') {
      this.ucitajSveNotifikacije();
    } else {
      this.sveNotifikacije = this.sveNotifikacije?.filter((notifikacija) => {
        return (
          notifikacija.tekst.toLowerCase().includes(value.toLowerCase()) ||
          notifikacija.tipNotifikacije.naziv
            .toLowerCase()
            .includes(value.toLowerCase()) ||
          notifikacija.clinetID == clientEmail?.id ||
          notifikacija.clinetID == managerFilter?.id
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

  filterByTimeRange() {
    this.sveNotifikacije = this.sveNotifikacije?.filter((notifikacija) => {
      const notifikacijaVreme = new Date(notifikacija.vremeSlanja).getTime();
      const minVreme = this.minTime ? new Date(this.minTime).getTime() : 0;
      const maxVreme = this.maxTime
        ? new Date(this.maxTime).getTime()
        : Number.MAX_SAFE_INTEGER;
      if (minVreme == 0 || maxVreme == 0) {
        return this.ucitajSveNotifikacije;
      }
      console.log(notifikacijaVreme);
      return notifikacijaVreme >= minVreme && notifikacijaVreme <= maxVreme;
    });
  }

  private ucitajSveNotifikacije() {
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
  private ucitajSveKlijente() {
    this.userService.getAllClients().subscribe(
      (data) => {
        this.clients = data.content;
      },
      (error) => {
        console.log(error);
        console.log('Ne uspesno');
      }
    );
  }

  private ucitajSveMenadzere() {
    this.managerService.getAllManagers().subscribe(
      (data) => {
        this.managers = data.content;
      },
      (error) => {
        console.log(error);
        console.log('Ne uspesno');
      }
    );
  }

  private ucitajSveTipoveNOtifikacija() {
    this.notifiService.getAllNotiTypes().subscribe(
      (data) => {
        this.tipNotifikacija = data.content;
      },
      (error) => {
        console.log(error);
        console.log('Ne uspesno');
      }
    );
  }
}
