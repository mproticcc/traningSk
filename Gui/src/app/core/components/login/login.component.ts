import { Component } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { ManagerService } from '../../service/manager.service';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  user: any = {};

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(
    private userService: UserService,
    private router: Router,
    private managerService: ManagerService,
    private adminService: AdminService
  ) {}

  onSubmit(): void {
    this.adminService.getAllAdmins().subscribe(
      (data) => {
        this.user = data;
        let email = this.loginForm.value.email;
        let pass = this.loginForm.value.password;
        let userData: any = data.content.find(
          (user: { email: string; password: string }) =>
            user.email === email && user.password === pass
        );
        if (userData) {
          localStorage.setItem('currentUser', JSON.stringify(userData));
          this.router.navigate([`../admin/`]);
        } else {
          console.log('Korisnik nije pronađen.');
        }
      },
      (error) => {
        console.log(error);
        console.log('Neuspešno');
      }
    );

    this.managerService.getAllManagers().subscribe(
      (data) => {
        this.user = data;
        let email = this.loginForm.value.email;
        let pass = this.loginForm.value.password;
        let userData: any = data.content.find(
          (user: { email: string; password: string; banovan: boolean }) =>
            user.email === email &&
            user.password === pass &&
            user.banovan == false
        );

        if (userData) {
          localStorage.setItem('currentUser', JSON.stringify(userData));
          this.router.navigate([`../manager/`]);
        } else {
          console.log('Korisnik nije pronađen.');
        }
      },
      (error) => {
        console.log(error);
        console.log('Neuspešno');
      }
    );

    this.userService.getAllClients().subscribe(
      (data) => {
        this.user = data;
        let email = this.loginForm.value.email;
        let pass = this.loginForm.value.password;
        let userData: any = data.content.find(
          (user: { email: string; password: string; isBanovan: boolean }) =>
            user.email === email &&
            user.password === pass &&
            user.isBanovan == false
        );

        if (userData) {
          localStorage.setItem('currentUser', JSON.stringify(userData));
          this.router.navigate([`../client/`]);
        } else {
          console.log('Korisnik nije pronađen.');
        }
      },
      (error) => {
        console.log(error);
        console.log('Neuspešno');
      }
    );
  }
}
