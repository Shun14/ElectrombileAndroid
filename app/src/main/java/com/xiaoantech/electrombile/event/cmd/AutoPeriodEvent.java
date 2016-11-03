package com.xiaoantech.electrombile.event.cmd;

import com.xiaoantech.electrombile.constant.EventBusConstant;

import org.json.JSONObject;

/**
 * Created by zzs on 2016/11/2.
 */

public class AutoPeriodEvent extends CMDEvent {
    public AutoPeriodEvent(EventBusConstant.cmdType type, JSONObject jsonObject){
        this.cmdType = type;
        this.jsonObject = jsonObject;
    }
}
