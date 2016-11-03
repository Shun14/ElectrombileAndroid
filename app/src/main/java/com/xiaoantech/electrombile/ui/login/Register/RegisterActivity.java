package com.xiaoantech.electrombile.ui.login.Register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityRegisterBinding;
import com.xiaoantech.electrombile.ui.login.Login.LoginActivity;

/**
 * Created by yangxu on 2016/10/31.
 */

public class RegisterActivity extends BaseAcitivity implements RegisterContract.View {
    private final static String         TAG =   "RegisterActivity";
    private ActivityRegisterBinding     mBinding;
    private RegisterContract.Presenter  mPresenter;

    @Override
    protected void initBefore() {

    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
    }

    @Override
    protected void initView() {
        mPresenter = new RegisterPresenter(this);
        mBinding.setPresenter(mPresenter);

    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void showToast(String errorMeg) {
        Toast.makeText(this,errorMeg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishRegister(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
