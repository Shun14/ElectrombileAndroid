package com.xiaoantech.electrombile.mqtt;

import android.util.Log;

import com.xiaoantech.electrombile.constant.EventBusConstant;
import com.xiaoantech.electrombile.constant.MqttCallbackConstant;
import com.xiaoantech.electrombile.constant.MqttCommonConstant;
import com.xiaoantech.electrombile.event.alarm.AlarmEvent;
import com.xiaoantech.electrombile.event.cmd.AutoLockEvent;
import com.xiaoantech.electrombile.event.cmd.AutoPeriodEvent;
import com.xiaoantech.electrombile.event.cmd.BatteryEvent;
import com.xiaoantech.electrombile.event.cmd.FenceEvent;
import com.xiaoantech.electrombile.event.cmd.LocationEvent;
import com.xiaoantech.electrombile.event.cmd.SeekEvent;
import com.xiaoantech.electrombile.event.cmd.SetBatteryTypeEvent;
import com.xiaoantech.electrombile.event.cmd.StatusEvent;
import com.xiaoantech.electrombile.event.fourtt.FourTT;
import com.xiaoantech.electrombile.event.gps.GPSEvent;
import com.xiaoantech.electrombile.event.notify.NotifyEvent;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangxu on 2016/11/2.
 */

public class MqttCallbackBus implements MqttCallback {
    private static final String TAG = "MqttCallback";
    @Override
    public void connectionLost(Throwable cause) {
        //TODO:connectionLost
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String messageStr = message.toString();
        if (null == messageStr){
            //TODO:Empty Message
            return;
        }
        try {
             JSONObject jsonObject = new JSONObject(messageStr);
            if (messageStr.contains(MqttCommonConstant.CMD)){
                receivedCMDMessage(jsonObject);
            }else if (messageStr.contains(MqttCommonConstant.GPS)){
                receivedGPSMessage(jsonObject);
            }else if (messageStr.contains(MqttCommonConstant.FOURTT)){
                receivedFourTT(jsonObject);
            }else if (messageStr.contains(MqttCommonConstant.ALARM)){
                receivedALARM(jsonObject);
            }else if (messageStr.contains(MqttCommonConstant.NOTIFY)){
                receivedNOTIFY(jsonObject);
            }else {
                //TODO
            }


        }catch (JSONException e){
            e.printStackTrace();
        }

        Log.d(TAG,message.toString());
    }

    private void receivedCMDMessage(JSONObject jsonObject){
        try {
            int code =  Integer.valueOf(jsonObject.get(MqttCommonConstant.CMD).toString());
            switch (code){
                case MqttCommonConstant.CMD_FENCE_ON:
                    EventBus.getDefault().post(new FenceEvent(EventBusConstant.cmdType.CMD_TYPE_FENCE_ON, jsonObject));
                    break;
                case MqttCommonConstant.CMD_FENCE_OFF:
                    EventBus.getDefault().post(new FenceEvent(EventBusConstant.cmdType.CMD_TYPE_FENCE_OFF, jsonObject));
                    break;
                case MqttCommonConstant.CMD_FENCE_GET:
                    EventBus.getDefault().post(new FenceEvent(EventBusConstant.cmdType.CMD_TYPE_FENCE_GET, jsonObject));
                    break;


                case MqttCommonConstant.CMD_SEEK_ON:
                    EventBus.getDefault().post(new SeekEvent(EventBusConstant.cmdType.CMD_TYPE_SEEK_ON, jsonObject));
                    break;
                case MqttCommonConstant.CMD_SEEK_OFF:
                    EventBus.getDefault().post(new SeekEvent(EventBusConstant.cmdType.CMD_TYPE_SEEK_OFF, jsonObject));
                    break;


                case MqttCommonConstant.CMD_LOCATION:
                    EventBus.getDefault().post(new LocationEvent(EventBusConstant.cmdType.CMD_TYPE_LOCATION, jsonObject));
                    break;


                case MqttCommonConstant.CMD_AUTO_LOCK_ON:
                    EventBus.getDefault().post(new AutoLockEvent(EventBusConstant.cmdType.CMD_TYPE_AUTOLOCK_ON, jsonObject));
                    break;
                case MqttCommonConstant.CMD_AUTO_LOCK_OFF:
                    EventBus.getDefault().post(new AutoLockEvent(EventBusConstant.cmdType.CMD_TYPE_AUTOLOCK_OFF, jsonObject));
                    break;
                case MqttCommonConstant.CMD_AUTOLOCK_GET:
                    EventBus.getDefault().post(new AutoLockEvent(EventBusConstant.cmdType.CMD_TYPE_AUTOLOCK_GET, jsonObject));
                    break;

                case MqttCommonConstant.CMD_AUTO_PERIOD_SET:
                    EventBus.getDefault().post(new AutoPeriodEvent(EventBusConstant.cmdType.CMD_TYPE_AUTOPERIOD_SET, jsonObject));
                    break;
                case MqttCommonConstant.CMD_AUTO_PERIOD_GET:
                    EventBus.getDefault().post(new AutoPeriodEvent(EventBusConstant.cmdType.CMD_TYPE_AUTOPERIOD_GET, jsonObject));
                    break;


                case MqttCommonConstant.CMD_BATTERY:
                    EventBus.getDefault().post(new BatteryEvent(EventBusConstant.cmdType.CMD_TYPE_BATTERY, jsonObject));
                    break;

                case MqttCommonConstant.CMD_STATUS_GET:
                    EventBus.getDefault().post(new StatusEvent(EventBusConstant.cmdType.CMD_TYPE_STATUS_GET, jsonObject));
                    break;

                case MqttCommonConstant.CMD_SET_BATTERY_TYPE:
                    EventBus.getDefault().post(new SetBatteryTypeEvent(EventBusConstant.cmdType.CMD_TYPE_SET_BATTERY_TYPE, jsonObject));
                    break;
            }//end
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void receivedGPSMessage(JSONObject jsonObject){
        EventBus.getDefault().post(new GPSEvent(jsonObject));
    }
    private void receivedFourTT(JSONObject jsonObject){
        EventBus.getDefault().post(new FourTT(jsonObject));
    }
    private void receivedALARM(JSONObject jsonObject){
        EventBus.getDefault().post(new AlarmEvent(jsonObject));
    }
    private void receivedNOTIFY(JSONObject jsonObject) {
        try {
            int notify = jsonObject.getInt(MqttCommonConstant.NOTIFY);
            switch (notify) {
                case MqttCallbackConstant.NOTIFY_AUTOLOCK:
                    EventBus.getDefault().post(new com.xiaoantech.electrombile.event.notify.AutoLockEvent(EventBusConstant.notifyType.NOTIFY_TYPE_AUTOLOCK, jsonObject));
                    break;
                case MqttCallbackConstant.NOTIFY_STATUS:
                    EventBus.getDefault().post(new com.xiaoantech.electrombile.event.notify.StatusEvent(EventBusConstant.notifyType.NOTIFY_TYPE_STATUS, jsonObject));
                    break;
                case MqttCallbackConstant.NOTIFY_BATTERY:
                    EventBus.getDefault().post(new com.xiaoantech.electrombile.event.notify.BatteryEvent(EventBusConstant.notifyType.NOTIFY_TYPE_BATTERY, jsonObject));
                    break;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

}
