package nz.ac.wgtn.swen301.assignment2.example;

import nz.ac.wgtn.swen301.assignment2.JSONLayout;
import nz.ac.wgtn.swen301.assignment2.MemAppender;
import org.apache.log4j.*;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogRunner {

    private static int count = 0;

    public static void generateLogEvent() {
        if (count >= 2) {
            System.exit(0);
        }
        Logger logger = Logger.getLogger("Log");
        MemAppender appender = new MemAppender("LogRunner");
        appender.setLayout(new JSONLayout());
        logger.addAppender(appender);
        logger.setLevel(Level.INFO);

        Random r = new Random();
        int random = r.nextInt(5);
        if (random == 0) {
            logger.info("INFO");
        }
        if (random == 1) {
            logger.warn("WARN");
        }
        if (random == 2) {
            logger.debug("DEBUG");
        }
        if (random == 3) {
            logger.error("ERROR");
        }
        if (random == 4) {
            logger.fatal("FATAL");
        }
        count++;
        System.out.println(appender.getCurrentLogs().toString());
    }

    public static void main(String[] args) {

        Runnable logRunnable = LogRunner::generateLogEvent;
        ScheduledExecutorService logExec = Executors.newScheduledThreadPool(1);
        logExec.scheduleAtFixedRate(logRunnable, 0, 1, TimeUnit.MINUTES);

    }
}
