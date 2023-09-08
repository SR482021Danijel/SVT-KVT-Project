import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../posts/post-model';
import { PostService } from '../posts/post.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ReactType } from '../posts/react-type';
import { Reaction } from '../posts/reaction-model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private router: Router, private postService: PostService) {}

  jwt: JwtHelperService = new JwtHelperService();

  posts: Post[] = [];

  reactType = ReactType;

  username = this.jwt.decodeToken(localStorage.getItem('jwt')!).sub;

  ngOnInit(): void {
    this.postService.getAll(this.username).subscribe({
      next: (data) => {
        // console.log(data[0].checked);
        this.posts = data as Post[];
        console.log(this.posts);
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

  addPost() {
    this.router.navigate(['/post/add']);
  }

  deletePost(id: any) {
    this.postService.delete(id).subscribe({
      error: (err) => {
        console.log(err);
      },
    });
  }

  updatePost() {
    this.router.navigate(['/post/update']);
  }
}
