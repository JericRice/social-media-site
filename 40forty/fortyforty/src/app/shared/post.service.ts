import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Post } from '../models/Post';
import { UploadService } from './upload.service';
import { User } from '../models/User';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  private selectedFile;
  private picture = '';

  constructor(private httpcli: HttpClient, private uploadServ: UploadService) { }

  getposts() {
    // https://localhost:8080/Project2/getPosts.MasterServlet
    // http://localhost:8080/40forty/getAllPosts
    // https://api.myjson.com/bins/kp1gl
    return this.httpcli.get<Post[]>('http://localhost:8080/40forty/posts/getAllPosts');
    // return this.posts;
  }

  getMyPosts(id:number){
    // let currentUser = JSON.parse(localStorage.getItem("currentUser")) as User;
    const url = "http://localhost:8080/40forty/posts/getAllUserPost?id=" + id; 
    return this.httpcli.get<Post[]>(url);
  }
  
  // This should be used to update the text (body) property of a post
  // Or if a person wants to add a media after the post is made
  updatePost(p: Post, v: number) 
  {
    // console.log(p);

    // const payload2 = `{
    //   "reaction" : {
    //     "value" : "${v}"
    //   },
    //   "post" : {
    //     "postID" : "${p.postId}"
    //   }
    // }`;

    // console.log(payload2);

    // return this.httpcli.post(`http://localhost:8080/40forty/reactions/createReaction`, payload2, { withCredentials: true });
  }

  // This gets called when a user wants to react to a post
  reactToPost(post: Post, value: number) 
  {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }), 
      withCredentials: true 
    };

    return this.httpcli.post(`http://localhost:8080/40forty/reactions/createReaction?value=${value}`, JSON.stringify(post), options);
  }

  // This gets called if a user has already reacted but wants to swap their reaction
  updateReaction(post: Post)
  {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }), 
      withCredentials: true 
    };

    return this.httpcli.put(`http://localhost:8080/40forty/reactions/updateReaction`, JSON.stringify(post), options);
  }




  // I really hate that I made these seperate methods, but here we are
  // It would have been easier if there were more java controller routes for text vs media,
  // or if the controllers took in queryParam strings because it's really only using a single field,
  // but, here we are
  //
  // go red sox
  // - brandon

  // Hi, brandon back here. I've decided to use that fancy TypeScript ? to make the parameter optional
  // Not only does it look swanky, but it makes the logic ~centralized~
  // etc. etc.
  // 
  // go red sox
  // - brandon
  sendPost(body, file?)
  {
    let currentUser = localStorage.getItem("currentUser");

    let formData = new FormData();

    console.log("sending a post...");
    console.log(body);
    console.log(file);
    console.log(JSON.stringify(file));

    let newPostMap = new Map<String, Object>();
    newPostMap.set("body", body);
    newPostMap.set("file", file);

    // So the map above ^ ain't workin
    // Time to bust out some fancy shmancy JSON writing skills
    const payload2 = `{
        "body" : "${body}",
        "file" : "${file}"
      }`;

    // console.log(payload2);
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }), 
      withCredentials: true 
    };

    console.log(payload2);

    return this.httpcli.post<Post>('http://localhost:8080/40forty/posts/create-post', payload2, options);
  }

  sendMediaPost(body, image) 
  {
    const payload2 = `{
      "text" : "${body}",
      "mediaURL" : "${image}"
    }`;

    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }), 
      withCredentials: true 
    };

    return this.httpcli.post('http://localhost:8080/40forty/posts/create-post', payload2, options);
  }
}
