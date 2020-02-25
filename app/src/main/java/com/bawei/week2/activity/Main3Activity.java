package com.bawei.week2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.week2.R;
import com.bawei.week2.base.BaseActivity;
import com.bawei.week2.base.BasePresenter;
import com.bawei.week2.bean.BeanClass;
import com.bawei.week2.conteact.ILoginContract;
import com.bawei.week2.presenter.LoginPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

public class Main3Activity extends BaseActivity implements ILoginContract.ILoginView {


    private Button bt;
    private EditText et1;
    private EditText et3;
    private TextView tv;

    @Override
    public BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initView() {
        bt = findViewById(R.id.bt);
        et1 = findViewById(R.id.et1);
        et3 = findViewById(R.id.et3);
        tv = findViewById(R.id.tv);
    }

    @Override
    protected void initData() {
        final String path = "http://mobile.bwstudent.com/small/user/v1/register";
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et1.getText().toString();
                String pwd = et3.getText().toString();
                HashMap<String,String> map=new HashMap<>();
                map.put("phone",phone);
                map.put("pwd",pwd);

                BasePresenter presenter = getPresenter();
                if(presenter!=null&&presenter instanceof LoginPresenter){
                    ((LoginPresenter)presenter).doLogin(path,map);
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoginSuccess(String str) {
        Gson gson = new Gson();
        BeanClass beanClass = gson.fromJson(str, BeanClass.class);
        Toast.makeText(Main3Activity.this, ""+beanClass.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(String str) {

    }
}
