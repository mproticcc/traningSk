import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './core/components/login/login.component';
import { RegistrationComponent } from './core/components/registration/registration.component';
import { AdminComponent } from './features/admin/admin.component';
import { ClientComponent } from './features/client/client.component';
import { ManagerComponent } from './features/manager/manager.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'registration',
    component: RegistrationComponent,
  },
  {
    path: 'admin',
    component: AdminComponent,
  },
  {
    path: 'client',
    component: ClientComponent,
  },
  {
    path: 'manager',
    component: ManagerComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
