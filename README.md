# GTA SA savegame editor [![Build Status](https://api.travis-ci.org/gtasa-savegame-editor/gtasa-savegame-editor.svg?branch=master)](https://travis-ci.org/gtasa-savegame-editor) [![Join the chat at https://gitter.im/gtasa-savegame-editor/Lobby](https://badges.gitter.im/gtasa-savegame-editor/Lobby.svg)](https://gitter.im/gtasa-savegame-editor/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![GitHub Pages Badge](https://img.shields.io/badge/docs-gh--pages-lightgrey.svg)](https://gtasa-savegame-editor.github.io/docs/#/)

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

You should now have the following files inside a newly created `target` folder:

- `gtasaveedit-[version]-jar-with-dependencies.jar`
    - This is the main executable. You can run it with `java -jar [jarfile]`.
- `gtasaveedit_[version]_all.deb`
    - This is a Debian package. You can install it on Debian, Ubuntu and other Linux distros based on those.
    - You can also run `alien <debfile>` to create a `.rpm` file that can be installed on Fedora based distros.
- `gtasaveedit-[version]/GTA SA Savegame Editor.app`
    - This is a `.app` file to be used with macOS.