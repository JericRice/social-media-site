import { Component, OnInit } from '@angular/core';
import { PostService } from '../shared/post.service';
import { getReactionScore, Post, Reaction } from '../models/Post';
import { LoginService } from '../shared/login.service';
import { Router } from '@angular/router';
import { UploadService } from '../shared/upload.service';
import { NumberAttributeValue } from 'aws-sdk/clients/dynamodb';
import { SSL_OP_SSLEAY_080_CLIENT_DH_BUG } from 'constants';
import { User } from '../models/User';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {
  pagenum = 0;
  pagemax = 20; // lower this when we test speed pls
  posts: Post[];
  page: Post[];
  perpage = 10;
  collectionSize;
  myUser: User;

  private newPostBody = '';
  private selectedFile;

  constructor(private postService: PostService, private loginserv: LoginService, private uploadServ: UploadService) {
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0] as File;
    console.log(this.selectedFile);
  }

  ngOnInit() {
    this.myUser = this.loginserv.getCurrentUser();
    this.selectedFile = null;

    console.log(this.myUser);

    this.postService.getposts().subscribe(
      data => {
        this.posts = data;
        console.log(data);
        for (let p of this.posts)
        {
          this.initPost(p);
          // how do we check the array of reactions to get the user's reaction if it exists...
          for(let i = 0; i < p.reactions.length; i++)
          {
            if(p.reactions[i].reactor.username === this.myUser.username)
            {
              console.log(p.reactions[i]);
              p.userReaction = p.reactions[i];
            }
          }

          // for some reason this fancy, condensed code just doesn't wanna work :/
          // let reaction = p.reactions.find(reaction => reaction.reactor.user === this.myUser);
          // console.log(reaction);
          // p.userReaction = (reaction === undefined) ? 0 : reaction;

          p.score = getReactionScore(p);
        }
        this.pagemax = this.posts.length;
        this.setpage();
        this.collectionSize = this.posts.length;
      }
    );

  }

  decrement() {
    if (this.pagenum > 0) {
      this.pagenum--;
      this.setpage();
    }

  }

  // React to a post, 1 for positive, -1 for negative
  reactPost(p: Post, v: number)
  {
    // console.log(p);

    // if(!(p.reactions.indexOf(this.loginserv.getCurrentUser()) > -1))
    console.log(' liking a post should work if you see this.');

    // If they haven't reacted before, use reactToPost to send a POST req to server.
    // otherwise, if the value is different from their previous reaction value, use updateReaction to send a PUT req to server.
    // otherwise they pressed the same react button, so we can delete it... or do nothing
    if (p.userReaction === 0 && !p.userIsReacting)
    {
      // This stops people from spamming it
      p.userIsReacting = true;

      // We change these first so the user sees an instant update
      // BUT they shouldn't be able to spam it
      // We should move this when websockets are involved
      p.userReaction = v;
      // p.score += v;

      console.log("This is a new reaction");

      this.postService.reactToPost(p, v).subscribe(data => {
        console.log(data);
        if (data != null) {
          p.reactions.push(data as Reaction);
          p.score += v;
          p.userReaction = data;
        } else {
          p.userReaction = 0;
          p.score -= v;
        }
        // bad or good, the transaction is done
        p.userIsReacting = false;
      });
    }
    else if(v != p.userReaction.value && !p.userIsReacting)
    {
      p.userIsReacting = true;

      p.userReaction = v;
      // We add it twice here because it needs to negate the first, then add the reaction
      // p.score += v + v;

      console.log("This is a old reaction");

      this.postService.updateReaction(p).subscribe(data => 
      {
        // We need to remove the current reaction and add a new one
        // Or toggle the current reaction in reactions[]
        // let reactions : [] = p.reactions;
        // let index = reactions.indexOf(this.loginserv.getCurrentUser())

        console.log(data);
        if (data != null) {
          p.userReaction = p.reactions.find(reaction => reaction.reactor.userID === this.myUser.userID);
          p.userReaction.value = v;
          p.score += v + v;
        } else {
          p.score -= v + v;
        }

        // bad or good, the transaction is done
        p.userIsReacting = false;
      });
    }
    else
    {
      return;
    }
  }

  increment() {
    if (this.pagenum < this.pagemax - 1) {
      this.pagenum++;
      this.setpage();
    }
  }
  setpage() {
    let start = this.pagenum * this.pagenum;
    let end = start + this.perpage;
    this.page = [];
    for (start; start < end; start++) {
      this.page.push(this.posts[start]);
    }
  }

  // Send over a new post to db
  submitPost() 
  {
    console.log('Submitting post to backend ...');

    console.log(this.selectedFile);

    // Call service and send post
    if (this.newPostBody.length <= 250 && this.newPostBody !== '') 
    {
      this.postService.sendPost(this.newPostBody, this.selectedFile).subscribe(data => {
        console.log(data);
        let newPost = data;
        newPost = this.initPost(newPost);
        this.posts.push(newPost);
        this.setpage();
      });
    } 
    else 
    {
      // Set error message in post modal
      console.log("NOPE OCTOPUS");
    }
  }

  initPost(p: Post) {
    p.timestamp = new Date(p.dateCreated).toLocaleString();
    p.userReaction = 0;
    p.userIsReacting = false;
    p.score = 0;

    return p;
  }
}
