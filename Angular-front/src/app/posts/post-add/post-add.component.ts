import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PostService } from '../post.service';

@Component({
  selector: 'app-post-add',
  templateUrl: './post-add.component.html',
  styleUrls: ['./post-add.component.css'],
})
export class PostAddComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private router: Router
  ) {
    this.form = this.fb.group({
      content: [null, Validators.required],
    });
  }

  submit() {
    let creationDate = new Date();

    this.postService.add(this.form.value, creationDate).subscribe({
      next: (data) => {
        console.log(data);
        this.router.navigate(['home']);
      },
      error: (err) => {
        console.log(err);
      },
    });
    this.form.reset();
  }
}
