package com.xiaoantech.electrombile.event.cmd;

import com.xiaoantech.electrombile.constant.EventBusConstant;

import org.json.JSONObject;

/**
 * Created by yangxu on 2016/11/2.
 */

public class CMDEvent {
    protected EventBusConstant.cmdType     cmdType;
    protected JSONObject jsonObject;

    public JSONObject getJsonObject(){
        return jsonObject;
    }

    public EventBusConstant.cmdType getCmdType(){
        return cmdType;
    }
}
