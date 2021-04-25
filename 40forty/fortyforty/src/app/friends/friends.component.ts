import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { FriendService } from '../shared/friend.service';
import { LoginService } from '../shared/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {

  private users: User[];

  private filteredUsers: User[];

  private currentUser: User;

  private _filter = '';
  get filter(): string {
    return this._filter;
  }
  set filter(temp: string) {
    this._filter = temp;
    this.filteredUsers = this._filter ? this.performFilter(this._filter) : this.users;
  }


  constructor(private friendServ: FriendService, private loginServ: LoginService, private router: Router) {
    this.currentUser = this.loginServ.getCurrentUser();
    this.displayFriends();
  }

  ngOnInit() {
  }

  displayFriends() {
    this.friendServ.getFriends().subscribe(data => {
      console.log(data);
      this.users = data;
      this.filteredUsers = this.users;
    });
  }

  performFilter(filterBy: string): User[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.users.filter((person: User) =>
      person.username.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }

  // testSes() {
  //   console.log('Testing for session persistance');
  //   this.friendServ.testSession().subscribe(data => { console.log(data); });
  // }
  // links to a users profile
  loadProfile(user) {
    this.router.navigate(['/profileInfo', user]);

    console.log(user);
  }
}
