package com.xiaoantech.electrombile.ui.login.LoginMain;



import android.content.Intent;
import android.util.Log;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.ui.login.Login.LoginActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yangxu on 2016/10/28.
 */
public class LoginMainPresenter implements LoginMainContract.Presenter{
    private LoginMainContract.View  mLoginMainView;
    private EventBus    mEventBus;

    public LoginMainPresenter(LoginMainContract.View LoginMainView){
        this.mLoginMainView = LoginMainView;
        this.mLoginMainView.setPresenter(this);
    }

    @Override
    public void subscribe(){
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }
    @Override
    public void unsubscribe(){
        mEventBus.unregister(this);
    }

    @Override
    public int getBackgroundImage(){
        return R.drawable.back ;
    }
    @Override
    public void register(){
        mLoginMainView.gotoRegister();
    }
    @Override
    public void login(){
        mLoginMainView.gotoLogin();
    }
}
