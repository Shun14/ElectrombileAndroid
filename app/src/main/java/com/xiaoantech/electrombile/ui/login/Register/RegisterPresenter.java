package com.xiaoantech.electrombile.ui.login.Register;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.xiaoantech.electrombile.constant.LeanCloudConstant;
import com.xiaoantech.electrombile.utils.StringUtil;

import java.util.List;

/**
 * Created by yangxu on 2016/10/31.
 */

public class RegisterPresenter implements RegisterContract.Presenter{
    private final static String     TAG = "RegisterPresenter";
    private RegisterContract.View   mRegisterView;

    protected RegisterPresenter(RegisterContract.View registerView){
        this.mRegisterView = registerView;
        mRegisterView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getIdentifiedCode(final String phoneNum) {
        if (StringUtil.isEmpty(phoneNum) || phoneNum.length() != 11){
            mRegisterView.showToast("请输入正确的手机号！");
            return;
        }

        AVUser user = new AVUser();

        user.setUsername(phoneNum);
        user.setMobilePhoneNumber(phoneNum);
        user.setPassword(LeanCloudConstant.defaultPassword);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null){
                    mRegisterView.showToast("验证码获取成功！");
                }else {
                    checkSignUpOrNotVerified(phoneNum);
                }
            }
        });

        Log.d(TAG,"getIdentifiedCode");
    }

    private void checkSignUpOrNotVerified(final String phoneNum){
        AVQuery<AVObject> query = new AVQuery<>(LeanCloudConstant.User);
        query.whereEqualTo(LeanCloudConstant.UserName,phoneNum);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (null == e){
                    if (list.size() < 0 ){
                        //未知错误
                        mRegisterView.showToast("未知错误！");
                    }else {
                        AVUser user = (AVUser) list.get(0);
                        if (user.isMobilePhoneVerified()){
                            mRegisterView.showToast("该用户已被注册，请直接登录！");
                        }else {
                            requestVerifiedCode(phoneNum);
                        }
                    }
                }else {
                    mRegisterView.showToast(e.toString());
                }
            }
        });
    }

    private void requestVerifiedCode(String phoneNum){
        AVUser user = new AVUser();
        user.setMobilePhoneNumber(phoneNum);
        AVUser.requestMobilePhoneVerifyInBackground(user.getMobilePhoneNumber(), new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (null != e){
                    mRegisterView.showToast(e.toString());
                }else {
                    mRegisterView.showToast("验证码获取成功");
                }
            }
        });
    }


    @Override
    public void register(String phoneNum, String identifiedCode, final String password, String passwordConfirm) {
        if (phoneNum == null || phoneNum.length() != 11){
            mRegisterView.showToast("请输入正确的手机号！");
            return;
        }
        if (password == null || passwordConfirm == null || !password.equals(passwordConfirm)){
            mRegisterView.showToast("两次密码输入不一致！");
            return;
        }

        AVUser.verifyMobilePhoneInBackground(identifiedCode, new AVMobilePhoneVerifyCallback() {
            @Override
            public void done(AVException e) {
                if (null == e){
                    AVUser user = AVUser.getCurrentUser();
                    user.setPassword(password);
                    user.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (null == e){
                                mRegisterView.finishRegister();
                            }else {
                                mRegisterView.showToast("注册失败，错误代码："+e.getCode());
                            }
                        }
                    });
                }else {
                    mRegisterView.showToast("注册失败，错误代码："+e.getCode());
                }
            }
        });
    }
}
