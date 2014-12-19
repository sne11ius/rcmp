package nu.wasis.rcmp.cli;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

import nu.wasis.rcmp.compare.RecursiveDirectoryComparer;
import nu.wasis.rcmp.compare.result.Difference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class RecursiveCompare {

    private static final PrintStream OUT = System.out;
    private static final Logger LOG = LogManager.getLogger();

    public static void main(final String... args) throws IOException {
        final Options options = new Options();
        final CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
        } catch (final CmdLineException e) {
            LOG.error(e);
            return;
        }

        if (null == options.getD0() || null == options.getD1()) {
            printHelp(parser);
            return;
        }

        if (!options.getD0().exists()) {
            OUT.println("File d0 `" + options.getD0() + "' does not exist.");
            return;
        }
        if (!options.getD1().exists()) {
            OUT.println("File d1 `" + options.getD1() + "' does not exist.");
            return;
        }

        OUT.println("Comparing: \n\t" + options.getD0() + "\n\t" + options.getD1());

        final Set<Difference> differences = RecursiveDirectoryComparer.compareDirectories(options.getD0(), options.getD1());
        if (differences.isEmpty()) {
            OUT.println("Directories match.");
        } else {
            OUT.println("Directories do not match.");
            OUT.println("Differences:");
            for (final Difference diff : differences) {
                OUT.println(diff);
            }
        }
    }

    private static void printHelp(final CmdLineParser parser) throws IOException {
        final Writer stringWriter = new StringWriter();
        stringWriter.append("Usage: rcmp [options]\n");
        parser.printUsage(stringWriter, null);
        OUT.println(stringWriter.toString());
    }

}
