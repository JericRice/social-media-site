// import { Component, OnInit } from '@angular/core';
// import { Title } from '@angular/platform-browser';
// import { HttpClient } from '@angular/common/http';
// import { Router } from '@angular/router';
// import { CurrentUserService } from '../../services/current-user.service';
// import { User } from '../../models/User';

// @Component({
//   selector: 'app-home',
//   templateUrl: './home.component.html',
//   styleUrls: ['./home.component.css']
// })
// export class HomeComponent implements OnInit {

//   private hasProfilePic: boolean = true;

//   constructor(private client: HttpClient, private router: Router, private currUser: CurrentUserService, private title: Title) { }

//   ngOnInit() {
//     this.title.setTitle('Home Page');
//     this.client.get('http://localhost:8080/SocialMedia/session', { withCredentials: true }).subscribe(
//       (succ: any) => {
//         if(!succ.uid){  //if it's null
//           this.router.navigate(['/login']);
//           alert("You must be logged in to view this page");
//         }
//         else{
//           sessionStorage.removeItem("user");  //remove whichever user is currently stored
          
//           const user = new User(succ.uid, succ.firstname, succ.lastname, succ.email, succ.imgHash);
//           this.currUser.setUser(user);

//           if(succ.imgHash == null){
//             this.hasProfilePic = false;
//           }
           
//           return succ;
//         }
//       },
//     err => {
//         alert('failed to retrieve sessionID');
//     });
//   }
// }
