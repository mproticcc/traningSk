import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { RouterModule } from '@angular/router';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';

const COMPONENTS = [LoginComponent, RegistrationComponent];

@NgModule({
  declarations: [...COMPONENTS],
  imports: [CommonModule, RouterModule, ReactiveFormsModule, FormsModule],
  exports: [...COMPONENTS],
})
export class CoreModule {}
