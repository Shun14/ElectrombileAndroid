package com.xiaoantech.electrombile.ui.login.ForgetPass;

import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;

/**
 * Created by yangxu on 2016/10/31.
 */

public interface ForgetPassContract {

    interface View extends BaseView<Presenter>{
        void showToast(String errorMsg);

        void finishResetPass();
    }

    interface Presenter extends BasePresenter {
        void getIdentifiedCode(String phoneNum);

        void resetPass(String phoneNum, String identifiedCode, String password, String passwordConfirm);
    }
}
