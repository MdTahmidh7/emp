export interface IAddress {
  id: number;
  houseNo?: number | null;
  roadNo?: number | null;
  block?: string | null;
  section?: number | null;
  division?: string | null;
}

export type NewAddress = Omit<IAddress, 'id'> & { id: null };
