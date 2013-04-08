package com.ags.mp3.service;

import com.ags.mp3.connetor.HttpGoearConnector;
import com.ags.mp3.downloader.Mp3Downloader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 1/02/13
 *         Time: 19:35
 */
public class GoearService {

    private HttpGoearConnector goearConnector;
    private Mp3Downloader mp3Downloader;
    private String musicPath;


    public GoearService(String musicPath) {
        this.goearConnector = new HttpGoearConnector("http://downmusic.org/download-music-from-goear.php");
        this.mp3Downloader = new Mp3Downloader();
        this.musicPath = musicPath;
    }

    public void downloadMp3(String goearUrl) throws IOException {
        String encode = URLEncoder.encode(goearUrl, "UTF-8");
        String htmlDownload = goearConnector.doPost("url_goear=" + encode + "&descargar=Descargar+canci%C3%B3n");
        String linkTracked = this.getLinkTracker(htmlDownload);

        String xml = goearConnector.doGet(linkTracked);

        Document document = Jsoup.parse(xml);
        String mp3Url = this.getSongUrl(document);
        String fileName = this.getSongName(document);

        mp3Downloader.saveMp3(mp3Url,musicPath,fileName);

        System.out.println("");
        System.out.println("Mp3 downloaded: "+fileName);
    }

    private String getLinkTracker(String html) {
        Document document = Jsoup.parse(html);
        return document.getElementById("links").getElementsByTag("a").get(0).attr("href");
    }

    private String getSongName(Document document) {
        return document.getElementsByAttribute("path").get(0).attr("title");
    }
    private String getSongUrl(Document document) {
        return document.getElementsByAttribute("path").get(0).attr("path");
    }
}
