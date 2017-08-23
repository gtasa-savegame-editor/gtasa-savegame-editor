# GTA:SA Savegame Editor - CLI Tool

> It's for splitting a savegame file into multiple block files and vice versa.

## Usage:

1. Modify paths in `application.properties` to fit your setup.
2. `$ mvn clean install`
3. `$ java -jar target/cli-<version>.jar` (Replace the `<version>` with the actual version) 

You can then type in `help` to get an overview of the commands available.

More from the author:

> Use "copy 1 blabla" to split savegame 1 into blocks. After you modify the block files, use "copy blabla 2" to combine the block files to savegame file 2.
