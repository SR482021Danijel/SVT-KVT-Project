import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReactType } from './react-type';
import { Comment } from './comment-model';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  constructor(private http: HttpClient) {}
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  add(
    text: string,
    timeStamp: Date,
    username: string,
    postId: any
  ): Observable<any> {
    return this.http.post(
      'api/comments/add',
      { text, timeStamp, username, postId },
      { headers: this.headers, responseType: 'json' }
    );
  }

  getAllByPost(id: any): Observable<any> {
    return this.http.get('api/comments/comment/' + id);
  }

  changeCommentReaction(
    react: ReactType,
    timestamp: Date,
    comment: Comment,
    username: string
  ): Observable<any> {
    return this.http.put('api/comments/changeCommentReaction', {
      reactionType: react,
      timeStamp: timestamp,
      comment: comment,
      username: username,
    });
  }
}
