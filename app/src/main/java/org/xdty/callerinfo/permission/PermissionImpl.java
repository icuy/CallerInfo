package org.xdty.callerinfo.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;

public class PermissionImpl implements Permission {

    private Context mContext;

    public PermissionImpl(Context context) {
        mContext = context;
    }

    @Override
    public boolean canDrawOverlays() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(mContext);
        }
        return true;
    }

    @Override
    public void requestDrawOverlays(int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + mContext.getPackageName()));
            ((Activity) mContext).startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public int checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mContext.checkSelfPermission(permission);
        }
        return PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermissions(@NonNull String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((Activity) mContext).requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    requestCode);
        }
    }
}
