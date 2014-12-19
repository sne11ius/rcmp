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
    rcmp -d0 ~/src/rcmp -d1 ~/src/rcmp_diff/
    Comparing: 
	    /home/user0/src/rcmp
	    /home/user0/src/rcmp_diff
    Directories do not match.
    Unmatched Files:
	    /home/user0/src/rcmp_diff/build/classes/main/nu/wasis/rcmp/cli/RecursiveCompare.class
	    /home/user0/src/rcmp_diff/.gradle/2.2.1/taskArtifacts/fileHashes.bin
	    /home/user0/src/rcmp_diff/.git/HEAD
	    /home/user0/src/rcmp_diff/.gradle/2.2.1/taskArtifacts/taskArtifacts.bin
	    /home/user0/src/rcmp_diff/build/reports/tests/classes/nu.wasis.rcmp.compare.TestRecursiveDirectoryComparer.html
	    /home/user0/src/rcmp_diff/build.gradle
	    /home/user0/src/rcmp_diff/build/libs/rcmp.jar
	    /home/user0/src/rcmp_diff/build/reports/tests/index.html
	    /home/user0/src/rcmp_diff/.gradle/2.2.1/taskArtifacts/cache.properties.lock
	    /home/user0/src/rcmp_diff/build/install/rcmp/lib/rcmp.jar
	    /home/user0/src/rcmp_diff/build/test-results/binary/test/results.bin
	    /home/user0/src/rcmp_diff/.gradle/2.2.1/taskArtifacts/fileSnapshots.bin
	    /home/user0/src/rcmp_diff/src/main/java/nu/wasis/rcmp/cli/RecursiveCompare.java
	    /home/user0/src/rcmp_diff/README.md
	    /home/user0/src/rcmp_diff/build/test-results/TEST-nu.wasis.rcmp.compare.TestRecursiveDirectoryComparer.xml
	    /home/user0/src/rcmp_diff/build/reports/tests/packages/nu.wasis.rcmp.compare.html
	    /home/user0/src/rcmp_diff/bin/nu/wasis/rcmp/cli/RecursiveCompare.class
	    /home/user0/src/rcmp_diff/src/main/resources/log4j2.xml
	    /home/user0/src/rcmp_diff/.gradle/2.2.1/taskArtifacts/outputFileStates.bin
