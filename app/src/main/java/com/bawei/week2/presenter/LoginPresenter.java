package com.bawei.week2.presenter;

import com.bawei.week2.base.BasePresenter;
import com.bawei.week2.base.IBaseView;
import com.bawei.week2.conteact.ILoginContract;
import com.bawei.week2.model.LoginModel;

import java.util.HashMap;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 */
public class LoginPresenter extends BasePresenter implements ILoginContract.ILoginPresenter {

    private LoginModel loginModel;

    public LoginPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void doLogin(String url, HashMap<String, String> map) {
        loginModel.doLogin(url, map, new ILoginContract.ILoginModel.ILoginCallBack() {
            @Override
            public void onLoginSuccess(String str) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView loginView= (ILoginContract.ILoginView) view;
                    loginView.onLoginSuccess(str);
                }
            }

            @Override
            public void onLoginFailure(String str) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView loginView= (ILoginContract.ILoginView) view;
                    loginView.onLoginFailure(str);
                }
            }
        });
    }
}
