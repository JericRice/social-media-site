import { Component, OnInit } from '@angular/core';
import { ProfileServService } from '../shared/profile-serv.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpParams, HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';


@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  username: any;
  password: any;
  firstName: any;
  lastName: any;
  email: any;


  constructor(private profileEdit: ProfileServService, private router: Router) { }

  ngOnInit() {
  }
  onSubmmit() {
    this.profileEdit.editProfile(JSON.parse(localStorage.getItem("currentUser")).userID, this.username, this.password, this.firstName, this.lastName, this.email).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['User']);
      });
  }
}
