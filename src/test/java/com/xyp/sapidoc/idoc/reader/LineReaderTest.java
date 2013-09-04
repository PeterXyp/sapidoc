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
     * Test of getContent method, of class LineReader.
     */
//    @Test
    public void testReadContent() {
    }

    @Test
    public void testFindTag() {
        findTag(TagEnum.IDOC, 1, 549, 5808);
        findTag(TagEnum.RECORD_SECTION, 1, 1, 547);
//        findTag(TagEnum.GROUP, 0, 1025, 1416);//G1
//        findTag(TagEnum.GROUP, 66, 1629, 1707);//G2
        findTag(TagEnum.GROUP, 1709, 1709, 4569);//G3
        findTag(TagEnum.GROUP, 2429, 2429, 2595);//G4
//        findTag(TagEnum.GROUP, 115, 2659, 3050);//G5
//        findTag(TagEnum.GROUP, 129, 3124, 3271);//G6
//        findTag(TagEnum.GROUP, 134, 3177, 3270);//G7
//        findTag(TagEnum.GROUP, 157, 3387, 3449);//G8
//        findTag(TagEnum.GROUP, 167, 3450, 4567);//G9
//        findTag(TagEnum.GROUP, 212, 4520, 4566);//G10
//        findTag(TagEnum.GROUP, 224, 4569, 4966);//G11
//        findTag(TagEnum.GROUP, 246, 4967, 5765);//G12
    }

    public void findTag(TagEnum tag, int fromIndex, int exp1, int exp2) {
        Line[] result = reader.findTag(tag, fromIndex);
        System.out.println("s"+result[0]);
        System.out.println("e"+result[1]);
        assertEquals(exp1, result[0].getLineNumber());
        assertEquals(exp2, result[1].getLineNumber());
    }
    
//    @Test
    public void testGenerateStructure() throws IOException{
        reader.generateStructure();
    }
    @Test
    public void testReadTagContent(){
        List<Line> lines = reader.readTagContent(TagEnum.CONTROL_RECORD);
        for (Line line : lines) {
            System.out.println(line);
        }
    }
}