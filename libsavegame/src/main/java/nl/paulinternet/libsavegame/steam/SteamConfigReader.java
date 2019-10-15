package nl.paulinternet.libsavegame.steam;

import nl.paulinternet.libsavegame.io.IOUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SteamConfigReader {
    /* Tries to read the steam configuration file.
     * If anything goes wrong (file does not exists, bad formatted config file), null is returned.
     */
    public static SteamConfigNode readSteamConfig(File steamDir) {
        try {
            File configFile = new File(steamDir, "config/config.vdf");
            SteamConfigReader reader = new SteamConfigReader(new String(IOUtil.readFile(configFile), StandardCharsets.UTF_8));
            return reader.read(true);
        } catch (IOException e) {
            return null;
        }
    }

    private static final int EOF = -1, TOKEN_OPEN = 0, TOKEN_CLOSE = 1, TOKEN_STRING = 2;

    private static class ConfigFileException extends IOException {
    }

    ;

    private String data;
    private int pos;

    private SteamConfigReader(String data) {
        this.data = data;
    }

    private SteamConfigNode read(boolean isRootNode) throws ConfigFileException {
        SteamConfigNode node = new SteamConfigNode();

        while (true) {
            switch (getTokenType()) {
                case EOF:
                    if (!isRootNode) throw new ConfigFileException();
                    return node;
                case TOKEN_OPEN:
                    throw new ConfigFileException();
                case TOKEN_CLOSE:
                    if (isRootNode) throw new ConfigFileException();
                    return node;
                case TOKEN_STRING:
                    String key = readString();
                    switch (getTokenType()) {
                        case TOKEN_OPEN:
                            node.putSubNode(key, read(false));
                            break;
                        case TOKEN_STRING:
                            node.putString(key, readString());
                            break;
                        default:
                            throw new ConfigFileException();
                    }
            }
        }
    }

    private int getTokenType() throws ConfigFileException {
        // Skip whitespace
        while (pos < data.length() && Character.isWhitespace(data.charAt(pos))) {
            pos++;
        }

        // Check if at the end
        if (pos == data.length()) return EOF;

        // Get character
        switch (data.charAt(pos++)) {
            case '{':
                return TOKEN_OPEN;
            case '}':
                return TOKEN_CLOSE;
            case '"':
                return TOKEN_STRING;
            default:
                throw new ConfigFileException();
        }
    }

    private String readString() throws ConfigFileException {
        // Reads a string, assuming that the first " has already been read.
        StringBuilder b = new StringBuilder();
        while (pos < data.length()) {
            char c = data.charAt(pos++);
            switch (c) {
                case '"':
                    return b.toString();
                case '\\':
                    if (pos == data.length()) throw new ConfigFileException();
                    char c2 = data.charAt(pos++);
                    switch (c2) {
                        case '\\':
                            b.append('\\');
                            break;
                        case '\'':
                            b.append('\'');
                            break;
                        case '"':
                            b.append('"');
                            break;
                        case 't':
                            b.append('\t');
                            break;
                        case 'r':
                            b.append('\r');
                            break;
                        case 'n':
                            b.append('\n');
                            break;
                        case '0':
                            b.append('\0');
                            break;
                        default:
                            b.append('\\');
                            b.append(c2);
                    }
                    break;
                default:
                    b.append(c);
            }
        }
        throw new ConfigFileException();
    }
}
