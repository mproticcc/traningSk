import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FiskultiranSalaService {
  private baseUrl = 'http://localhost:8085/gymservice/api/sala';

  constructor(private http: HttpClient) {}

  getAllSale(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getAllSale`, {});
  }

  updateSala(salaId: number, sala: any): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/update/${salaId}`, sala);
  }
}
