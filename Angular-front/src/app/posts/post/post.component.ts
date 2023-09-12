import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../post.service';
import { Post } from '../post-model';
import { ReactType } from '../react-type';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CommentService } from '../comment.service';
import { Comment } from '../comment-model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {
  jwt: JwtHelperService = new JwtHelperService();
  id: number = 0;
  post: any = {};
  comments: Comment[] = [];
  reactType = ReactType;
  username = this.jwt.decodeToken(localStorage.getItem('jwt')!).sub;
  postText: string = '';

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
    });

    this.postService.getById(this.id).subscribe({
      next: (data) => {
        this.post = data as Post;
        console.log(this.post);
      },
      error: (err) => {
        console.log(err);
      },
    });

    this.commentService.getAllByPost(this.id).subscribe({
      next: (data) => {
        this.comments = data as Comment[];
        console.log(this.comments);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  onReact(e: any, post: Post) {
    // console.log(e.target);
    let date = new Date();
    this.postService
      .changeReaction(e.target.value, date, post, this.username)
      .subscribe({
        next: (data) => {
          // console.log(data);
        },
        error: (err) => {
          console.log(err);
        },
      });
  }

  onCommentReact(e: any, comment: Comment) {
    let date = new Date();
    this.commentService
      .changeCommentReaction(e.target.value, date, comment, this.username)
      .subscribe({
        next: (data) => {
          // console.log(data);
        },
        error: (err) => {
          console.log(err);
        },
      });
  }

  submit() {
    let date = new Date();
    this.commentService
      .add(this.postText, date, this.username, this.id)
      .subscribe({
        next: (data) => {},
        error: (err) => {
          console.log(err);
        },
        complete: () => {
          this.commentService.getAllByPost(this.id).subscribe({
            next: (data) => {
              this.comments = data as Comment[];
              console.log(this.comments);
            },
            error: (err) => {
              console.log(err);
            },
          });
        },
      });
    console.log(this.postText);
    this.postText = '';
  }
}
