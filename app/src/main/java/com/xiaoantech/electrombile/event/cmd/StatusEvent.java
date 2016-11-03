package com.xiaoantech.electrombile.event.cmd;

import com.xiaoantech.electrombile.constant.EventBusConstant;

import org.json.JSONObject;

/**
 * Created by zzs on 2016/11/2.
 */

public class StatusEvent extends CMDEvent {
    public StatusEvent(EventBusConstant.cmdType type, JSONObject jsonObject){
        this.cmdType = type;
        this.jsonObject = jsonObject;
    }
}
