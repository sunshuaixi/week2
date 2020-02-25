package com.bawei.week2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 * * BaseActivity,我们需要在内部初始化我们的P层，BaseActivity作为一个基类，他是无法指定具体的哪一个P层
 *  * 所以必须使用P层的基类BasePresenter
 *  *
 *  * 告知使用了继承BasePresenter的泛型，然后实现IBaseView接口
 *  * 编写一个抽象方法initPresenter，让我们的子类完成初始化具体p层的工作
 *  * 在onCreate中初始化P层，在BaseActivity的子类中，完成重写的initPresenter这个抽象方法
 *  * @param <P>
 *  *
 *  *
 *  * 26.编写BaseActivity（或者BaseFragment），告知需要使用泛型<P extends BasePresenter>
 *  * 27.写一个抽象方法，提供给子类，具体初始化哪一个p层
 *  * 28.在onCreate中，调用抽象方法，并将结果保存成成员变量
 *  * 29.添加getPresenter方法，便于V层获取P层
 *  * 30.在onDestroy方法中，调用BasePresenter提供的解绑的方法
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());

        mPresenter = initPresenter();

        initView();
        initData();
    }

    /**
     * 提供一个给外部调用的，获取我们保存的P层的方法，以供外部使用
     * @return
     */
    public P getPresenter(){
        return mPresenter;
    }
    //在onDestroy生命周期中，我们需要完成P层与V层的解绑

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
            mPresenter=null;
        }
    }

    public abstract P initPresenter();

    protected abstract int getlayout();

    protected abstract void initView();

    protected abstract void initData();
}
