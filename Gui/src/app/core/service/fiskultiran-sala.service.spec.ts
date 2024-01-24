import { TestBed } from '@angular/core/testing';

import { FiskultiranSalaService } from './fiskultiran-sala.service';

describe('FiskultiranSalaService', () => {
  let service: FiskultiranSalaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FiskultiranSalaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
