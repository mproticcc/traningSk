import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private baseUrl = 'http://localhost:8085/userservice/api/admin';

  constructor(private http: HttpClient) {}

  getAllAdmins(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getAllAdmins`, {});
  }

  banUser(userId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/ban/${userId}`, {});
  }
  unBanUser(userId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/unban/${userId}`, {});
  }
  banManger(userId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/banM/${userId}`, {});
  }
  unBanManager(userId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/unbanM/${userId}`, {});
  }
}
