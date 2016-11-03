package com.xiaoantech.electrombile.event.notify;

import com.xiaoantech.electrombile.constant.EventBusConstant;

import org.json.JSONObject;

/**
 * Created by zzs on 2016/11/2.
 */

public class StatusEvent extends NotifyEvent {
    public StatusEvent(EventBusConstant.notifyType notifyType, JSONObject jsonObject){
        this.jsonObject = jsonObject;
        this.notifyType = notifyType;
    }
}
