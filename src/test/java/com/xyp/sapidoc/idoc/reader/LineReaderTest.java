package com.xyp.sapidoc.idoc.reader;

import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.util.ResourceUtil;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yunpeng_Xu
 */
public class LineReaderTest {

    private LineReader reader;

    public LineReaderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            reader = new LineReader(ResourceUtil.loadClasspathResource("ORDERS05.txt"));
        } catch (IOException ex) {
            Logger.getLogger(LineReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readTagContent method, of class LineReader.
     */
//    @Test
    public void testReadTagContent() {
        System.out.println("readTagContent");
        TagEnum tag = null;
        List expResult = null;
        List result = reader.readTagContent(tag);
        assertEquals(expResult, result);
    }

    /**
     * Test of close method, of class LineReader.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        reader.close();
    }

    /**
     * Test of getContent method, of class LineReader.
     */
    @Test
    public void testGetContent() {
        List<String> result = reader.getContent();
        assertEquals(5809, result.size());
    }

    /**
     * Test of getTagLineNumberMap method, of class LineReader.
     */
    @Test
    public void testGetTagLineNumberMap() {
        System.out.println("getTagLineNumberMap");
        Map<Integer, String> result = reader.getTags();
        assertEquals(270, result.size());
    }
}