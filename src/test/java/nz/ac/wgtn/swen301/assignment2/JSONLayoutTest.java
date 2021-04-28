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

        assertEquals(logger.getName(), logMap.get("logger"));
        assertEquals("WARN", logMap.get("level"));
        assertEquals("WARN test", logMap.get("message"));
        assertEquals("main", logMap.get("thread"));
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

        assertEquals(logger.getName(), logMap.get("logger"));
        assertEquals("ERROR", logMap.get("level"));
        assertEquals("ERROR test", logMap.get("message"));
        assertEquals("main", logMap.get("thread"));
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

        assertEquals(logger.getName(), logMap.get("logger"));
        assertEquals("DEBUG", logMap.get("level"));
        assertEquals("DEBUG test", logMap.get("message"));
        assertEquals("main", logMap.get("thread"));
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

        assertEquals(logger.getName(), logMap.get("logger"));
        assertEquals("FATAL", logMap.get("level"));
        assertEquals("FATAL test", logMap.get("message"));
        assertEquals("main", logMap.get("thread"));
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

        assertEquals(logger.getName(), logMap.get("logger"));
        assertEquals("INFO", logMap.get("level"));
        assertEquals("INFO test", logMap.get("message"));
        assertEquals("main", logMap.get("thread"));
    }
}
