package com.xiaoantech.electrombile.ui.login.ForgetPass;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.xiaoantech.electrombile.utils.StringUtil;

/**
 * Created by yangxu on 2016/10/31.
 */

public class ForgetPassPresenter implements ForgetPassContract.Presenter {
    private static final String         TAG = "ForgetPassPresenter";
    private ForgetPassContract.View     mForgetPassView;

    public ForgetPassPresenter(ForgetPassContract.View forgetPassView){
        this.mForgetPassView = forgetPassView;
        mForgetPassView.setPresenter(this);
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getIdentifiedCode(String phoneNum) {
        if (StringUtil.isEmpty(phoneNum) || phoneNum.length() != 11){
            mForgetPassView.showToast("请输入正确的手机号！");
            return;
        }

        AVUser.requestPasswordResetBySmsCodeInBackground(phoneNum, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (null == e){
                    mForgetPassView.showToast("验证码获取成功！");
                }else {
                    if (e.getCode() == AVException.USER_WITH_MOBILEPHONE_NOT_FOUND){
                        mForgetPassView.showToast("该手机号不存在!");
                    }else{
                        mForgetPassView.showToast("获取验证码失败，错误代码:"+e.getCode());
                    }
                    Log.d(TAG,e.toString());

                }
            }
        });
    }

    @Override
    public void resetPass(String phoneNum, String identifiedCode, String password, String passwordConfirm) {
        if (phoneNum == null || phoneNum.length() != 11){
            mForgetPassView.showToast("请输入正确的手机号！");
            return;
        }
        if (password == null || passwordConfirm == null || !password.equals(passwordConfirm)){
            mForgetPassView.showToast("两次密码输入不一致！");
            return;
        }

        AVUser.resetPasswordBySmsCodeInBackground(identifiedCode, password, new UpdatePasswordCallback() {
            @Override
            public void done(AVException e) {
                if (null == e){
                    mForgetPassView.finishResetPass();
                }else {
                    mForgetPassView.showToast("密码修改失败，错误代码:"+e.getCode());
                }
            }
        });
    }
}
