import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { Reaction } from './reaction-model';
import { Post } from './post-model';
import { ReactType } from './react-type';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  jwt: JwtHelperService = new JwtHelperService();

  add(content: any, date: Date): Observable<any> {
    // console.log(content.content);
    // console.log(date);
    const decoded = this.jwt.decodeToken(localStorage.getItem('jwt')!);
    // console.log(decoded.sub);

    return this.http.post(
      'api/posts/add',
      { content: content.content, creationDate: date, username: decoded.sub },
      { headers: this.headers, responseType: 'json' }
    );
  }

  getById(id: any): Observable<any> {
    return this.http.get('api/posts/post/' + id);
  }

  getAll(username: string): Observable<any> {
    return this.http.post('api/posts/getAll', { username });
  }

  update(): Observable<any> {
    return this.http.put('api/posts/update', {});
  }

  delete(id: any): Observable<any> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        id: id,
      },
    };
    return this.http.delete('api/posts/delete', options);
  }

  changeReaction(
    react: ReactType,
    timestamp: Date,
    post: Post,
    username: string
  ): Observable<any> {
    return this.http.put(
      'api/posts/changeReaction',
      {
        reactionType: react,
        timeStamp: timestamp,
        post: post,
        username: username,
      },
      { headers: this.headers, responseType: 'json' }
    );
  }
}
