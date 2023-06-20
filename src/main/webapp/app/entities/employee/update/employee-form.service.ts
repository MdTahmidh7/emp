import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IEmployee, NewEmployee } from '../employee.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEmployee for edit and NewEmployeeFormGroupInput for create.
 */
type EmployeeFormGroupInput = IEmployee | PartialWithRequiredKeyOf<NewEmployee>;

type EmployeeFormDefaults = Pick<NewEmployee, 'id' | 'empPersonalBusiness'>;

type EmployeeFormGroupContent = {
  id: FormControl<IEmployee['id'] | NewEmployee['id']>;
  empName: FormControl<IEmployee['empName']>;
  empAge: FormControl<IEmployee['empAge']>;
  empMobile: FormControl<IEmployee['empMobile']>;
  empBirthDate: FormControl<IEmployee['empBirthDate']>;
  empGender: FormControl<IEmployee['empGender']>;
  empPersonalBusiness: FormControl<IEmployee['empPersonalBusiness']>;
  empMaritalStatus: FormControl<IEmployee['empMaritalStatus']>;
  empExperience: FormControl<IEmployee['empExperience']>;
  empNationality: FormControl<IEmployee['empNationality']>;
  employeeAddress: FormControl<IEmployee['employeeAddress']>;
};

export type EmployeeFormGroup = FormGroup<EmployeeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EmployeeFormService {
  createEmployeeFormGroup(employee: EmployeeFormGroupInput = { id: null }): EmployeeFormGroup {
    const employeeRawValue = {
      ...this.getFormDefaults(),
      ...employee,
    };
    return new FormGroup<EmployeeFormGroupContent>({
      id: new FormControl(
        { value: employeeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      empName: new FormControl(employeeRawValue.empName),
      empAge: new FormControl(employeeRawValue.empAge),
      empMobile: new FormControl(employeeRawValue.empMobile),
      empBirthDate: new FormControl(employeeRawValue.empBirthDate),
      empGender: new FormControl(employeeRawValue.empGender),
      empPersonalBusiness: new FormControl(employeeRawValue.empPersonalBusiness),
      empMaritalStatus: new FormControl(employeeRawValue.empMaritalStatus),
      empExperience: new FormControl(employeeRawValue.empExperience),
      empNationality: new FormControl(employeeRawValue.empNationality),
      employeeAddress: new FormControl(employeeRawValue.employeeAddress),
    });
  }

  getEmployee(form: EmployeeFormGroup): IEmployee | NewEmployee {
    return form.getRawValue() as IEmployee | NewEmployee;
  }

  resetForm(form: EmployeeFormGroup, employee: EmployeeFormGroupInput): void {
    const employeeRawValue = { ...this.getFormDefaults(), ...employee };
    form.reset(
      {
        ...employeeRawValue,
        id: { value: employeeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): EmployeeFormDefaults {
    return {
      id: null,
      empPersonalBusiness: false,
    };
  }
}
