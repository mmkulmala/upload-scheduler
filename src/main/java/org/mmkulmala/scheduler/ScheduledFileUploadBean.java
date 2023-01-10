package org.mmkulmala.scheduler;

import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class ScheduledFileUploadBean {

    private static final Logger LOGGER
            = Logger.getLogger(ScheduledFileUploadBean.class);

    @ConfigProperty(name = "uploadfile.name")
    String UPLOAD_FILE_NAME;

    @ConfigProperty(name = "uploadfile.directory")
    String UPLOAD_FILE_DIR;

    @ConfigProperty(name = "uploadfile.url")
    String UPLOAD_FILE_URL;

    @Scheduled(cron = "{cron.upload.expr}")
    public void getOui() {

        String fileName = UPLOAD_FILE_DIR + getTimeStamp()  +"-" + UPLOAD_FILE_NAME;
        LOGGER.info("> preparing to upload file: " + fileName);
        try (java.io.BufferedInputStream in =
                new java.io.BufferedInputStream(new java.net.URL(UPLOAD_FILE_URL).openStream());
             java.io.FileOutputStream fileOutputStream =
                        new java.io.FileOutputStream(UPLOAD_FILE_DIR + getTimeStamp()  +"-" + UPLOAD_FILE_NAME)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            LOGGER.info(">> file: " + fileName + " uploaded");
        } catch (IOException e) {
            LOGGER.error("Exception occurred: ", e);
        }
    }

    private static String getTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

}
