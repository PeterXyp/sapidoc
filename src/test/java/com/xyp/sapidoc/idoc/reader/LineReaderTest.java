package com.xyp.sapidoc.idoc.reader;

import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.util.ResourceUtil;
import java.io.IOException;
import java.util.List;
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
    private int totalLineNumber;

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
            totalLineNumber = reader.getTotalLineNumber();
        } catch (IOException ex) {
            Logger.getLogger(LineReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindTag() {
        Line[] result1 = reader.findTag(TagEnum.IDOC);
        Line[] result2 = reader.findTag(TagEnum.IDOC, 1);
        Line[] result3 = reader.findTag(TagEnum.IDOC, 549, 5808);
        assertEquals(549, result1[0].getLineNumber());
        assertEquals(549, result2[0].getLineNumber());
        assertEquals(549, result3[0].getLineNumber());
        assertEquals(5808, result1[1].getLineNumber());
        assertEquals(5808, result2[1].getLineNumber());
        assertEquals(5808, result3[1].getLineNumber());
    }

    @Test
    public void testFindTags() {
        List<Line[]> result = reader.findTags(TagEnum.GROUP, 1, 5809);
        assertEquals(1026, result.get(0)[0].getLineNumber());//G1
        assertEquals(1417, result.get(0)[1].getLineNumber());//G1
        assertEquals(1709, result.get(2)[0].getLineNumber());//G3
        assertEquals(4569, result.get(2)[1].getLineNumber());//G3
    }

    @Test
    public void testFindStructureIndexByLineNumber() {
        assertEquals(0, reader.findStructureIndexByLineNumber(0, true));
        assertEquals(0, reader.findStructureIndexByLineNumber(1, true));
        assertEquals(1, reader.findStructureIndexByLineNumber(2, true));
        assertEquals(2, reader.findStructureIndexByLineNumber(4, true));
        assertEquals(3, reader.findStructureIndexByLineNumber(4, false));
        assertEquals(6, reader.findStructureIndexByLineNumber(349, true));
        assertEquals(7, reader.findStructureIndexByLineNumber(349, false));
        assertEquals(269, reader.findStructureIndexByLineNumber(5809, false));
        assertEquals(269, reader.findStructureIndexByLineNumber(5810, false));
    }
//    @Test
//    public void testFindTags() {
//        List<Line[]> results = reader.findTags(TagEnum.SEGMENT);
//        System.out.println("segments number " + results.size());
//        for (Line[] result : results) {
//            System.out.println("start line : " + result[0]);
//            System.out.println("end line : " + result[1]);
//            System.out.println("");
//        }
//    }
}