import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public auth: AuthService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const item = localStorage.getItem('user');

    if (item) {
      const decodedItem = JSON.parse(item);
      const cloned = req.clone({
        headers: req.headers.set('X-Auth-Token', decodedItem.token),
      });

      return next.handle(cloned);
    } else {
      return next.handle(req);
    }
  }
}
