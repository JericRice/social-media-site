import { Component, OnInit } from '@angular/core';
import { LoginService } from '../shared/login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private username = '';
  private password = '';
  loading = false;

  constructor(private loginserv: LoginService, private router: Router) {
   }


  ngOnInit() {

  }

  ngAfterViewInit() {
    const user = this.loginserv.getCurrentUser();
    console.log("Currentuser:" + user);
/*     if (user) {
      this.router.navigate(['friends']);
    } */
  }

  onSubmit() {
    this.loading = true;
    this.loginserv.doLogin(this.username, this.password);
  }

  onRegClick() {
    this.router.navigate(['register']);
  }

}
