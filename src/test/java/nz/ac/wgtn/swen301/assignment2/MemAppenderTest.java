package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MemAppenderTest {

    /**
     * Testing DEBUG level logging
     */
    @Test
    public void Test_01() {
        Logger logger = Logger.getLogger("Test_01");
        MemAppender appender = new MemAppender("MemAppender_01");
        appender.setLayout(new JSONLayout());
        logger.addAppender(appender);
        logger.setLevel(Level.DEBUG);
        logger.warn("WARN");
        logger.info("INFO");
        logger.fatal("FATAL");
        logger.error("ERROR");
        logger.debug("DEBUG");

        assert(appender.getLogCount() == 5);
        assertEquals ("WARN", appender.getCurrentLogs().get(0).getMessage());
        assertEquals ("INFO", appender.getCurrentLogs().get(1).getMessage());
        assertEquals ("FATAL", appender.getCurrentLogs().get(2).getMessage());
        assertEquals ("ERROR", appender.getCurrentLogs().get(3).getMessage());
        assertEquals ("DEBUG", appender.getCurrentLogs().get(4).getMessage());
        assertEquals (Level.DEBUG, logger.getLevel());
        assertEquals ("MemAppender_01", appender.getName());
    }

    /**
     * Testing discardLog() functionality, and WARN level
     */
    @Test
    public void Test_02() {
        Logger logger = Logger.getLogger("Test_02");
        MemAppender appender = new MemAppender("MemAppender_02");
        appender.setLayout(new JSONLayout());
        logger.addAppender(appender);
        logger.setLevel(Level.WARN);
        for (int i = 0; i < 1005; i++){
            logger.warn("WARN" + i);
        }
        logger.info("INFO");

        assert (appender.getLogCount() == 1000);
        assert (appender.getDiscardedLogCount() == 5);
        // confirms correct level set as info message is added last
        // meaning it would be warn6, if added
        assertEquals ("WARN5", appender.getCurrentLogs().get(0).getMessage());
        assertEquals (Level.WARN, logger.getLevel());
        assertEquals ("MemAppender_02", appender.getName());
    }

    /**
     * Test FATAL level
     */
    @Test
    public void Test_03() {
        Logger logger = Logger.getLogger("Test_03");
        MemAppender appender = new MemAppender("MemAppender_03");
        appender.setLayout(new JSONLayout());
        logger.addAppender(appender);
        logger.setLevel(Level.FATAL);
        logger.warn("WARN");
        logger.info("INFO");
        logger.fatal("FATAL");
        logger.error("ERROR");
        logger.debug("DEBUG");

        assert (appender.getLogCount() == 1);
        assertEquals ("FATAL", appender.getCurrentLogs().get(0).getMessage());
        assertEquals (Level.FATAL, logger.getLevel());
        assertEquals ("MemAppender_03", appender.getName());
    }

    /**
     * Tests getLogs()
     */
    @Test
    public void Test_04() {
        Logger logger = Logger.getLogger("Test_04");
        MemAppender appender = new MemAppender("MemAppender_04");
        appender.setLayout(new JSONLayout());
        logger.addAppender(appender);
        logger.setLevel(Level.DEBUG);
        logger.warn("WARN");
        logger.info("INFO");
        logger.fatal("FATAL");
        logger.error("ERROR");
        logger.debug("DEBUG");
        String[] logStringArray = appender.getLogs();
        assertEquals("WARN\r\n",logStringArray[0]);
    }

    /**
     * Test exportToJson
     *
     * @throws IOException
     */
    @Test
    public void Test_05() throws IOException {
        String dir = "logFiles/Test_05.json";
        String fileName = "Test_05";
        Logger logger = Logger.getLogger("Test_05");
        MemAppender appender = new MemAppender("MemAppender_05");
        appender.setLayout(new JSONLayout());
        logger.addAppender(appender);
        logger.setLevel(Level.DEBUG);
        logger.warn("WARN");
        logger.info("INFO");
        logger.fatal("FATAL");
        logger.error("ERROR");
        logger.debug("DEBUG");
        appender.exportToJSON(fileName);

        try {
            BufferedReader br =new BufferedReader(new FileReader(dir));
            String fileString = br.lines().collect(Collectors.joining());

            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(fileString, JsonArray.class);
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

            assertEquals(jsonObject.get("Message").getAsString(),appender.getCurrentLogs().get(0).getMessage());
        } catch (IOException e) {
            fail("Invalid filepath");
        }
    }

    /**
     * test requiresLayout()
     */
    @Test
    public void Test_06() {
        assertEquals(new MemAppender("Test_06").requiresLayout(), false);
    }
}
