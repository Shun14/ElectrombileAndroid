package com.xiaoantech.electrombile.manager;

import android.Manifest;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.xiaoantech.electrombile.application.App;

/**
 * Created by yangxu on 2016/11/2.
 */

public class DeviceInfoManager {
    private static DeviceInfoManager   mInstance = null;


    public static DeviceInfoManager getInstance(){
        if (null == mInstance){
            mInstance = new DeviceInfoManager();
        }
        return  mInstance;
    }

    public static String getDeviceId(){
        if (PermissionManager.isHavePermission(App.getContext(), Manifest.permission.READ_PHONE_STATE)){
            TelephonyManager telecomManager = (TelephonyManager) App.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            Log.d("test",telecomManager.getDeviceId());
            return telecomManager.getDeviceId();
        }
        return null;
    }
}
