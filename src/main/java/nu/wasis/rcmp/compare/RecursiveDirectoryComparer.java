package nu.wasis.rcmp.compare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RecursiveDirectoryComparer {

    // private static final Logger LOG = LogManager.getLogger();

    public static ComparisonResult compareDirectories(final File d0, final File d1) throws IOException {
        if (!d0.exists()) {
            throw new IllegalArgumentException("File d0 does not exist.");
        }
        if (!d1.exists()) {
            throw new IllegalArgumentException("File d1 does not exist.");
        }

        ComparingFileVisitor visitor = new ComparingFileVisitor(d0.toPath(), d1.toPath());
        Files.walkFileTree(d0.toPath(), visitor);

        if (visitor.getUnmatchedFiles().isEmpty()) {
            visitor = new ComparingFileVisitor(d1.toPath(), d0.toPath());
            Files.walkFileTree(d1.toPath(), visitor);
            if (visitor.getUnmatchedFiles().isEmpty()) {
                return new ComparisonResult(true, "", visitor.getUnmatchedFiles());
            } else {
                return new ComparisonResult(false, "Not all files do match.", visitor.getUnmatchedFiles());
            }
        } else {
            return new ComparisonResult(false, "Not all equal: " + visitor.getUnmatchedFiles(), visitor.getUnmatchedFiles());
        }
    }

}
