import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RezervacijaService {
  private baseUrl = 'http://localhost:8085/gymservice/api/reservation';

  constructor(private http: HttpClient) {}

  getAllReservations(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getAllReservation`, {});
  }

  deleteReservation(IDRez: number, clientId: number): Observable<any> {
    return this.http.delete<any>(
      `${this.baseUrl}/client/${clientId}/${IDRez}`,
      {}
    );
  }

  addNewReservation(reservation: any): Observable<any> {
    return this.http.post<any>(
      `${this.baseUrl}/registerReservation`,
      reservation
    );
  }
}
