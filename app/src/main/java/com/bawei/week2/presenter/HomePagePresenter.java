package com.bawei.week2.presenter;

import android.util.Log;

import com.bawei.week2.base.BasePresenter;
import com.bawei.week2.base.IBaseView;
import com.bawei.week2.conteact.IHomePageContract;
import com.bawei.week2.model.HomePageModel;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 *  * 通常情况下，一个页面对应一个Presenter
 *  * 这一个Presenter里面包含当前页面下所有的接口
 *  *
 *  * 实现契约类中p层接口，重写方法
 *  *
 *  * 19.写一个具体的Presenter层，实现契约类中的Presenter接口，还需要继承BasePresenter（自动生成的构造方法我们不需要动）
 *  * 20.重写 initModel，在该方法中new 一个我们刚才创建的Model类，并提成成员变量
 *  * 21.重写契约类中的方法，在方法中我们需要使用刚才保存的m层的成员变量，来调用m层的方法
 *  * 22.通过接口回调，接收m层的数据
 *  * 23.通过getView（），拿到V层的泛型，通过instanceof 判断是否是我们契约类中的V层接口
 *  * 24.如果是，强转成契约类中的V层接口，并调用契约类中V层接口里面的方法
 *  * 25.如果不是，不做任何处理
 *  */
public class HomePagePresenter extends BasePresenter implements IHomePageContract.IPresenter {

    private HomePageModel homePageModel;

    public HomePagePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    public void getBanner(String url) {
        homePageModel.getBanner(url, new IHomePageContract.IModel.IModelCallback() {
            @Override
            public void onGetBannerSuccess(String str) {
                Log.i("xxx",str);
                IBaseView view = getView();
                if(view instanceof IHomePageContract.IView){
                    IHomePageContract.IView iView= (IHomePageContract.IView) view;
                    iView.onGetBannerSuccess(str);
                }
            }

            @Override
            public void onGetBannerFailure(String str) {
                IBaseView view = getView();
                if(view instanceof IHomePageContract.IView){
                    IHomePageContract.IView iView= (IHomePageContract.IView) view;
                    iView.onGetBannerFailure(str);
                }
            }
        });
    }

    @Override
    public void getList(String url) {
        homePageModel.getList(url, new IHomePageContract.IModel.IModelGetListCallback() {
            @Override
            public void onGetListSuccess(String str) {
                IBaseView view = getView();
                if(view instanceof IHomePageContract.IView){
                    IHomePageContract.IView iView= (IHomePageContract.IView) view;
                    iView.onGetListSuccess(str);
                }
            }

            @Override
            public void onGetListFailure(String str) {
                IBaseView view = getView();
                if(view instanceof IHomePageContract.IView){
                    IHomePageContract.IView iView= (IHomePageContract.IView) view;
                    iView.onGetListFailure(str);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        homePageModel = new HomePageModel();
    }
}
