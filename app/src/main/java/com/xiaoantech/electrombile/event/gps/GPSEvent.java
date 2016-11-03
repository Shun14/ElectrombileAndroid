package com.xiaoantech.electrombile.event.gps;

import org.json.JSONObject;

/**
 * Created by zzs on 2016/11/2.
 */

public class GPSEvent {
    private JSONObject jsonObject;
    public GPSEvent(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }
    public JSONObject getJsonObject(){
        return jsonObject;
    }
}
