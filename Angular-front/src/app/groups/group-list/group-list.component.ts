import { Component, OnInit } from '@angular/core';
import { GroupService } from '../group.service';
import { Group } from '../group-model';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css'],
})
export class GroupListComponent implements OnInit {
  groups: Group[] = [];
  joinedGroups: Group[] = [];

  jwt: JwtHelperService = new JwtHelperService();

  username = this.jwt.decodeToken(localStorage.getItem('jwt')!).sub;

  constructor(private groupService: GroupService) {}

  ngOnInit(): void {
    this.groupService.getAll(this.username).subscribe({
      next: (data) => {
        for (let i = 0; i < data.length; i++) {
          const element = data[i];
          if (element.username == this.username) {
            this.joinedGroups.push(element);
          } else {
            this.groups.push(element);
          }
        }
        console.log(this.groups);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
