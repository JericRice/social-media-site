import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../shared/login.service';
import { UploadService } from '../shared/upload.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private username = '';
  private password = '';
  private confirm_pass = '';
  private firstname = '';
  private lastname = '';
  private email = '';
  private gender = '';
  private birthday;
  fail = false;
  selectedFile: any;
  avatar: string;



  constructor(private loginServ: LoginService, private router: Router, private uploadServ: UploadService) { }

  ngOnInit() {
  }

  onFileSelected(event){
    this.selectedFile = event.target.files[0] as File;
    console.log(this.selectedFile);
  }

  onPress() 
  {
    // Added so uploading only happens if the file is present
    // ...but it would fail if it's there because the doUpload method
    // in the login.service.ts needs a currentuser in the session FIRST
    if(!this.selectedFile === null)
    {
      this.uploadFile(this.selectedFile);
    }
    //
    // I commented out all register profile pic input features


    // We can put field input check logic here!
    this.register();
  }

  uploadFile(file) {
    this.avatar = this.uploadServ.doUpload(file);
    this.register();
  }

  register() {
    // This needs more checks!!
    // The username should be between 1-15 characters
    // The email must have a @ symbol, and be between
    // All fields must be filled in
    if (this.password === this.confirm_pass) {
      this.loginServ.doRegister(this.email, this.username, this.password, this.firstname, this.lastname, this.gender, this.birthday);
    } else {
      console.log("Screw you buddy");
    }
  }

}
