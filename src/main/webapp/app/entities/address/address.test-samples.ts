import { IAddress, NewAddress } from './address.model';

export const sampleWithRequiredData: IAddress = {
  id: 57999,
};

export const sampleWithPartialData: IAddress = {
  id: 7649,
};

export const sampleWithFullData: IAddress = {
  id: 60253,
  houseNo: 84840,
  roadNo: 27107,
  block: 'set override Market',
  section: 33845,
  division: 'unbearably Table than',
};

export const sampleWithNewData: NewAddress = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
