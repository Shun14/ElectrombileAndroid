package com.xiaoantech.electrombile.event.alarm;

import org.json.JSONObject;

/**
 * Created by zzs on 2016/11/2.
 */

public class AlarmEvent {
    private JSONObject jsonObject;
    public AlarmEvent(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
