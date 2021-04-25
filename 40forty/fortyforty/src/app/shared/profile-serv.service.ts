import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, never } from 'rxjs';


interface User {
  'userId': string;
  'username': string;
  'fname': string;
  'lname': string;
  'email': string;
}

@Injectable({
  providedIn: 'root'
})

export class ProfileServService {
  private path = 'http://localhost:8080/40forty/users/getAllUsers'; // Path to json goes here.
  private url = 'http://localhost:8080/40forty/users/updateUser';
  private edit = 'http://localhost:8080/40forty/users/updateUser';
  private getEm = 'http://localhost:8080/40forty/users/getUserByEmail';
  private reset;// =  'http://localhost:8080/40forty/resetPswd';
  constructor(private httCli: HttpClient) { }

  getUser(email: string): Observable<User> {
    const cred = {withCredentials: true};
    this.getEm += '?email=' + email;
    return this.httCli.get<User>(this.getEm, cred);
  }
  // updating password from profile
  updatePswd(uname: string, newPword: string, oldPword: string): Observable<boolean> {
    const payload = new HttpParams()
    .set('username', uname)
    .set('password', newPword)
    .set('oldPassword', oldPword);

    // const currentUser = JSON.parse(sessionStorage.getItem("currentUser"));

    const payload2 = `{
      "username" : "${uname}",
      "password" : "${newPword}",
      "oldPassword" : "${oldPword}"
    }`;

    const success = this.httCli.post<any>(this.url, payload2);
    return success;
  }
  // resetting password from login
  resetPswd(email: string): Observable<boolean> {
    const reset = new HttpParams().set('email', email);
    const sentEmail = this.httCli.post<any>(this.url, reset);
    return sentEmail;
  }
  // Edit profile
  editProfile(id: number, username: string, password: string, firstName: string, lastName: string, email: string): Observable<boolean> {
    console.log(localStorage.getItem("currentUser"));
    const editUser = new HttpParams()
    .set('userID', id.toString())
    .set('username', username)
    .set('password', password)
    .set('firstName', firstName)
    .set('lastName', lastName)
    .set('email', email);
    
    //const editUser2 = JSON.parse(`{"userID" : "${id.toString()}", "username" : "${username}", "password" : "${password}", "firstName" : "${firstName}", "lastName" : "${lastName}"}`)
    const profileSuccess = this.httCli.post<any>(this.edit, {}, {params : editUser, withCredentials : true});
    return profileSuccess;
  }
// forgot password reset
forgotPassword(username: string, email: string) {
  const forgot = new HttpParams()
  .set('username', username)
  .set('email', email);
  const forgotEmail = this.httCli.post<any>(this.reset, forgot);
  return forgotEmail;
}

}
