import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as AWS from 'aws-sdk/global';
import * as S3 from 'aws-sdk/clients/s3';
import { Observable, BehaviorSubject } from 'rxjs';
import { PostService } from './post.service';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private bucket;

  // behavior subject
  private data;

  // Updated with my s3

  constructor(private httpCli: HttpClient) {
    this.bucket = new S3({
      accessKeyId: 'AKIASDTWIPFYCPLVWTYH',
      secretAccessKey: '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',
      region: 'us-east-2'
    });
    this.data = new BehaviorSubject<string>(this.data);
    this.data = this.data.asObservable();
    this.data = this.data.toPromise();
   }

  doUpload(file) {
    const filename = 'https://40forty.s3.amazonaws.com/' + file.name; 
    const contentType = file.type;
    const params = {
      Bucket: '40forty',
      Key: file.name,
      Body: file,
      ACL: 'public-read',
      ContentType: contentType
    };
    this.bucket.upload(params, (err, res) => {
      console.log(res.location);
    });
    return filename;
  }
}






// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import * as AWS from 'aws-sdk/global'; // AWS SERVER
// // import * as S3 from 'aws-sdk/clients/s3'; < -- WE NEED TO FIGURE THIS OUT -- > naaaaaaaah lol
// import { Observable, BehaviorSubject } from 'rxjs';
// import { PostService } from './post.service';
// import { S3 } from 'aws-sdk';
// import { Session } from 'inspector';

// @Injectable({
//   providedIn: 'root'
// })
// export class UploadService {
//   private bucket;

//   // behavior subject
//   private data;

//   // 40fortybucket
//   private albumBucketName : string;

//   // SO THIS IS WHAT I COULD FIGURE OUT WITH s3 thanks to RESEARCH. 

//   // WE CLOSE!
//   //
//   // I uncommented your AWS import from aws-sdk/global and followed the docs found here
//   // https://docs.aws.amazon.com/sdk-for-javascript/v2/developer-guide/s3-example-photo-album.html
//   //
//   // It had me make an Identity Pool through Amazon Cognito and a IAM Role
//   //
//   // The credentials are hard-coded, env variables would be very cool
//   //
//   // The file key should have the username in front of it, that way we can get them back
//   // Alternatively, or also, we could create a new folder for each user
//   //
//   // There's a folder on the S3 in the root called media/ , I figure everything can go there
//   // - Brandon

//   constructor(private httpCli: HttpClient) 
//   {
//     this.albumBucketName = "40fortybucket";
//     let bucketRegion = "us-east-2";
//     let IdentityPoolId = "us-east-2:148cbc99-ddcf-4db8-b269-7b5ef4728446";

//     AWS.config.update({
//       region: bucketRegion,
//       credentials: new AWS.CognitoIdentityCredentials({
//         IdentityPoolId: IdentityPoolId
//       })
//     });

//     this.bucket = new S3({
//       apiVersion: "2006-03-01",
//       params: { Bucket: this.albumBucketName }
//     });

//     this.data = new BehaviorSubject<string>(this.data);
//     this.data = this.data.asObservable();
//     this.data = this.data.toPromise();
//    }

//   doUpload(file)
//   {
//     // FAKE SINCE I DIDN'T KNOW THIS
//     // s'all good, man
//     const filename = 'https://40fortybucket.s3.amazonaws.com/' + file.name;

//     let currentUser = JSON.parse(localStorage.getItem("currentUser"));

//     const fileKey = `${currentUser.username}-${file.name}`;
//     const contentType = file.type;
//     const params = {
//       Bucket: this.albumBucketName,
//       Key: fileKey,
//       Body: file,
//       ACL: 'public-read',
//       ContentType: contentType
//     };

//     if(this.bucket.headObject({ Key: fileKey }, (err, data) => 
//     {
//       if(!err) {
//         return "ERROR : File already exists"
//       }
//       if (err.code !== "NotFound") {
//         return "ERROR uploading file: " + err.message;
//       }
//     }))
    
//     this.bucket.putObject({ Key: fileKey }, (err, res) => 
//     {
//       if(err) {
//         return "ERROR uploading file: " + err.message;
//       }

//       return filename;
//     });

//     return "ERROR idk";
//   }
// }
