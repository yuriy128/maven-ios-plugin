package de.letsdev.maven.plugins.ios;

/**
 * Maven iOS Plugin
 *
 * User: cwack
 * Date: 09.10.2012
 * Time: 19:54:44
 *
 * This code is copyright (c) 2012 let's dev.
 * URL: http://www.letsdev.de
 * e-Mail: contact@letsdev.de
 */

public class Utils {

    public enum PLUGIN_PROPERTIES {

        APP_DIR("appDir"),
        APPNAME("appName"),
        BUILD_ID("buildId"),
        CODE_SIGN_IDENTITY("codeSignIdentity"),
        CONFIGURATION("configuration"),
        HOCKEY_APP_TOKEN("hockeyAppToken"),
        KEYCHAIN_PASSWORD("keychainPassword"),
        KEYCHAIN_PATH("keychainPath"),
        RELEASE_NOTES("releaseNotes"),
        SCHEME("scheme"),
        SDK("sdk"),
        SOURCE_DIR("sourceDir"),
        TARGET("target"),
        TARGET_DIR("targetDir");

        private PLUGIN_PROPERTIES(String name) {
            this.name = name;
        }

        private final String name;

        public String toString() {
            return name;
        }
    }
}