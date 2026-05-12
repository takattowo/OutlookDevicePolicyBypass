# Outlook Device Policy Bypass

[![ko-fi](https://img.shields.io/badge/Support_on-Ko--fi-FF5E5B?logo=ko-fi&logoColor=white)](https://ko-fi.com/takatto)

LSPosed module that kills the MDM device-policy enrollment prompt in **Microsoft Outlook for Android** (`com.microsoft.office.outlook`).

Lets you use Outlook on Android without having to make your phone a Managed/Admin Device.

Tested on Android 15 / 16 and Outlook 5.2+.

Built against the modern [libxposed API 101](https://github.com/libxposed/api). Only loads inside the Outlook process (static scope), nothing else gets touched.

## Install

1. Grab the APK from the [Releases](../../releases) page.
2. Install it.
3. Open LSPosed Manager → enable the module → make sure Outlook is in scope.
4. Force-stop Outlook, and enjoy!

If something looks off: `adb logcat -s OutlookPolicyBypass`.

## Contributing

Open an issue or PR. Bug reports with the Outlook version + Android version are extra welcome.

## Build (CI)

`.github/workflows/build.yml` builds a signed release APK on every push and PR. Tag a commit `vX.Y.Z` to also publish a GitHub Release.

## Support

If this saved you from corporate MDM hell, [buy me a coffee](https://ko-fi.com/takatto) ☕
