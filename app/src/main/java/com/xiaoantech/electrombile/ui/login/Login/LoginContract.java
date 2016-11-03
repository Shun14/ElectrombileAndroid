package com.xiaoantech.electrombile.ui.login.Login;

import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;

/**
 * Created by yangxu on 2016/10/30.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {

        void showToast(String errorMeg);

        void showWaitingDialog(String dialogString);

        void loginSuccess();

        void gotoRegister();

        void gotoForgetPass();
    }

    interface Presenter extends BasePresenter{
        void login(String userphone,String password);

        void register();

        void forgetPass();
    }
}
