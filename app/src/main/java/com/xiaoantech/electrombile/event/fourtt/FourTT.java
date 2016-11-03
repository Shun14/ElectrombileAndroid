package com.xiaoantech.electrombile.event.fourtt;

import org.json.JSONObject;

/**
 * Created by zzs on 2016/11/2.
 */

public class FourTT {
    private JSONObject jsonObject;
    public FourTT(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
