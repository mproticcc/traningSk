import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private baseUrlNotiType =
    'http://localhost:8085/notifservice/api/notification-types';
  private baseUrlNoti = 'http://localhost:8085/notifservice/api/notifications';

  constructor(private http: HttpClient) {}

  getAllNotiTypes(): Observable<any> {
    return this.http.get<any>(`${this.baseUrlNotiType}/getAllNotiTypes`, {});
  }

  deleteTypeNoti(typeID: number): Observable<any> {
    return this.http.delete<any>(
      `${this.baseUrlNotiType}/delete/${typeID}`,
      {}
    );
  }
  updateTypeNot(notiID: number, updateNoti: any): Observable<string> {
    return this.http.put<string>(
      `${this.baseUrlNotiType}/update/${notiID}`,
      updateNoti
    );
  }

  getAllNotifications(): Observable<any> {
    return this.http.get<any>(`${this.baseUrlNoti}/getAllNoti`, {});
  }
}
