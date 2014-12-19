package nu.wasis.rcmp.compare.result;

import java.io.File;

public class Difference {

    private final File left;
    private final File right;
    private final DifferenceType type;

    public Difference(final File left, final File right, final DifferenceType type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }

    public File getLeft() {
        return left;
    }

    public File getRight() {
        return right;
    }

    public DifferenceType getType() {
        return type;
    }

    @Override
    public String toString() {
        final String left = null == this.left ? "[missing]" : this.left.toString();
        final String right = null == this.right ? "[missing]" : this.right.toString();
        final String separator = DifferenceType.CHECKSUM == type ? " <-> " : (null == this.left ? " <- " : " -> ");
        return type + ": " + (DifferenceType.CHECKSUM == type ? "" : " ") + left + separator + right;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Difference other = (Difference) obj;
        if (left == null) {
            if (other.left != null)
                return false;
        } else if (!left.equals(other.left))
            return false;
        if (right == null) {
            if (other.right != null)
                return false;
        } else if (!right.equals(other.right))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
