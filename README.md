# GTA SA savegame editor [![Build Status](https://travis-ci.org/lfuelling/gtasa-savegame-editor.svg?branch=master)](https://travis-ci.org/lfuelling/gtasa-savegame-editor)

This is a modified version of the software that can be found on [paulinternet.nl](http://paulinternet.nl/?page=sa).

This version contains mostly macOS specific fixes and requires Java > 8. That said, if you try to run it on Windows or Linux it may crash or be missing some features.

This doesn't mean that you shouldn't run it on those systems! I heavily advise you to do this and report any bugs you find as issue.

## Downloading

Just go to the [Releases page](https://github.com/lfuelling/gtasa-savegame-editor/releases) and download the file you need.


## Building

To build the application:

```bash
$ mvn clean install -DskipTests=true
```

You should now have a `.jar` file in the `target` folder. Run it.
