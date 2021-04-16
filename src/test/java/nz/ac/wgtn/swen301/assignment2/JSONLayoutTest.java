package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import org.apache.log4j.*;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

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

        Map logMap = new Gson().fromJson(writer.toString(), Map.class);

        assertEquals(logger.getName(), logMap.get("Logger"));
        assertEquals("WARN", logMap.get("Level"));
        assertEquals("WARN test", logMap.get("Message"));
        assertEquals("main", logMap.get("Thread"));
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

        Map logMap = new Gson().fromJson(writer.toString(), Map.class);

        assertEquals(logger.getName(), logMap.get("Logger"));
        assertEquals("ERROR", logMap.get("Level"));
        assertEquals("ERROR test", logMap.get("Message"));
        assertEquals("main", logMap.get("Thread"));
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

        Map logMap = new Gson().fromJson(writer.toString(), Map.class);

        assertEquals(logger.getName(), logMap.get("Logger"));
        assertEquals("DEBUG", logMap.get("Level"));
        assertEquals("DEBUG test", logMap.get("Message"));
        assertEquals("main", logMap.get("Thread"));
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

        Map logMap = new Gson().fromJson(writer.toString(), Map.class);

        assertEquals(logger.getName(), logMap.get("Logger"));
        assertEquals("FATAL", logMap.get("Level"));
        assertEquals("FATAL test", logMap.get("Message"));
        assertEquals("main", logMap.get("Thread"));
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

        Map logMap = new Gson().fromJson(writer.toString(), Map.class);

        assertEquals(logger.getName(), logMap.get("Logger"));
        assertEquals("INFO", logMap.get("Level"));
        assertEquals("INFO test", logMap.get("Message"));
        assertEquals("main", logMap.get("Thread"));
    }
}
