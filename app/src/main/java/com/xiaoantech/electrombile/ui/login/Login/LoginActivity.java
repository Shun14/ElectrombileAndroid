package com.xiaoantech.electrombile.ui.login.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.widget.Toast;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityLoginBinding;
import com.xiaoantech.electrombile.ui.login.ForgetPass.ForgetPassActivity;
import com.xiaoantech.electrombile.ui.login.LoginMain.LoginMainContract;
import com.xiaoantech.electrombile.ui.login.Register.RegisterActivity;

/**
 * Created by yangxu on 2016/10/28.
 */
public class LoginActivity extends BaseAcitivity implements LoginContract.View{
    private final static String         TAG = "LoginActivity";
    private ActivityLoginBinding        mBinding;
    private LoginContract.Presenter     mPresenter;
    private ProgressDialog              mProgessDialog;


    @Override
    protected void initBefore() {

    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    @Override
    protected void initView() {
        mPresenter = new LoginPresenter(this);
        mBinding.setPresenter(mPresenter);
        mProgessDialog = new ProgressDialog(this);
    }

    public void setPresenter(LoginContract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void showToast(String errorMeg) {
        Toast.makeText(this,errorMeg,Toast.LENGTH_SHORT).show();
        mProgessDialog.cancel();
    }

    @Override
    public void showWaitingDialog(String dialogString){
        mProgessDialog.setMessage(dialogString);
        mProgessDialog.show();
    }

    @Override
    public void loginSuccess() {
        Log.d(TAG,"Login Success!");
        mProgessDialog.cancel();
        //TODO:登录成功

    }

    @Override
    public void gotoRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoForgetPass() {
        Intent intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
        startActivity(intent);
    }
}
