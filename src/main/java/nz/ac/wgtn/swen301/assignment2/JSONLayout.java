package nz.ac.wgtn.swen301.assignment2;
import com.google.gson.Gson;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class JSONLayout extends Layout {

    @Override
    public String format(LoggingEvent loggingEvent) {
        Map<String, Object> logMap = new LinkedHashMap<>();
        logMap.put("Logger", loggingEvent.getLoggerName());
        logMap.put("Level", loggingEvent.getLevel().toString());
        logMap.put("Time", loggingEvent.getTimeStamp());
        logMap.put("Thread", loggingEvent.getThreadName());
        logMap.put("Message", loggingEvent.getMessage());

        return new Gson().toJson(logMap);
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    @Override
    public void activateOptions() {
        //no Options to activate
    }
}