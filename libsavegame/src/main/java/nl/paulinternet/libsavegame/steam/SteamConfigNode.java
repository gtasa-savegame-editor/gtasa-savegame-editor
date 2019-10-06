package nl.paulinternet.libsavegame.steam;

import java.util.HashMap;
import java.util.Map;

public class SteamConfigNode {
    private Map<String, SteamConfigNode> subNodes;
    private Map<String, String> strings;

    public SteamConfigNode() {
        subNodes = new HashMap<>();
        strings = new HashMap<>();
    }

    public void putSubNode(String key, SteamConfigNode value) {
        subNodes.put(key, value);
    }

    public void putString(String key, String value) {
        strings.put(key, value);
    }

    /*
     * Gets the value specified by the path.
     * If the value doesn't exist, null is returned.
     *
     * Example:
     *
     * getString("InstallConfigStore", "Software", "Valve", "Steam", "apps", "12120", "installdir")
     * might return
     * "D:\steamgames\steamapps\common\Grand Theft Auto San Andreas"
     */
    public String getString(String... path) {
        if (path == null || path.length == 0) throw new IllegalArgumentException();
        return getString(path, 0);
    }

    private String getString(String[] path, int pos) {
        if (path.length - pos == 1) {
            return strings.get(path[pos]);
        } else {
            SteamConfigNode subNode = subNodes.get(path[pos]);
            if (subNode == null) return null;
            return subNode.getString(path, pos + 1);
        }
    }
}
