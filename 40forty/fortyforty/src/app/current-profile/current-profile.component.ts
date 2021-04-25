import { Component, OnInit } from '@angular/core';
import { ProfileServService } from '../shared/profile-serv.service';
import { User } from '../models/User';
import { Router, ActivatedRoute } from '@angular/router';
import { PostService } from '../shared/post.service';
import { getReactionScore, Post } from '../models/Post';

@Component({
  selector: 'app-current-profile',
  templateUrl: './current-profile.component.html',
  styleUrls: ['./current-profile.component.css']
})
export class CurrentProfileComponent implements OnInit {
  user;
  myPosts;
  pagenum = 0;
  pagemax= 20;
  collectionSize;
  posts : Post[];
  page : Post[];
  perpage : 10;
  /*   user$: User[];
   */
  constructor(private profileS: ProfileServService, private router: ActivatedRoute, private postService: PostService ) {
    // add user component for finding user
    /* if(user == User)  */
  }

  ngOnInit() {
    // console.log("in current");
    // this.user = JSON.parse(localStorage.getItem("currentUser")) as User;
    // console.log(this.user);
    // this.postService.getMyPosts(this.user.userID).subscribe(
    //   data => {
    //     this.myPosts = data;
    //     console.log(data);
    //     for (const p of this.myPosts) {
    //       p.timestamp = new Date(p.timestamp).toLocaleString();
    //     }
    //   });
    // console.log(this.myPosts)
    this.user = JSON.parse(localStorage.getItem("currentUser")) as User;

    console.log(this.user);

    this.postService.getMyPosts(this.user.userID).subscribe(
      data => {
        this.myPosts = data;
        console.log(data);
        for (let p of this.myPosts)
        {
          this.initPost(p);
          // how do we check the array of reactions to get the user's reaction if it exists...
          // for some reason this fancy, condensed code just doesn't wanna work :/
          // let reaction = p.reactions.find(reaction => reaction.reactor.user === this.myUser);
          // console.log(reaction);
          // p.userReaction = (reaction === undefined) ? 0 : reaction;

          p.score = getReactionScore(p);
        }
      }
    );
  }

  ProfileClick(email: string) {
    console.log('method');
    // this.profileS.getUser(email).subscribe(
    //   data => {
    //     this.user = data;
    //   }
    // );
    this.user = localStorage.getItem("currentUser");
    console.log(this.user);
  }
  
  initPost(p: Post) {
    p.timestamp = new Date(p.dateCreated).toLocaleString();
    p.userReaction = 0;
    p.userIsReacting = false;
    p.score = 0;

    return p;
  }
  setpage() {
    let start = this.pagenum * this.pagenum;
    let end = start + this.perpage;
    this.page = [];
    for (start; start < end; start++) {
      this.page.push(this.posts[start]);
    }
  }
}
