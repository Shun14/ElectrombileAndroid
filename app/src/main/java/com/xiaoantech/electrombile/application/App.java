package com.xiaoantech.electrombile.application;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by jk on 16-10-26.
 *
 * initialize AVOS cloud and the baidu map sdk
 *
 */

public class App extends Application {

    private static Context  context;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        AVOSCloud.initialize(this,"5wk8ccseci7lnss55xfxdgj9xn77hxg3rppsu16o83fydjjn","yovqy5zy16og43zwew8i6qmtkp2y6r9b18zerha0fqi5dqsw");
    }
}
