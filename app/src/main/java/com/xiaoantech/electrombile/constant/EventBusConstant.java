package com.xiaoantech.electrombile.constant;

/**
 * Created by yangxu on 2016/11/2.
 */

public class EventBusConstant {
    public enum cmdType{
        CMD_TYPE_FENCE_ON,                 //设置小安宝开关
        CMD_TYPE_FENCE_OFF,                //
        CMD_TYPE_FENCE_GET,                 //获取小安宝开关开关

        CMD_TYPE_SEEK_ON,
        CMD_TYPE_SEEK_OFF,
        CMD_TYPE_LOCATION,

        CMD_TYPE_AUTOLOCK_ON,
        CMD_TYPE_AUTOLOCK_OFF,
        CMD_TYPE_AUTOLOCK_GET,

        CMD_TYPE_AUTOPERIOD_SET,
        CMD_TYPE_AUTOPERIOD_GET,

        CMD_TYPE_BATTERY,
        CMD_TYPE_STATUS_GET,
        CMD_TYPE_SET_BATTERY_TYPE
    }

//        EventType_AutoLockSet,              //设置自动落锁
//        EventType_AutoLockGet,              //获取自动落锁
//        EventType_AutoPeriodSet,            //设置落锁时间
//        EventType_AutoPeriodGet,            //获取落锁时间
//        EventType_AutoStatusGet,            //自动落锁状态获取
//        EventType_CMDGPSGET,                 //CMDGPS数据获得
//        EventType_FetchItinerary           //获取总里程数

    public enum notifyType{
        NOTIFY_TYPE_AUTOLOCK,
        NOTIFY_TYPE_STATUS,
        NOTIFY_TYPE_BATTERY

    }

//    public enum carSituationType{
//        carSituation_Online,
//        carSituation_Waiting,
//        carSituation_Offline,
//        carSituation_Unkown
//    }
}
