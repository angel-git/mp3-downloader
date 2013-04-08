package com.ags.mp3.downloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 1/02/13
 *         Time: 20:07
 */
public class Mp3Downloader {


    public void saveMp3(String url,String folder, String fileName) throws IOException {
        URL website = new URL(url);
        URLConnection conexion = website.openConnection();
        conexion.connect();
        int lenghtOfFile = conexion.getContentLength();

        InputStream input = new BufferedInputStream(website.openStream());
        FileOutputStream fos = new FileOutputStream(folder + System.getProperty("file.separator")+fileName + ".mp3");
        byte data[] = new byte[1024];

        int count;

        long total = 0;
        double NANOS_PER_SECOND = 1000000000.0;
        double BYTES_PER_MIB = 1024 * 1024;

        System.out.println("downloading song: " + fileName);

        Long start = System.nanoTime();
        while ((count = input.read(data)) != -1) {
            total += count;
            fos.write(data, 0, count);
            BigDecimal speed = new BigDecimal(NANOS_PER_SECOND / BYTES_PER_MIB * total / (System.nanoTime() - start + 1));
            speed = speed.setScale(2, RoundingMode.CEILING);
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            System.out.print(nf.format(speed.doubleValue()) + "Mbit/s " + ((int) ((total * 100) / lenghtOfFile)) + "%\r");
        }

        fos.flush();
        fos.close();
        input.close();

    }

}
