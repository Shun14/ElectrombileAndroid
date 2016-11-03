package com.xiaoantech.electrombile.event.notify;

import com.xiaoantech.electrombile.constant.EventBusConstant;

import org.json.JSONObject;

/**
 * Created by zzs on 2016/11/2.
 */

public class NotifyEvent {
    protected EventBusConstant.notifyType      notifyType;
    protected JSONObject                       jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public EventBusConstant.notifyType getNotifyType() {
        return notifyType;
    }
}
