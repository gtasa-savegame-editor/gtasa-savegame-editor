package nl.paulinternet.gtasaveedit.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import nl.paulinternet.gtasaveedit.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static nl.paulinternet.gtasaveedit.view.Main.LINUX;
import static nl.paulinternet.gtasaveedit.view.Main.MAC;
import static nl.paulinternet.gtasaveedit.view.Main.WINDOWS;

public final class ThemeManager {
    private static final Logger log = LoggerFactory.getLogger(ThemeManager.class);

    private ThemeManager() {
    }

    public static void install() {
        install(Settings.getThemeMode());
    }

    public static void install(int themeMode) {
        applyLinuxUiScaleOverride();
        boolean dark = themeMode == Settings.THEME_DARK
                || (themeMode == Settings.THEME_SYSTEM && isSystemDarkModePreferred());
        try {
            UIManager.setLookAndFeel(dark ? new FlatDarkLaf() : new FlatLightLaf());
            FlatLaf.updateUI();
        } catch (UnsupportedLookAndFeelException e) {
            log.warn("Unable to install theme!", e);
        }
    }

    private static void applyLinuxUiScaleOverride() {
        if (!LINUX || System.getProperty("flatlaf.uiScale") != null) {
            return;
        }
        Double scale = detectLinuxUiScale();
        if (scale != null && Math.abs(scale - 1.0) > 0.01) {
            String formatted = scale == Math.rint(scale) ? String.valueOf(scale.longValue()) : String.valueOf(scale);
            System.setProperty("flatlaf.uiScale", formatted);
        }
    }

    private static boolean isSystemDarkModePreferred() {
        try {
            if (WINDOWS) {
                return Advapi32Util.registryGetIntValue(WinReg.HKEY_CURRENT_USER,
                        "Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize", "AppsUseLightTheme") == 0;
            } else if (MAC) {
                return commandOutputContains("dark", "defaults", "read", "-g", "AppleInterfaceStyle");
            } else if (LINUX) {
                return isLinuxDarkModePreferred();
            }
        } catch (Throwable t) {
            log.debug("Unable to detect system theme preference, defaulting to light theme", t);
        }
        return false;
    }

    private static boolean isLinuxDarkModePreferred() throws Exception {
        Boolean kde = detectKdeDarkMode();
        if (kde != null) {
            return kde;
        }
        return detectGnomeDarkMode();
    }

    private static Boolean detectGnomeDarkMode() throws Exception {
        return commandOutputContains("dark", "gsettings", "get", "org.gnome.desktop.interface", "color-scheme")
                || commandOutputContains("dark", "gsettings", "get", "org.gnome.desktop.interface", "gtk-theme");
    }

    private static Boolean detectKdeDarkMode() {
        File kdeGlobals = new File(System.getProperty("user.home"), ".config/kdeglobals");
        String lookAndFeel = readIniValue(kdeGlobals, "KDE", "LookAndFeelPackage");
        if (lookAndFeel != null) {
            return lookAndFeel.toLowerCase().contains("dark");
        }
        String colorScheme = readIniValue(kdeGlobals, "General", "ColorScheme");
        if (colorScheme != null) {
            return colorScheme.toLowerCase().contains("dark");
        }
        return null;
    }

    private static String readIniValue(File file, String section, String key) {
        if (!file.isFile()) {
            return null;
        }
        String wantedSection = "[" + section + "]";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean inSection = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("[")) {
                    inSection = line.equals(wantedSection);
                } else if (inSection && line.startsWith(key + "=")) {
                    return line.substring(key.length() + 1).trim();
                }
            }
        } catch (IOException e) {
            log.debug("Unable to read '{}' from {}", key, file, e);
        }
        return null;
    }

    private static Double detectLinuxUiScale() {
        Double envScale = parseEnvScale("GDK_SCALE");
        if (envScale != null) {
            return envScale;
        }
        envScale = parseEnvScale("QT_SCALE_FACTOR");
        if (envScale != null) {
            return envScale;
        }
        try {
            Process process = new ProcessBuilder("xrdb", "-query").redirectErrorStream(true).start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Xft.dpi:")) {
                        double dpi = Double.parseDouble(line.substring("Xft.dpi:".length()).trim());
                        process.waitFor();
                        return dpi / 96.0;
                    }
                }
            }
            process.waitFor();
        } catch (Exception e) {
            log.debug("Unable to detect display scale from Xft.dpi", e);
        }
        return null;
    }

    private static Double parseEnvScale(String variable) {
        String value = System.getenv(variable);
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static boolean commandOutputContains(String needle, String... command) throws Exception {
        Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
        }
        process.waitFor();
        return output.toString().toLowerCase().contains(needle);
    }
}
