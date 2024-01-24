import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ManagerService {
  private baseUrl = 'http://localhost:8085/userservice/api/manager';

  constructor(private http: HttpClient) {}

  getAllManagers(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getAllManagers`, {});
  }

  saveClient(clientData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/registerManager`, clientData);
  }

  updateProfile(clientId: number, updatedClient: any): Observable<string> {
    return this.http.put<string>(
      `${this.baseUrl}/update/${clientId}`,
      updatedClient
    );
  }
}
