package nu.wasis.rcmp.compare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class TestRecursiveDirectoryComparer {

    // private static final Logger LOG = LogManager.getLogger();

    private static final String INDICATOR_FILENAME = "test.base";

    private File baseDir;

    @Before
    public void init() throws URISyntaxException {
        baseDir = new File(TestRecursiveDirectoryComparer.class.getResource(INDICATOR_FILENAME).toURI()).getParentFile();
    }

    @Test
    public void testEmptyDirsMatch() throws URISyntaxException, IOException {
        final File left = new File(baseDir, "/base0/emptyDir");
        final File right = new File(baseDir, "/base1/emptyDir");

        final ComparisonResult compareDirectories = RecursiveDirectoryComparer.compareDirectories(left, right);

        assertTrue("Two empty dirs should always match.", compareDirectories.isEqual());
    }

    @Test
    public void testLeftContainsExtraFile() throws IOException {
        final File left = new File(baseDir, "/base0/containsFile");
        final File right = new File(baseDir, "/base1/emptyDir");

        final File missingFile = new File(baseDir, "/base0/containsFile/file.file");

        final ComparisonResult compareDirectories = RecursiveDirectoryComparer.compareDirectories(left, right);
        assertFalse("Left contains extra file.", compareDirectories.isEqual());
        assertEquals("Missing file not correct.", new HashSet<File>(Arrays.asList(missingFile)), compareDirectories.getNotMatched());
    }

    @Test
    public void testRightContainsExtraFile() throws IOException {
        final File left = new File(baseDir, "/base0/emptyDir");
        final File right = new File(baseDir, "/base1/containsFile");

        final File missingFile = new File(baseDir, "/base1/containsFile/file.file");

        final ComparisonResult compareDirectories = RecursiveDirectoryComparer.compareDirectories(left, right);
        assertFalse("Right should contain extra file.", compareDirectories.isEqual());
        assertEquals("Missing file not correct.", new HashSet<File>(Arrays.asList(missingFile)), compareDirectories.getNotMatched());
    }

    @Test
    public void testDetectFileDifference() throws IOException {
        final File left = new File(baseDir, "/base0/containsFile");
        final File right = new File(baseDir, "/base1/differentFile");

        final ComparisonResult compareDirectories = RecursiveDirectoryComparer.compareDirectories(left, right);
        assertFalse("File contents should not match.", compareDirectories.isEqual());
    }

}
