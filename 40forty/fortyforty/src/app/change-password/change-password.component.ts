import { Component, OnInit } from "@angular/core";
import { ProfileServService } from '../shared/profile-serv.service';
import { Observable } from 'rxjs';
import { HttpParams, HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: "app-change-password",
  templateUrl: "./change-password.component.html",
  styleUrls: ["./change-password.component.css"]
})
export class ChangePasswordComponent implements OnInit {
  renew: any;
  username: any;
  password: any;
  newpass: any;
  oldPassword: any;

  constructor(private profilePwsd: ProfileServService, private routerPwd: Router ) {
  }

  ngOnInit() {
  }

  handleChange() {
    console.log({
      username: this.username,
      password: this.password,
      newPassword: this.newpass,
      retypeNew: this.renew
    });
  }

  onSubmmit() {
    this.profilePwsd.updatePswd(this.username, this.password, this.oldPassword).subscribe(
      data => {
        console.log(data);
        this.routerPwd.navigate(['User']);
      });
  }


  vaildateRe(password, passwordRe) {
    if (password === passwordRe) {
      return true;
    } else {
      return false;
    }
  }

}

