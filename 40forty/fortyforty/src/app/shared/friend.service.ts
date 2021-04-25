import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  constructor(private httpCli: HttpClient) { }

  getFriends() {
    return this.httpCli.get<User[]>('http://localhost:8080/40forty/users/getAllUsers');
  }

  //Cause I have no better place to put it tbh
  testSession() {
    console.log("yeet");
    return this.httpCli.get('http://localhost:8080/Project2/testSession.MasterServlet', {withCredentials: true});
  }
}
