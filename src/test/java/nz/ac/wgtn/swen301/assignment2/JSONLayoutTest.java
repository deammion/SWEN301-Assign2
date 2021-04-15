package nz.ac.wgtn.swen301.assignment2;

import org.apache.log4j.*;
import org.json.JSONObject;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertEquals;


/**
 * Unit test for simple App.
 */
public class JSONLayoutTest
{
    /**
     * Test WARN Functionality
     */
    @Test
    public void Test_01() {
        Writer writer = new StringWriter();
        Logger logger = Logger.getLogger("test_01");
        Appender appender = new WriterAppender(new JSONLayout(), writer);
        logger.addAppender(appender);
        logger.warn("WARN test");

        String string = writer.toString();
        JSONObject jsonObject = new JSONObject(string);

        assertEquals(logger.getName(), jsonObject.get("Logger"));
        assertEquals("WARN", jsonObject.get("Level"));
        assertEquals("WARN test", jsonObject.get("Message"));
        assertEquals("main", jsonObject.get("Thread"));
    }

    /**
     * Test ERROR Functionality
     */
    @Test
    public void Test_02() {
        Writer writer = new StringWriter();
        Logger logger = Logger.getLogger("test_02");
        Appender appender = new WriterAppender(new JSONLayout(), writer);
        logger.addAppender(appender);
        logger.error("ERROR test");

        String string = writer.toString();
        JSONObject jsonObject = new JSONObject(string);

        assertEquals(logger.getName(), jsonObject.get("Logger"));
        assertEquals("ERROR", jsonObject.get("Level"));
        assertEquals("ERROR test", jsonObject.get("Message"));
        assertEquals("main", jsonObject.get("Thread"));
    }

    /**
     * Test DEBUG Functionality
     */
    @Test
    public void Test_03() {
        Writer writer = new StringWriter();
        Logger logger = Logger.getLogger("test_03");
        Appender appender = new WriterAppender(new JSONLayout(), writer);
        logger.addAppender(appender);
        logger.debug("DEBUG test");

        String string = writer.toString();
        JSONObject jsonObject = new JSONObject(string);

        assertEquals(logger.getName(), jsonObject.get("Logger"));
        assertEquals("DEBUG", jsonObject.get("Level"));
        assertEquals("DEBUG test", jsonObject.get("Message"));
        assertEquals("main", jsonObject.get("Thread"));
    }

    /**
     * Test FATAL Functionality
     */
    @Test
    public void Test_04() {
        Writer writer = new StringWriter();
        Logger logger = Logger.getLogger("test_04");
        Appender appender = new WriterAppender(new JSONLayout(), writer);
        logger.addAppender(appender);
        logger.fatal("FATAL test");

        String string = writer.toString();
        JSONObject jsonObject = new JSONObject(string);

        assertEquals(logger.getName(), jsonObject.get("Logger"));
        assertEquals("FATAL", jsonObject.get("Level"));
        assertEquals("FATAL test", jsonObject.get("Message"));
        assertEquals("main", jsonObject.get("Thread"));
    }

    /**
     * Test INFO Functionality
     */
    @Test
    public void Test_05() {
        Writer writer = new StringWriter();
        Logger logger = Logger.getLogger("test_05");
        Appender appender = new WriterAppender(new JSONLayout(), writer);
        logger.addAppender(appender);
        logger.info("INFO test");

        String string = writer.toString();
        JSONObject jsonObject = new JSONObject(string);

        assertEquals(logger.getName(), jsonObject.get("Logger"));
        assertEquals("INFO", jsonObject.get("Level"));
        assertEquals("INFO test", jsonObject.get("Message"));
        assertEquals("main", jsonObject.get("Thread"));
    }
}
