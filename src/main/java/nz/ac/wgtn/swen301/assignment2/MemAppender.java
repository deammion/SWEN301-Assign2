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
   //Max allowable amount of stored log files
    private static final long maxSize = 1000;

    //local (to project) directory to store files
    private static final String dir = "logFiles/";
    private static final String fileSuffix = ".json";

    //LinkList used as each element is linked to those adjacent to them
    List<LoggingEvent> eventLog = new LinkedList<>();

    //Longs used to track log count and discarded log count
    long discardedLogCount = 0;
    long logCount = 0;

    public MemAppender(String name) {
        setName(name);
    }

    /**
     * Add log event
     * @param loggingEvent - log event to add
     */
    @Override
    protected void append(LoggingEvent loggingEvent) {
        eventLog.add(loggingEvent);
        if (logCount < maxSize) {
            logCount++;
        }
        discardLog();
    }

    /**
     * Close MemAppender
     */
    @Override
    public void close() {
        //nothing to close
    }

    /**
     *
     * @return boolean - True if requires layout
     */
    @Override
    public boolean requiresLayout() {
        return false;
    }

    /**
     * Set MemAppender Name
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns all stored logs
     * @return List - list of all stored log events (unmodifiable)
     */
    public List<LoggingEvent> getCurrentLogs() {
        return Collections.unmodifiableList(eventLog);
    }

    /**
     * Discards the oldest log
     */
    public void discardLog(){
        while (eventLog.size() > maxSize) {
            eventLog.remove(0);
            discardedLogCount++;
        }
    }

    /**
     * Gets
     * @return String Array - Array of Logs formatted as strings
     */
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

    /**
     * Return the current log count
     * @return long - current logs stored
     */
    public long getLogCount() {
        return logCount;
    }

    /**
     * Return the amount of discarded logs
     * @return long - discarded log count
     */
    public long getDiscardedLogCount() {
        return discardedLogCount;
    }

    /**
     * Exports the current stored logs to a Json file
     * @param filename - name to store logs under
     * @throws IOException
     */
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
