import { Component, OnInit } from '@angular/core';
import { ProfileServService } from '../shared/profile-serv.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  constructor(private profileForgot: ProfileServService, private route: Router) { }
  private username = '';
  private email = '';

  ngOnInit() {
  }
  // Submitting form to edit password
  onSubmmit() {
    this.profileForgot.forgotPassword(this.username, this.email).subscribe(
      data => {
        console.log(data);
        this.route.navigate(['login']);
      });
  }
}
