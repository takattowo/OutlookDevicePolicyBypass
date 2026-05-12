# Outlook Device Policy Bypass

LSPosed module that disables the MDM device-policy enrollment prompt in **Microsoft Outlook for Android** (`com.microsoft.office.outlook`).

Built against the modern [libxposed API 101](https://github.com/libxposed/api). Loads only into the Outlook process (static scope).

## What it hooks

Class: `com.microsoft.office.outlook.olmcore.managers.mdm.DevicePolicy`

| Method | Forced return |
|---|---|
| `requiresDeviceManagement()` | `false` |
| `isPolicyApplied()` | `true` |

If Microsoft renames/moves the class in a future Outlook build, the hook is skipped and a warning is logged to `logcat` under tag `OutlookPolicyBypass`. Update the class/method names in `app/src/main/java/com/outlookbypass/xposed/ModuleMain.java` to match.

## Requirements

- LSPosed (Zygisk or Riru) on a rooted device, **with libxposed API 101 enabled**.
- Outlook for Android.
- Android 8.0+ (API 26).

## Install

1. Download `OutlookDevicePolicyBypass-release-*.apk` from the [Releases](../../releases) page (or from CI artifacts).
2. Install on device: `adb install OutlookDevicePolicyBypass-release.apk`.
3. Open LSPosed Manager, enable the module, ensure scope contains **Outlook**.
4. Force-stop Outlook, then reopen.

Watch logs: `adb logcat -s OutlookPolicyBypass`.

## Build (CI)

`.github/workflows/build.yml` builds a signed release APK on every push and PR. Tag a commit `vX.Y.Z` to also publish a GitHub Release.

### Required repository secrets

| Secret | Purpose |
|---|---|
| `KEYSTORE_BASE64` | Base64-encoded JKS/PKCS12 keystore |
| `KEYSTORE_PASSWORD` | Keystore password |
| `KEY_ALIAS` | Signing key alias |
| `KEY_PASSWORD` | Signing key password |

Generate a keystore and the base64 blob:

```bash
keytool -genkey -v -keystore release.jks -keyalg RSA -keysize 2048 \
        -validity 10000 -alias outlookbypass
base64 -w0 release.jks > release.jks.b64   # paste contents into KEYSTORE_BASE64
```

If `KEYSTORE_BASE64` is not set the build falls back to the Android debug keystore so the APK still installs.

## Build (local)

Requires JDK 21 and Gradle 9.1.0+ (or the wrapper).

```bash
# first time only, to create gradle/wrapper/gradle-wrapper.jar
gradle wrapper

# build
./gradlew :app:assembleRelease
```

Output APK: `app/build/outputs/apk/release/`.

For local signing, create `keystore.properties` at repo root (git-ignored):

```
storeFile=/abs/path/to/release.jks
storePassword=...
keyAlias=outlookbypass
keyPassword=...
```

## License

Hook logic derived from the `Bypass Outlook Device Policy` feature in [darkeyes84/dark_tricks](https://github.com/darkeyes84/dark_tricks). All unrelated tweaks were stripped; the module has been rewritten against libxposed API 101.
