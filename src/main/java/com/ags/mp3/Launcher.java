package com.ags.mp3;

import com.ags.mp3.service.GoearService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 1/02/13
 *         Time: 19:25
 */
public class Launcher {


    private String mp3Folder;

    public static void main(String... args) throws Exception {
        new Launcher().showMenu();
    }


    public void showMenu() throws IOException {
        this.readConfiguration();
        System.out.println("GoEar DOWNLOADER");
        System.out.println("0 - Exit");
        System.out.println("1 - Download");
        int option = this.readOption(1);
        if (option == 0) {
            System.exit(0);
        } else if (option == 1) {
            this.run();
        }


    }

    private void readConfiguration() throws IOException {
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream("configuration.properties");
        prop.load(in);
        in.close();

        mp3Folder = prop.get("mp3.folder").toString();
    }

    public void run() throws IOException {

        System.out.println("paste a GoEar song:");
        String link = this.readLink();
        GoearService goearService = new GoearService(mp3Folder);
        try {
            goearService.downloadMp3(link);
        } catch (IOException e) {
            System.out.println("UUUUPSSSSS connection lost or somehitng :(");
        } finally {
            showMenu();
        }

    }

    private String readLink() {
        BufferedReader leerEntrada = new BufferedReader(new InputStreamReader(System.in));
        try {
            return leerEntrada.readLine();
        } catch (IOException e) {
            System.out.println("something went wrong :-(");
            System.out.println("try again");
            return readLink();
        }
    }

    private int readOption(int max) throws IOException {
        BufferedReader leerEntrada = new BufferedReader(new InputStreamReader(System.in));
        String option = leerEntrada.readLine();
        try {
            int i = Integer.parseInt(option);
            if (i > max || i < 0) {
                System.out.println("wrong number :(");
                return readOption(max);
            }

            return i;

        } catch (Exception e) {
            System.out.println("the option must be a number");
            return readOption(max);
        }
    }
}
