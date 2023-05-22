import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private router: Router) {}

  private access_token = null;

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  login(user: any) {
    return this.http
      .post(
        '/api/users/login',
        { username: user.username, password: user.password },
        { headers: this.headers, responseType: 'json' }
      )
      .pipe(
        map((res: any) => {
          console.log('Login success');
          this.access_token = res.accessToken;
          localStorage.setItem('jwt', res.accessToken);
        })
      );
  }

  register(user: any): Observable<any> {
    return this.http.post(
      '/api/users/register',
      {
        username: user.username,
        email: user.email,
        firstName: user.firstName,
        lastName: user.lastName,
        password: user.password,
      },
      { headers: this.headers, responseType: 'json' }
    );
  }

  logout() {
    this.access_token = null;
    this.router.navigate(['/login']);
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }
}
