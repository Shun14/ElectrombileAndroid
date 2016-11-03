package com.xiaoantech.electrombile.event.cmd;

import com.xiaoantech.electrombile.constant.EventBusConstant;

import org.json.JSONObject;

/**
 * Created by yangxu on 2016/11/2.
 */

public class FenceEvent extends CMDEvent{
    public FenceEvent(EventBusConstant.cmdType type,JSONObject jsonObject){
        this.cmdType = type;
        this.jsonObject = jsonObject;
    }


}
