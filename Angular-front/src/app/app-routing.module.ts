import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { PostAddComponent } from './posts/post-add/post-add.component';
import { GroupListComponent } from './groups/group-list/group-list.component';
import { GroupAddComponent } from './groups/group-add/group-add.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'post/add',
    component: PostAddComponent,
  },
  {
    path: 'group',
    component: GroupListComponent,
  },
  {
    path: 'group/add',
    component: GroupAddComponent,
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: '/login',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
