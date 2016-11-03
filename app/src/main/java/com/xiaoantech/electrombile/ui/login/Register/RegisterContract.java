package com.xiaoantech.electrombile.ui.login.Register;

import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;

/**
 * Created by yangxu on 2016/10/31.
 */

public interface RegisterContract  {
    interface View extends BaseView<Presenter>{

        void showToast(String errorMsg);

        void finishRegister();
    }



    interface Presenter extends BasePresenter{

        void getIdentifiedCode(String phoneNum);

        void register(String phoneNum, String identifiedCode, String password, String passwordConfirm);
    }
}
