import dayjs from 'dayjs/esm';

import { gender } from 'app/entities/enumerations/gender.model';
import { maritalStatus } from 'app/entities/enumerations/marital-status.model';

import { IEmployee, NewEmployee } from './employee.model';

export const sampleWithRequiredData: IEmployee = {
  id: 48105,
};

export const sampleWithPartialData: IEmployee = {
  id: 97558,
  empMobile: 'engage',
};

export const sampleWithFullData: IEmployee = {
  id: 32643,
  empName: 'Soft Gender',
  empAge: 82434,
  empMobile: 'Silicon matrix',
  empBirthDate: dayjs('2023-06-19'),
  empGender: 'MALE',
  empPersonalBusiness: false,
  empMaritalStatus: 'UNMARRIED',
  empExperience: '70709',
  empNationality: 'Movies Volkswagen male',
};

export const sampleWithNewData: NewEmployee = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
