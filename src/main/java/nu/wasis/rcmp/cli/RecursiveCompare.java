package nu.wasis.rcmp.cli;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import nu.wasis.rcmp.compare.ComparisonResult;
import nu.wasis.rcmp.compare.RecursiveDirectoryComparer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class RecursiveCompare {

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
            System.out.println("File d0 `" + options.getD0() + "' does not exist.");
            return;
        }
        if (!options.getD1().exists()) {
            System.out.println("File d1 `" + options.getD1() + "' does not exist.");
            return;
        }

        System.out.println("Comparing: \n\t" + options.getD0() + "\n\t" + options.getD1());

        final ComparisonResult result = RecursiveDirectoryComparer.compareDirectories(options.getD0(), options.getD1());
        if (result.isEqual()) {
            System.out.println("Directories match.");
        } else {
            System.out.println("Directories do not match.");
            System.out.println("Unmatched Files:");
            for (final File file : result.getNotMatched()) {
                System.out.println("\t" + file.getPath());
            }
        }
    }

    private static void printHelp(final CmdLineParser parser) throws IOException {
        final Writer stringWriter = new StringWriter();
        stringWriter.append("Usage: rcmp [options]\n");
        parser.printUsage(stringWriter, null);
        System.out.println(stringWriter.toString());
    }

}
