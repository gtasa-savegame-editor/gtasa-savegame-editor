# GTA:SA Savegame Editor - CLI Tool

> It's for splitting a savegame file into multiple block files and vice versa.

## Usage:

1. Modify paths in `application.properties` to fit your setup.
2. `$ mvn clean install`
3. `$ java -jar target/cli-<version>.jar` (Replace the `<version>` with the actual version) 

You can then type in `help` to get an overview of the commands available.

### Workflow:

1. Use the `copy` command to split a savegame into blocks
    - ex: `copy 1 foo` splits savegame one into blocks in `foo`
2. Modify/Analyze the block files
3. Merge the blocks back into another savegame by using `copy` the other way around
    - ex: `copy foo 2` merges the blocks in `foo` into savefile two
