import { Component } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { ManagerService } from '../../service/manager.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
})
export class RegistrationComponent {
  userDetails: any = {};

  constructor(
    private userService: UserService,
    private router: Router,
    private managerService: ManagerService
  ) {}

  onSubmit(): void {
    if (this.userDetails.role == 'ROLE_CLIENT') {
      this.userService.saveClient(this.userDetails).subscribe(
        (response) => {
          this.router.navigate([`../login/`]);
          console.log('Registracija uspešna!', response);
          alert('Uspesna registracija, molimo vas potvrdite registraciju');
        },
        (error) => {
          console.error('Greška prilikom registracije:', error);
        }
      );
    } else {
      this.managerService.saveClient(this.userDetails).subscribe(
        (response) => {
          this.router.navigate([`../login/`]);
          console.log('Registracija uspešna!', response);
          alert('Uspesna registracija, molimo vas potvrdite registraciju');
        },
        (error) => {
          console.error('Greška prilikom registracije:', error);
        }
      );
    }
  }
}
