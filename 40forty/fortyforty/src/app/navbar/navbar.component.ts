import { Component, OnInit } from '@angular/core';
import { LoginService } from '../shared/login.service';
import { Router } from '@angular/router';
import { User } from '../models/User';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private currentUser: User;

  constructor(private loginserv: LoginService, private router: Router) { }

  ngOnInit() {
  }

  logout() {
    this.loginserv.doLogout();
  }
}
