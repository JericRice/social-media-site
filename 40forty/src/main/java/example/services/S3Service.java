package example.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import example.models.Post;
import example.models.UserAccount;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@Service
public class S3Service {
    private static final AWSCredentials cred = new BasicAWSCredentials("AKIASDTWIPFYCPLVWTYH", "M84PtwE+nNFUzk47IDdBGtiIekF0VAOMpiU079et");
    private static final AmazonS3 s3 =AmazonS3Client.builder().withRegion("us-east-2")
            .withCredentials(new AWSStaticCredentialsProvider(cred))
            .build();
    private static final String bucket = "40forty";
    public static final String baseUrl = "http://" + S3Service.bucket + ".s3.us-east-2.amazonaws.com/";

    /*
     * Create methods
     *
     */
    public static void createFolder(String type, Integer id) {
        // create meta-data for your folder (keep content length set to 0)
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by '/' send request to S3 to create folder
        S3Service.s3.putObject( new PutObjectRequest(S3Service.bucket,
                type + "/", emptyContent, metadata));
    }
    public static String uploadImage(UserAccount user) {
        return uploadImage(user, null);
    }
    public static String uploadImage(UserAccount user, Post post) {
        String url = "";
        String base64="";
        if(post != null) {
            url = "post/" + (user.getUsername()+ post.getDateCreated().toString()).hashCode();
            base64=post.getMediaURL();
        }else {
            url = "user/" + user.getUsername();
            base64=user.getAvatarURL();
        }
        System.out.println(base64);
        String[] base64Array = base64.split(",");
        byte[] decodedByte = Base64.getDecoder().decode(base64Array[1]);
        String[] base64Head = base64Array[0].split("/");
        url += (base64Head.length>0) ? "." + base64Head[1].split(";")[0] : "";
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(decodedByte.length);
        // create image content
        InputStream imageContent = new ByteArrayInputStream(decodedByte);
        // create a PutObjectRequest passing the folder name suffixed by /
        // send request to S3 to create folder
        S3Service.s3.putObject( new PutObjectRequest(S3Service.bucket,
                url, imageContent, metadata));
        return S3Service.baseUrl  +url;
    }

    /*
     * Delete
     */
    public static void deleteFolder(String type) {
        List<S3ObjectSummary> fileList =
                S3Service.s3.listObjects(S3Service.bucket, type).getObjectSummaries();
        for (S3ObjectSummary file : fileList) {
            S3Service.s3.deleteObject(S3Service.bucket, file.getKey());
        }
        S3Service.s3.deleteObject(S3Service.bucket, type);
    }

    public static void deleteImage(String type, Integer id) {
        List<S3ObjectSummary> fileList =
                S3Service.s3.listObjects(S3Service.bucket, type + "/" + id.toString()  ).getObjectSummaries();
        for (S3ObjectSummary file : fileList) {
            S3Service.s3.deleteObject(S3Service.bucket, file.getKey());
        }
        S3Service.s3.deleteObject(S3Service.bucket, id.toString());
    }
    /*
     * Read
     */
    public static String getImage(String type, Integer id) {
        S3Object s3Image = S3Service.s3.getObject(new GetObjectRequest(S3Service.bucket, type+"/"+id.toString()));
        byte[] imageBytes=null;
        try {
            imageBytes = IOUtils.toByteArray(s3Image.getObjectContent());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (imageBytes==null)? "" :  Base64.getEncoder().encodeToString(imageBytes);
    }
}
