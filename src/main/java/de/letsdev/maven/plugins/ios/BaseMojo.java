/**
 * maven-ios-plugin
 * <p>
 * User: cwack
 * Date: 2016-06-08
 * <p>
 * This code is copyright (c) 2016 let's dev.
 * URL: http://www.letsdev.de
 * e-Mail: contact@letsdev.de
 */

package de.letsdev.maven.plugins.ios;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import java.util.HashMap;
import java.util.Map;

public class BaseMojo extends AbstractMojo {

    /**
     * iOS Source Directory
     *
     * @parameter property="ios.sourceDir"
     * default-value="src/ios"
     */
    protected String sourceDir;

    /**
     * iOS app name
     *
     * @parameter property="ios.appName"
     * @required
     */
    protected String appName;

    /**
     * classifier
     *
     * @parameter property="ios.classifier"
     */
    protected String classifier;

    /**
     * iOS project name
     *
     * @parameter property="ios.projectName"
     */
    protected String projectName;

    /**
     * iOS provisioning profile  UUID
     * <p>
     * If not set the default provisioning file in xcode project will be used.
     *
     * @parameter property="ios.provisioningProfileUUID"
     */
    protected String provisioningProfileUUID;

    /**
     * iOS bundle identifier
     * <p>
     * If not set the bundle identifier in the info.plist will be used
     *
     * @parameter property="ios.bundleIdentifier"
     */
    protected String bundleIdentifier;

    /**
     * iOS display name
     * <p>
     * If not set the display name in the info.plist will be used
     *
     * @parameter property="ios.displayName"
     */
    protected String displayName;


    /**
     * iOS scheme. This is necessary for xcarchive builds.
     * <p>
     * Scheme must be set to "share" into the xcode project!
     *
     * @parameter property="ios.scheme"
     */
    protected String scheme;

    /**
     * iOS SDK
     *
     * @parameter property="ios.sdk"
     * default-value="iphoneos"
     */
    protected String sdk;

    /**
     * iphoneos SDK build architectures
     *
     * @parameter property="ios.iphoneosArchitectures"
     * default-value="arm64 armv7"
     */
    protected String iphoneosArchitectures;

    /**
     * iphonesimulator SDK build architectures
     *
     * @parameter property="ios.iphonesimulatorArchitectures"
     * default-value="i386 x86_64"
     */
    protected String iphonesimulatorArchitectures;

    /**
     * flag for bitcode enabled option for builds with iphonesimulator sdk
     *
     * @parameter property="ios.iphonesimulatorBitcodeEnabled"
     * default-value="true"
     */
    protected boolean iphonesimulatorBitcodeEnabled = true;

    /**
     * flag for iOS framework builds
     *
     * @parameter property="ios.iOSFrameworkBuild"
     * default-value="false"
     */
    protected boolean iOSFrameworkBuild;

    /**
     * flag for macosx framework builds
     *
     * @parameter property="ios.macOSFrameworkBuild"
     * default-value="false"
     */
    protected boolean macOSFrameworkBuild;

    /**
     * flag for iOS code signing enabled
     *
     * @parameter property="ios.codeSigningEnabled"
     */
    protected boolean codeSigningEnabled;

    /**
     * flag for iOS export to xcarchive enabled.
     * <p>
     * If false the .app will be generated instead of xcarchive.
     * <p>
     * You must set the xcode "scheme" value. Also the XCode scheme must be shared in the xcode project!
     * <p>
     * Default: true
     *
     * @parameter property="ios.buildXCArchiveEnabled"
     */
    protected boolean buildXCArchiveEnabled = true;

    /**
     * flag for iOS code signing with resources rules enabled
     * <p>
     * Following will be added to code sign execution:
     * <p>
     * <pre>CODE_SIGN_RESOURCE_RULES_PATH=$(SDKROOT)/ResourceRules.plist</pre>
     *
     * This was necessary from iOS SDK 6.1 until 8.0
     *
     * Default: false
     *
     * @parameter property="ios.codeSigningWithResourceRulesEnabled"
     */
    protected boolean codeSigningWithResourceRulesEnabled = false;

    /**
     * iOS code sign identity. The Code Sign identity, see distribution certficates common name.
     *
     * @parameter property="ios.codeSignIdentity"
     */
    protected String codeSignIdentity;

    /**
     * path to code signing entitlements file
     *
     * @parameter property="ios.codeSignEntitlements"
     */
    protected String codeSignEntitlements;

    /**
     * Path to keychain to sign with
     *
     * @parameter property="ios.keychainPath"
     */
    protected String keychainPath;

    /**
     * Password to unlock keychain to sign with
     *
     * @parameter property="ios.keychainPassword"
     */
    protected String keychainPassword;

    /**
     * iOS configuration, Release or Debug
     *
     * @parameter property="ios.configuration"
     * default-value="Release"
     */
    protected String configuration;

    /**
     * build id, will be set into info.plist
     *
     * @parameter property="ios.buildId"
     */
    protected String buildId;

    /**
     * target. The XCode target. See also "scheme".
     * <p>
     * If building apps with xcarchive, you must use "scheme" instead of target, then given target will be ignored.
     *
     * @parameter property="ios.target"
     */
    protected String target;

    /**
     * infoPlist
     *
     * @parameter property="ios.infoPlist"
     */
    protected String infoPlist;

    /**
     * app icon name
     *
     * @parameter property="ios.appIconName"
     */
    protected String appIconName;

    /**
     * gccPreprocessorDefinitions, added to xcodebuild command
     *
     * @parameter property="ios.gccPreprocessorDefinitions"
     */
    protected String gccPreprocessorDefinitions;

    /**
     * ipaVersion
     *
     * @parameter property="ios.ipaVersion"
     */
    protected String ipaVersion;

    /**
     * assetsDirectory, directory for assets (existing one will be renamed to assets-tmp)
     *
     * @parameter
     */
    protected String assetsDirectory;

    /**
     * appIcons, directory for appIcons (existing one will be renamed to appIcons-tmp)
     *
     * @parameter
     */
    protected String appIconsDirectory;

    /**
     * determines if project uses cocoapods, dependencies will be installed (via pod install) and .xcworkspace will be built instead of .xcodeproj
     *
     * @parameter
     */
    protected String cocoaPodsEnabled;

    /**
     * defining release task
     * available options are Release, Testflight & AppStoreUpload
     * <p>
     * property can also be set via environment variable RELEASE_TASK
     *
     * @parameter property="ios.releaseTask"
     * default-value="Release"
     */
    protected String releaseTask;

    /**
     * The maven project.
     *
     * @parameter property="project"
     * @required
     * @readonly
     */
    protected MavenProject mavenProject;

    protected Map<String, String> properties = null;

    protected Map<String, String> prepareProteries() {
        Map<String, String> properties = new HashMap<String, String>();

        final String targetDir = this.mavenProject.getBuild().getDirectory();

        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.APP_NAME.toString(), this.appName);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.APP_ICON_NAME.toString(), this.appIconName);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.PROJECT_NAME.toString(), this.projectName);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.GCC_PREPROCESSOR_DEFINITIONS.toString(), this.gccPreprocessorDefinitions);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.IPHONESIMULATOR_ARCHITECTURES.toString(), this.iphonesimulatorArchitectures);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.IPHONESIMULATOR_BITCODE_ENABLED.toString(), Boolean.toString(this.iphonesimulatorBitcodeEnabled));
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.IPHONEOS_ARCHITECTURES.toString(), this.iphoneosArchitectures);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.IOS_FRAMEWORK_BUILD.toString(), Boolean.toString(this.iOSFrameworkBuild));
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.MACOSX_FRAMEWORK_BUILD.toString(), Boolean.toString(this.macOSFrameworkBuild));
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.CODE_SIGNING_ENABLED.toString(), Boolean.toString(this.codeSigningEnabled));
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.BUILD_TO_XCARCHIVE_ENABLED.toString(), Boolean.toString(this.buildXCArchiveEnabled));
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.CODE_SIGN_WITH_RESOURCE_RULES_ENABLED.toString(), Boolean.toString(this.codeSigningWithResourceRulesEnabled));
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.CODE_SIGN_IDENTITY.toString(), this.codeSignIdentity);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.CODE_SIGN_ENTITLEMENTS.toString(), this.codeSignEntitlements);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.SDK.toString(), this.sdk);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.SOURCE_DIRECTORY.toString(), this.sourceDir);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.TARGET_DIR.toString(), targetDir);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.CONFIGURATION.toString(), this.configuration);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.BUILD_ID.toString(), this.buildId);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.SCHEME.toString(), this.scheme);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.TARGET.toString(), this.target);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.KEYCHAIN_PATH.toString(), this.keychainPath);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.KEYCHAIN_PASSWORD.toString(), this.keychainPassword);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.INFO_PLIST.toString(), this.infoPlist);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.IPA_VERSION.toString(), this.ipaVersion);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.ASSETS_DIRECTORY.toString(), this.assetsDirectory);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.APP_ICONS_DIRECTORY.toString(), this.appIconsDirectory);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.PROVISIONING_PROFILE_UUID.toString(), this.provisioningProfileUUID);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.BUNDLE_IDENTIFIER.toString(), this.bundleIdentifier);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.DISPLAY_NAME.toString(), this.displayName);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.CLASSIFIER.toString(), this.classifier);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.COCOA_PODS_ENABLED.toString(), this.cocoaPodsEnabled);
        this.addProperty(properties, Utils.PLUGIN_PROPERTIES.RELEASE_TASK.toString(), this.releaseTask);

        return properties;
    }

    protected void addProperty(Map<String, String> properties, String key, String value) {
        if (properties != null && key != null && value != null) {
            properties.put(key, value);
        }
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
         this.properties = prepareProteries();
    }
}
