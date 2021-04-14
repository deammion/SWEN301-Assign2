package nz.ac.wgtn.swen301.assignment2;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;


public class JSONLayout extends Layout {

    @Override
    public String format(LoggingEvent loggingEvent) {
        JSONObject json = new JSONObject();
        json.put("Logger", loggingEvent.getLoggerName());
        json.put("level", loggingEvent.getLevel().toString());
        json.put("Time", loggingEvent.getTimeStamp());
        json.put("thread", loggingEvent.getThreadName());
        json.put("message", loggingEvent.getMessage());

        return json.toString();
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    @Override
    public void activateOptions() {

    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger logger = Logger.getLogger(JSONLayout.class);
        Appender appender = new ConsoleAppender(new JSONLayout());
        logger.addAppender(appender);
        logger.setLevel(Level.INFO);
        logger.warn("This is WARN");
        logger.debug("This is DEBUG");
        logger.error("This is ERROR");
        logger.fatal("This is FATAL");
    }
}