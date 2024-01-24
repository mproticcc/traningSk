import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TreningService {
  private baseUrl = 'http://localhost:8085/gymservice/api/treninzi';

  constructor(private http: HttpClient) {}

  getAllTrainigs(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getAllTrainings`, {});
  }

  deleteTrainig(ID: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/delete/${ID}`, {});
  }
}
