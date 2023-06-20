import { IAddress, NewAddress } from './address.model';

export const sampleWithRequiredData: IAddress = {
  id: 12904,
};

export const sampleWithPartialData: IAddress = {
  id: 90906,
  roadNo: 37815,
  division: 'violet since',
};

export const sampleWithFullData: IAddress = {
  id: 25880,
  houseNo: 39147,
  roadNo: 40137,
  block: 'Incredible delectus',
  section: 95350,
  division: 'Towne yellow override',
};

export const sampleWithNewData: NewAddress = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
