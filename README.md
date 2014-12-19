#rcmp

Compare directories recursively

## Build

Run `gradlew dist`.

Result will be in `./build/install/rcmp/`

## Usage
    rcmp [options]
       -d0 DIRECTORY : Directory #0 (required)
       -d1 DIRECTORY : Directory #1 (required)

## Example run
    rcmp -d0 ~/src/rcmp/src/main/java/ -d1 ~/src/rcmp_diff/src/main/java/
    Comparing: 
	    /home/user0/src/rcmp/src/main/java
	    /home/user0/src/rcmp_diff/src/main/java
    Directories do not match.
    Differences:
        MISSING:  /home/user0/src/rcmp/src/main/java/nu/wasis/rcmp/compare/result/DifferenceType.java -> [missing]
        MISSING:  /home/user0/src/rcmp/src/main/java/nu/wasis/rcmp/compare/result/Difference.java -> [missing]
        CHECKSUM: /home/user0/src/rcmp/src/main/java/nu/wasis/rcmp/cli/RecursiveCompare.java <-> /home/user0/src/rcmp_diff/src/main/java/nu/wasis/rcmp/cli/RecursiveCompare.java
        MISSING:  [missing] <- /home/user0/src/rcmp_diff/src/main/java/nu/wasis/rcmp/compare/ComparisonResult.java
        CHECKSUM: /home/user0/src/rcmp/src/main/java/nu/wasis/rcmp/compare/RecursiveDirectoryComparer.java <-> /home/user0/src/rcmp_diff/src/main/java/nu/wasis/rcmp/compare/RecursiveDirectoryComparer.java
        CHECKSUM: /home/user0/src/rcmp/src/main/java/nu/wasis/rcmp/compare/ComparingFileVisitor.java <-> /home/user0/src/rcmp_diff/src/main/java/nu/wasis/rcmp/compare/ComparingFileVisitor.java

## Wtf?
Don't worry: It's just a simple test project for gradle.
