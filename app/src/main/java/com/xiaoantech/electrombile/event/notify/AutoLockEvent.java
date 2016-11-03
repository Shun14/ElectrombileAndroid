package com.xiaoantech.electrombile.event.notify;

import com.xiaoantech.electrombile.constant.EventBusConstant;

import org.json.JSONObject;

/**
 * Created by zzs on 2016/11/2.
 */

public class AutoLockEvent extends NotifyEvent {
    public AutoLockEvent(EventBusConstant.notifyType notifyType, JSONObject jsonObject){
        this.notifyType = notifyType;
        this.jsonObject = jsonObject;
    }

}
