import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8085/userservice/api/client';

  constructor(private http: HttpClient) {}

  getAllClients(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getAllClient`, {});
  }

  saveClient(clientData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/registerClient`, clientData);
  }

  getClientById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}/getClient`);
  }

  loginClient(tokenRequestData: any): Observable<any> {
    return this.http.post<any>(
      `${this.baseUrl}/client/login`,
      tokenRequestData
    );
  }

  updateProfile(clientId: number, updatedClient: any): Observable<string> {
    return this.http.put<string>(
      `${this.baseUrl}/update/${clientId}`,
      updatedClient
    );
  }
}
