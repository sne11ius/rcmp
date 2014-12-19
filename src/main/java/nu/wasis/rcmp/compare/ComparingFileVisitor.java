package nu.wasis.rcmp.compare;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

import nu.wasis.rcmp.compare.result.Difference;
import nu.wasis.rcmp.compare.result.DifferenceType;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComparingFileVisitor implements FileVisitor<Path> {

    private static final Logger LOG = LogManager.getLogger();

    private final Path p0;
    private final Path p1;

    private final Set<Difference> differences = new HashSet<>();

    public ComparingFileVisitor(final Path p0, final Path p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    @Override
    public FileVisitResult visitFile(final Path path, final BasicFileAttributes attrs) throws IOException {
        final Path p0Relative = p0.relativize(p0.resolve(path));
        final Path p1Relative = p1.resolve(p0Relative);
        if (!p1Relative.toFile().exists()) {
            differences.add(new Difference(path.toFile(), null, DifferenceType.MISSING));
        } else {
            if (path.toFile().isDirectory()) {
                LOG.error("Cannot compute checksum for directory: " + path.toFile().toString());
            } else if (p1Relative.toFile().isDirectory()) {
                LOG.error("Cannot compute checksum for directory: " + p1Relative.toFile().toString());
            } else {
                final long p0Checksum = FileUtils.checksumCRC32(path.toFile());
                final long p1Checksum = FileUtils.checksumCRC32(p1Relative.toFile());
                if (p0Checksum != p1Checksum) {
                    differences.add(new Difference(path.toFile(), p1Relative.toFile(), DifferenceType.CHECKSUM));
                }
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(final Path path, final IOException exc) throws IOException {
        differences.add(new Difference(path.toFile(), null, DifferenceType.ERROR));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    public Set<Difference> getDifferences() {
        return differences;
    }

}
