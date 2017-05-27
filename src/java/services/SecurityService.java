/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.lowagie.text.pdf.PdfFileSpecification.url;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import sun.net.*;
import sun.net.www.http.HttpClient;

public class SecurityService {

    private String RANDOM_SALT = "asdiuq8hnc8idsc";
    private String ANDROID_NOTIFICATION_URL = "https://fcm.googleapis.com/fcm/send";
    private String ANDROID_NOTIFICATION_KEY = "AAAADiQjZEs:APA91bGAmm8MPAfv3xBx9AGLWPUyFB3_2RuWN0UTgRHGB8dvWxybyGy-GzBUgyYu338KUjvRbHNwi7P9j7uqtPyuQTfEVQYDJ_TtsY6_d6jcyQWzdfb1g5dFdR0Y3fXuRhi6QpM8qDwV";

    public String hashPassword(String UserPassword) throws NoSuchAlgorithmException {
        String password = UserPassword + this.RANDOM_SALT;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(password.getBytes(), 0, password.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    public String getOtp() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public void otpv2(String otp) throws IOException, JSONException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        JSONObject obj = new JSONObject();
        JSONObject msgObject = new JSONObject();
        msgObject.put("body", otp);
        msgObject.put("title", "notification");

        obj.put("to", "etN09hk7SII:APA91bGSE-G6jEKywU6D368rNRrd77zgL2UmKglsDhs-mdOsSJB4zrSS450D6TrrnwGiANx8wrn1mRCxYoJE_Qr743PVLCeN-mzL36UCQFYXMyqBJMQZr9nFKrvW7HmAQ5_507CUK7ys");
        obj.put("notification", msgObject);

        StringEntity requestEntity = new StringEntity(
                obj.toString(),
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(ANDROID_NOTIFICATION_URL);
        postMethod.setHeader("Content-type", "application/json");
        postMethod.setHeader("Authorization", "key=" + ANDROID_NOTIFICATION_KEY);
        postMethod.setEntity(requestEntity);
        HttpResponse rawResponse = client.execute(postMethod);
    }

//    public void sentOtp(String otp) throws IOException {
//
//        try {
//            OkHttpClient client = new OkHttpClient();
//            MediaType mediaType = MediaType.parse("application/json");
//            JSONObject obj = new JSONObject();
//            JSONObject msgObject = new JSONObject();
//            msgObject.put("body", otp);
//            msgObject.put("title", "notification");
//
//            obj.put("to", "etN09hk7SII:APA91bGSE-G6jEKywU6D368rNRrd77zgL2UmKglsDhs-mdOsSJB4zrSS450D6TrrnwGiANx8wrn1mRCxYoJE_Qr743PVLCeN-mzL36UCQFYXMyqBJMQZr9nFKrvW7HmAQ5_507CUK7ys");
//            obj.put("notification", msgObject);
//
//            RequestBody body = RequestBody.create(mediaType, obj.toString());
//            Request request = new Request.Builder().url(ANDROID_NOTIFICATION_URL).post(body)
//                    .addHeader("Content-type", "application/json")
//                    .addHeader("Authorization", "key=" + ANDROID_NOTIFICATION_KEY).build();
//
//            return client.newCall(request).execute();
//        } catch (JSONException ex) {
//            Logger.getLogger(SecurityService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
