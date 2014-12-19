package nu.wasis.rcmp.cli;

import java.io.File;

import org.kohsuke.args4j.Option;

public class Options {

    @Option(name = "-d0", usage = "Directory #0 (required)")
    private File d0;

    @Option(name = "-d1", usage = "Directory #1 (required)")
    private File d1;

    public File getD0() {
        return d0;
    }

    public File getD1() {
        return d1;
    }

}
