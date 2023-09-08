import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GroupService {
  constructor(private http: HttpClient) {}

  add(group: any, date: Date, username: any): Observable<any> {
    return this.http.post('api/groups/add', {
      name: group.name,
      description: group.description,
      creationDate: date,
      username: username,
    });
  }

  getAll(username: any): Observable<any> {
    return this.http.post('api/groups/getAll', { username: username });
  }
}
