package nu.wasis.rcmp.compare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;

import nu.wasis.rcmp.compare.result.Difference;

public class RecursiveDirectoryComparer {

    // private static final Logger LOG = LogManager.getLogger();

    public static Set<Difference> compareDirectories(final File d0, final File d1) throws IOException {
        if (!d0.exists()) {
            throw new IllegalArgumentException("File d0 does not exist.");
        }
        if (!d1.exists()) {
            throw new IllegalArgumentException("File d1 does not exist.");
        }

        ComparingFileVisitor visitor = new ComparingFileVisitor(d0.toPath(), d1.toPath());
        Files.walkFileTree(d0.toPath(), visitor);

        final Set<Difference> allDifferences = visitor.getDifferences();

        visitor = new ComparingFileVisitor(d1.toPath(), d0.toPath());
        Files.walkFileTree(d1.toPath(), visitor);
        // allDifferences.addAll(visitor.getDifferences().stream().filter(diff
        // -> !allDifferences.contains(new Difference(diff.getRight(),
        // diff.getLeft(), diff.getType()))).collect(Collectors.toList()));
        allDifferences.addAll(visitor.getDifferences().stream().map(diff -> new Difference(diff.getRight(), diff.getLeft(), diff.getType())).collect(Collectors.toList()));
        return allDifferences;
    }

}
