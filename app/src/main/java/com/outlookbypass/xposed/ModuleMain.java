package com.outlookbypass.xposed;

import android.util.Log;

import java.lang.reflect.Method;

import io.github.libxposed.api.XposedModule;

public class ModuleMain extends XposedModule {

    private static final String TAG = "OutlookPolicyBypass";
    private static final String TARGET_PKG = "com.microsoft.office.outlook";
    private static final String DEVICE_POLICY_CLASS =
            "com.microsoft.office.outlook.olmcore.managers.mdm.DevicePolicy";

    @Override
    public void onModuleLoaded(ModuleLoadedParam param) {
        log(Log.INFO, TAG, "module loaded in " + param.getProcessName());
    }

    @Override
    public void onPackageLoaded(PackageLoadedParam param) {
        if (!TARGET_PKG.equals(param.getPackageName())) return;
        if (!param.isFirstPackage()) return;

        ClassLoader cl = param.getDefaultClassLoader();
        Class<?> devicePolicy;
        try {
            devicePolicy = Class.forName(DEVICE_POLICY_CLASS, false, cl);
        } catch (ClassNotFoundException e) {
            log(Log.WARN, TAG, "DevicePolicy class not found; Outlook version may have moved/renamed it", e);
            return;
        }

        hookBoolean(devicePolicy, "requiresDeviceManagement", false);
        hookBoolean(devicePolicy, "isPolicyApplied", true);
    }

    private void hookBoolean(Class<?> owner, String methodName, boolean forced) {
        Method m;
        try {
            m = owner.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            log(Log.WARN, TAG, owner.getName() + "." + methodName + "() not found", e);
            return;
        }
        hook(m).intercept(chain -> forced);
        log(Log.INFO, TAG, "hooked " + owner.getSimpleName() + "." + methodName + " -> " + forced);
    }
}
