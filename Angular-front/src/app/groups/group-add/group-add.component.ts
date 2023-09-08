import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GroupService } from '../group.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-group-add',
  templateUrl: './group-add.component.html',
  styleUrls: ['./group-add.component.css'],
})
export class GroupAddComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private groupService: GroupService,
    private router: Router
  ) {
    this.form = this.fb.group({
      name: [null, Validators.required],
      description: [null, Validators.required],
    });
  }
  jwt: JwtHelperService = new JwtHelperService();

  username = this.jwt.decodeToken(localStorage.getItem('jwt')!).sub;

  submit() {
    let creationDate = new Date();

    this.groupService
      .add(this.form.value, creationDate, this.username)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.router.navigate(['group']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    this.form.reset();
  }
}
