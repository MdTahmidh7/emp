import dayjs from 'dayjs/esm';
import { IAddress } from 'app/entities/address/address.model';
import { gender } from 'app/entities/enumerations/gender.model';
import { maritalStatus } from 'app/entities/enumerations/marital-status.model';

export interface IEmployee {
  id: number;
  empName?: string | null;
  empAge?: number | null;
  empMobile?: string | null;
  empBirthDate?: dayjs.Dayjs | null;
  empGender?: keyof typeof gender | null;
  empPersonalBusiness?: boolean | null;
  empMaritalStatus?: keyof typeof maritalStatus | null;
  empExperience?: string | null;
  empNationality?: string | null;
  employeeAddress?: Pick<IAddress, 'id'> | null;
}

export type NewEmployee = Omit<IEmployee, 'id'> & { id: null };
