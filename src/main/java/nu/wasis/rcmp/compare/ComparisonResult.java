package nu.wasis.rcmp.compare;

import java.io.File;
import java.util.Set;

public class ComparisonResult {

    private final boolean isEqual;
    private final String message;
    private final Set<File> notMatched;

    public ComparisonResult(final boolean isEqual, final String message, final Set<File> notMatched) {
        this.isEqual = isEqual;
        this.message = message;
        this.notMatched = notMatched;
    }

    public boolean isEqual() {
        return isEqual;
    }

    public String getMessage() {
        return message;
    }

    public Set<File> getNotMatched() {
        return notMatched;
    }

}
