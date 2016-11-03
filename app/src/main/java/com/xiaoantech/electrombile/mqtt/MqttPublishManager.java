package com.xiaoantech.electrombile.mqtt;

import com.xiaoantech.electrombile.constant.MqttCommonConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangxu on 2016/11/2.
 */

public class MqttPublishManager {

    private static MqttPublishManager mInstance = null;

    public static MqttPublishManager getInstance() {
        if (null == mInstance){
            mInstance = new MqttPublishManager();
        }
        return mInstance;
    }

    public void fenceOn(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_FENCE_ON));
    }

    public void fenceOff(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_FENCE_OFF));
    }

    public void fenceGet(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_FENCE_GET));
    }

    public void seekOn(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_SEEK_ON));
    }

    public void seekOff(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_SEEK_OFF));
    }

    public void getLocation(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_LOCATION));
    }

    public void autoLockOn(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_AUTO_LOCK_ON));
    }

    public void autoLockOff(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_AUTO_LOCK_OFF));
    }

    public void setAutoPeriod(String IMEI,int period){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_AUTO_PERIOD_SET,
                MqttCommonConstant.Period, period));
    }

    public void getAutoPeriod(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_AUTO_PERIOD_GET));
    }

    public void getAutoLock(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_AUTOLOCK_GET));
    }

    public void getBattery(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_BATTERY));
    }

    public void getStatus(String IMEI){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_STATUS_GET));
    }

    public void setBatteryType(String IMEI,int type){
        mInstance.publishCMD(IMEI,getPayloadWithCMD(MqttCommonConstant.CMD_SET_BATTERY_TYPE,
                MqttCommonConstant.Type, type));
    }

    private byte[] getPayloadWithCMD(int cmd){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put(MqttCommonConstant.CMD,cmd);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject.toString().getBytes();
    }

    private byte[] getPayloadWithCMD(int cmd,String name,int value) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put(MqttCommonConstant.CMD,cmd);
            jsonObject.put(name,value);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject.toString().getBytes();
    }

    private void publishCMD(String IMEI, byte[] payload){
        if (null != IMEI) {
            String topic = "app2dev/" + IMEI + "/cmd";
            MqttManager.getInstance().publish(topic, payload);
        }
        //TODO:Error
    }

}
