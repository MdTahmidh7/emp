import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'address',
        data: { pageTitle: 'employeeManagementApp.address.home.title' },
        loadChildren: () => import('./address/address.routes'),
      },
      {
        path: 'employee',
        data: { pageTitle: 'employeeManagementApp.employee.home.title' },
        loadChildren: () => import('./employee/employee.routes'),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
