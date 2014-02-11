package com.ags.mp3.service;

import com.ags.mp3.downloader.Mp3Downloader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 1/02/13
 *         Time: 19:35
 */
public class GoearService {

    private Mp3Downloader mp3Downloader;
    private String musicPath;

    private final static String GOEAR_URL = "http://www.goear.com/action/sound/get";


    public GoearService(String musicPath) {
        this.mp3Downloader = new Mp3Downloader();
        this.musicPath = musicPath;
    }

    public void downloadMp3(String goearUrl) throws IOException {
        String linkTracked = goearUrl.substring(0, goearUrl.lastIndexOf("/"));
        String linkGood = linkTracked.substring(linkTracked.lastIndexOf("/"),linkTracked.length());
        String songName = goearUrl.substring(goearUrl.lastIndexOf("/")+1, goearUrl.length());
        mp3Downloader.saveMp3(GOEAR_URL+linkGood,musicPath, songName);

        System.out.println("");
        System.out.println("Mp3 downloaded: "+songName);
    }

    private String getLinkTracker(String html) {
        Document document = Jsoup.parse(html);
        return document.getElementById("links").getElementsByTag("a").get(0).attr("href");
    }

}
