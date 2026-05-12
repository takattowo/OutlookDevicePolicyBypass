# Outlook Device Policy Bypass

[![ko-fi](https://img.shields.io/badge/Support_on-Ko--fi-FF5E5B?logo=ko-fi&logoColor=white)](https://ko-fi.com/takatto)

适用于 Android 的 LSPosed 模块，可绕过 Microsoft Outlook 的 MDM 设备管理/管理员设备要求，无需将手机注册为受管设备即可正常使用 Outlook。已测试支持 Android 15/16 与 Outlook 5.2+。

LSPosed module that kills the MDM device-policy enrollment prompt in **Microsoft Outlook for Android** (`com.microsoft.office.outlook`).

Lets you use Outlook on Android without having to set Outlook as Device Admin.

<img width="1440" height="2169" alt="image" src="https://github.com/user-attachments/assets/95e9ec2e-5cf6-4e8d-94af-9f35a6c0b13f" />

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
