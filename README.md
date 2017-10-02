# GTA SA savegame editor [![Build Status](https://travis-ci.org/lfuelling/gtasa-savegame-editor.svg?branch=master)](https://travis-ci.org/lfuelling/gtasa-savegame-editor) [![Join the chat at https://gitter.im/gtasa-savegame-editor/Lobby](https://badges.gitter.im/gtasa-savegame-editor/Lobby.svg)](https://gitter.im/gtasa-savegame-editor/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

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

If you use macOS, you should also have a `.app` file in `target/gtasaveedit-<version>-SNAPSHOT` that you can run.
