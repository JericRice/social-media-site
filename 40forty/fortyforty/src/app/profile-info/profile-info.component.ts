import { Component, OnInit } from '@angular/core';
import { ProfileServService } from '../shared/profile-serv.service';
import { User } from '../models/User';
import { Router, ActivatedRoute } from '@angular/router';
import { PostService } from '../shared/post.service';
import { Post } from '../models/Post';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent implements OnInit {
  user;
  myPosts;
  /*   user$: User[];
   */
  constructor(private profileS: ProfileServService, private router: ActivatedRoute, private postService: PostService ) {
    // add user component for finding user
    /* if(user == User)  */
  }

  ngOnInit() {
    this.user = JSON.parse(this.router.snapshot.paramMap.get('user'));
    console.log(this.user);
    if (this.user == 0) {
      this.ProfileClick(this.user.email);
    }
    this.postService.getposts().subscribe(
      data => {
        this.myPosts = data;
        for (const p of this.myPosts) {
          p.timestamp = new Date(p.timestamp).toLocaleString();
        }
      });
    console.log(this.myPosts)
  }

  ProfileClick(email: string) {
    console.log('method');
    this.profileS.getUser(email).subscribe(
      data => {
        this.user = data;
      }
    );
  }
}
