package com.ags.mp3.connetor;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 1/02/13
 *         Time: 19:19
 */
public class HttpGoearConnector {


    private String urlString;

    public HttpGoearConnector(String url) {
        this.urlString = url;
    }

    public String doPost(String formData) {
        HttpURLConnection connection = null;
        try {

            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(formData.getBytes().length));

            //send request
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(formData);
            dos.flush();
            dos.close();
            //get response
            // Get response data.
            String str = null;
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while ((str = input.readLine()) != null) {
                sb.append(str.replace("&nbsp;", " "));
            }
            input.close();
            return sb.toString();

        } catch (IOException e) {
            //TODO
            return null;
        }


    }

    public String doGet(String urlGet) {


        URL url = null;
        try {
            url = new URL(urlGet);
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine.replace("&nbsp;", " "));
            }
            in.close();
            return sb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

       return null;

    }

}
