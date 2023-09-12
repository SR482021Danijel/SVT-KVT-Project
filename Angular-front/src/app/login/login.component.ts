import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
    });
  }
  ngOnInit() {}

  submit() {
    this.authService.login(this.form.value).subscribe({
      next: (data) => {
        console.log('Login success');
        localStorage.setItem('jwt', data.accessToken);
        this.router.navigate(['home']);
      },
      error: (err) => console.log(err),
    });
  }
}
