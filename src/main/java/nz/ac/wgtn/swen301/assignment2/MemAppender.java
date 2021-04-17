package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MemAppender extends AppenderSkeleton implements MemAppenderMBean {

    private static final long maxSize = 1000;
    private static final String dir = "logFiles/";
    private static final String fileSuffix = ".json";

    List<LoggingEvent> eventLog = new LinkedList<>();

    long discardedLogCount = 0;
    long logCount = 0;

    public MemAppender(String name) {
        setName(name);
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        eventLog.add(loggingEvent);
        if (logCount < maxSize) {
            logCount++;
        }
        discardLog();
    }

    @Override
    public void close() {
        //nothing to close
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LoggingEvent> getCurrentLogs() {
        return Collections.unmodifiableList(eventLog);
    }

    public void discardLog(){
        while (eventLog.size() > maxSize) {
            eventLog.remove(0);
            discardedLogCount++;
        }
    }

    @Override
    public String[] getLogs() {
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern(PatternLayout.DEFAULT_CONVERSION_PATTERN);

        String[] logStrings = new String[eventLog.size()];
        for (int i = 0; i < eventLog.size(); i++) {
            logStrings[i] = patternLayout.format(eventLog.get(i));
        }
        return logStrings;
    }

    public long getLogCount() {
        return logCount;
    }

    public long getDiscardedLogCount() {
        return discardedLogCount;
    }

    public void exportToJSON(String filename) throws IOException {
        if (layout instanceof JSONLayout) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<JsonObject> jsonLog = new LinkedList<>();
            for (LoggingEvent logEvent : eventLog) {
                String jsonString = layout.format(logEvent);
                JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
                jsonLog.add(jsonObject);
            }
            String arr = gson.toJson(jsonLog);
            BufferedWriter bw = new BufferedWriter(new FileWriter(dir + filename + fileSuffix));
            bw.write(arr);
            bw.close();
        }
    }
}
