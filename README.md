# GTA SA savegame editor [![Build Status](https://api.travis-ci.org/gtasa-savegame-editor/gtasa-savegame-editor.svg?branch=master)](https://travis-ci.org/gtasa-savegame-editor) [![Join the chat at https://gitter.im/gtasa-savegame-editor/Lobby](https://badges.gitter.im/gtasa-savegame-editor/Lobby.svg)](https://gitter.im/gtasa-savegame-editor/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![GitHub Pages Badge](https://img.shields.io/badge/docs-gh--pages-70dcf4.svg)](https://gtasa-savegame-editor.github.io/docs/#/)

This is a modified version of the software that can be found on [paulinternet.nl](http://paulinternet.nl/?page=sa).

This version contains the following features (additionally to the ones found in the original version):
- requires Java >= 8
- macOS specific fixes
- support for german/european steam versions of the game
- support for macOS Steam version of the game
- support for Linux Steam version of the game (SteamPlay/Proton)
- support for editing garages and cars in them
- support for transferring savegames from Android
    - support for *reading* Android savegames without crashing is still in development
- notifications about new versions
- different download options (additionally to `.jar`):
    - a `.exe` for windows
    - a `.app` (and `.dmg`) for macOS
    - a `.deb` for Debian/Ubuntu
    - a `.rpm` for Fedora/CentOS

## Downloading

Just go to the [Releases page](https://github.com/lfuelling/gtasa-savegame-editor/releases) and download the file you need.

## Building

To build the application:

```bash
$ mvn clean install -DskipTests=true
```

You should now have the following files inside a newly created `target` folder:

- `gtasaveedit-[version]-jar-with-dependencies.jar`
    - This is the main executable. You can run it with `java -jar [jarfile]`.

### Profiles

You can also build specific application formats. For example: `mvn clean package -Pwindows` will create a `.exe` file.

Available profiles:
- `macOS`
    - builds a `.dmg` and `.app` file
- `deb`
    - builds a `.deb` file
    - You can run `alien --scripts -r <debfile>` afterwards to create a `.rpm` file that can be installed on Fedora based distros.
- `windows`
    - builds a `.exe`
