package com.xiaoantech.electrombile.mqtt;

import android.util.Log;

import com.xiaoantech.electrombile.constant.ServiceConstant;
import com.xiaoantech.electrombile.manager.DeviceInfoManager;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

/**
 * Created by yangxu on 2016/11/2.
 */

public class MqttManager {
    private static final String TAG = "MqttManager";
    private static MqttManager mInstance    =   null;

    private MqttCallback    mCallback;

    private MqttClient      mClient;
    private MqttConnectOptions  connectOptions;
    private boolean clean   =   true;

    private MqttManager(){
        mCallback = new MqttCallbackBus();
    }

    public static MqttManager getInstance(){
        if (null == mInstance){
            mInstance = new MqttManager();
        }
        return mInstance;
    }

    /**
     * 释放单例
     */
    public static void release(){
        try{
            if (null != mInstance){
//                mInstance.
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createConnect(){
        boolean flag = false;
        String mqttDir = System.getProperty("java.io.tmpdir");
        try{
            MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(mqttDir);
            connectOptions = new MqttConnectOptions();
            connectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            connectOptions.setCleanSession(clean);
            String url = "tcp://" + ServiceConstant.MQTT_HOST + ":" +ServiceConstant.PORT;
            mClient = new MqttClient(url,DeviceInfoManager.getDeviceId(),dataStore);
            mClient.setCallback(mCallback);
            flag = doConnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    private boolean doConnect(){
        boolean flag = false;
        if (null != mClient){
            try{
                mClient.connect(connectOptions);
                Log.d(TAG,"connect to tcp");
                flag = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return flag;
    }

    public void subscribe(String IMEI){
        boolean flag = false;

        String topicCMD = "dev2app/" + IMEI + "/cmd";
        String topicGPS = "dev2app/" + IMEI + "/gps";
        String topic433 = "dev2app/" + IMEI + "/433";
        String topicAlarm = "dev2app/" + IMEI + "/alarm";
        String topicNotify = "dev2app/" + IMEI + "/notify";

        String[] topics = {topicCMD,topicGPS,topic433,topicAlarm,topicNotify};

        int[] qoss = {ServiceConstant.MQTT_QUALITY_OF_SERVICE,
                ServiceConstant.MQTT_QUALITY_OF_SERVICE,
                ServiceConstant.MQTT_QUALITY_OF_SERVICE,
                ServiceConstant.MQTT_QUALITY_OF_SERVICE,
                ServiceConstant.MQTT_QUALITY_OF_SERVICE};

        if (null != mClient && mClient.isConnected()){
            try{
                mClient.subscribe(topics,qoss);
                Log.d(TAG,"connection establish to devtoapp");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void unsubScribe(String IMEI){
        boolean flag = false;

        String topicCMD = "dev2app/" + IMEI + "/cmd";
        String topicGPS = "dev2app/" + IMEI + "/gps";
        String topic433 = "dev2app/" + IMEI + "/433";
        String topicAlarm = "dev2app/" + IMEI + "/alarm";
        String topicNotify = "dev2app/" + IMEI + "/notify";

        String[] topics = {topicCMD,topicGPS,topic433,topicAlarm,topicNotify};

        try {
            mClient.unsubscribe(topics);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public boolean publish(String topicName,byte[] payload){
        boolean flag = false;
        if (null != mClient && mClient.isConnected()){
            MqttMessage message = new MqttMessage(payload);
            message.setQos(ServiceConstant.MQTT_QUALITY_OF_SERVICE);
            try {
                mClient.publish(topicName,message);
                flag = true;
            }catch (MqttException e){
                e.printStackTrace();
            }
        }
        return flag;
    }
}

