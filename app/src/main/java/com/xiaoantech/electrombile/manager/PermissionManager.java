package com.xiaoantech.electrombile.manager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.PermissionChecker;

/**
 * Created by yangxu on 2016/11/2.
 */

public class PermissionManager {
    private static PermissionManager mInstance = null;

    public static PermissionManager getInstance(){
        if (null == mInstance){
            mInstance = new PermissionManager();
        }
        return mInstance;
    }

    public static boolean isHavePermission(Context context, String permission){
        return PermissionChecker.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED;
    }



}
